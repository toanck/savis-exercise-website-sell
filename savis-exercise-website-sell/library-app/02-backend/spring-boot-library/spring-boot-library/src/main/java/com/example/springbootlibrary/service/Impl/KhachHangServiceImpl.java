package com.example.springbootlibrary.service.Impl;

import com.example.springbootlibrary.dao.DiaChiRepository;
import com.example.springbootlibrary.dao.KhachHangRepository;
import com.example.springbootlibrary.entity.DiaChi;
import com.example.springbootlibrary.entity.KhachHang;
import com.example.springbootlibrary.requestmodels.KhachHangRequest;
import com.example.springbootlibrary.responsemodels.KhachHangReponse;
import com.example.springbootlibrary.service.KhachHangService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private DiaChiRepository diaChiRepository;
    long currentTimestampMillis = System.currentTimeMillis();
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Page<KhachHangReponse> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return khachHangRepository.getKhachHangAll(pageable);
    }

    @Override
    public DiaChi add(KhachHangRequest khachHangRequest)throws Exception  {
        Optional<KhachHang> emailnv= khachHangRepository.findKhachHangByEmail(khachHangRequest.getEmail());
        Optional<KhachHang> sdtnv=khachHangRepository.findKhachHangBySodienthoai(khachHangRequest.getSodienthoai());
        if (emailnv.isPresent()) {
            throw new Exception("Email is already present!");
        }
        if(sdtnv.isPresent()){
            throw new Exception("So dien thoai is already present!");
        }
        KhachHang khachHang = KhachHang.builder()
                .ma(khachHangRequest.getMa()).hoten(khachHangRequest.getHoten())
                .email(khachHangRequest.getEmail())
                .anhdaidien(khachHangRequest.getAnhdaidien())
                .matkhau(khachHangRequest.getMatkhau())
                .sodienthoai(khachHangRequest.getSodienthoai())
                .gioitinh(khachHangRequest.getGioitinh())
                .ngaysinh(khachHangRequest.getNgaysinh())
                .ngaytao(khachHangRequest.getNgaytao())
                .trangthai(Integer.valueOf(khachHangRequest.getTrangthai())).build();
        KhachHang KhachHangg = khachHangRepository.save(khachHang);
        sendEmail(khachHang);
        DiaChi diaChi = DiaChi.builder().khachHang(KhachHangg)
                .tinhthanhpho(khachHangRequest.getTinhthanhpho())
                .phuongxa(khachHangRequest.getPhuongxa())
                .ngaytao(khachHangRequest.getNgaytao())
                .quanhuyen(khachHangRequest.getQuanhuyen())
                .mota(khachHangRequest.getMota())
                .trangthai(Integer.valueOf(khachHangRequest.getTrangthai())).build();
        return diaChiRepository.save(diaChi);
    }

    @Override
    public DiaChi addid(KhachHangRequest khachHangRequest, UUID id) {
        KhachHang khachHang = KhachHang.builder().id(id).build();
        DiaChi diaChi = DiaChi.builder().khachHang(khachHang)
                .tinhthanhpho(khachHangRequest.getTinhthanhpho())
                .phuongxa(khachHangRequest.getPhuongxa())
                .quanhuyen(khachHangRequest.getQuanhuyen())
                .mota(khachHangRequest.getMota())
                .trangthai(Integer.valueOf(khachHangRequest.getTrangthai()))
                .ngaytao(khachHangRequest.getNgaytao()).build();
        return diaChiRepository.save(diaChi);
    }

    @Override
    public Page<KhachHangReponse> getAllTrangThai(Integer pageNo, String tt) {
        Pageable pageable = PageRequest.of(pageNo, 100);
        return khachHangRepository.getKhachHangTrangThai(pageable, tt);
    }

    @Override
    public DiaChi update(KhachHangRequest khachHangRequest, UUID id1, UUID id2) throws Exception {
        Optional<KhachHang> emailnv= khachHangRepository.findKhachHangByEmailAndIdNot(khachHangRequest.getEmail(),id2);
        Optional<KhachHang> sdtnv=khachHangRepository.findKhachHangBySodienthoaiAndIdNot(khachHangRequest.getSodienthoai(),id2);
        if (emailnv.isPresent()) {
            throw new Exception("Email is already present!");
        }
        if(sdtnv.isPresent()){
            throw new Exception("So dien thoai is already present!");
        }
        KhachHang khachHang = KhachHang.builder().id(id2)
                .ma(khachHangRequest.getMa())
                .anhdaidien(khachHangRequest.getAnhdaidien())
                .ngaysua(khachHangRequest.getNgaysua())
                .hoten(khachHangRequest.getHoten())
                .email(khachHangRequest.getEmail())
                .matkhau(khachHangRequest.getMatkhau())
                .sodienthoai(khachHangRequest.getSodienthoai())
                .gioitinh(khachHangRequest.getGioitinh())
                .ngaysinh(khachHangRequest.getNgaysinh())
                .ngaytao(khachHangRequest.getNgaytao())
                .trangthai(Integer.valueOf(khachHangRequest.getTrangthai())).build();
        KhachHang KhachHangg = khachHangRepository.save(khachHang);
        DiaChi diaChi = DiaChi.builder().id(id1).khachHang(KhachHangg)
                .tinhthanhpho(khachHangRequest.getTinhthanhpho())
                .phuongxa(khachHangRequest.getPhuongxa())
                .ngaytao(khachHangRequest.getNgaytao())
                .quanhuyen(khachHangRequest.getQuanhuyen())
                .mota(khachHangRequest.getMota())
                .trangthai(Integer.valueOf(khachHangRequest.getTrangthai())).build();
        return diaChiRepository.save(diaChi);
    }
    private void sendEmail(KhachHang khachHang) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(khachHang.getEmail());
            helper.setSubject("Chào mừng bạn đến với công ty");
            helper.setText("Xin chào " + khachHang.getHoten() + ",\n\n" +
                    "Chúc mừng bạn đã trở thành khách hàng của công ty chúng tôi.\n" +
                    "Dưới đây là một số thông tin về tài khoản của bạn:\n\n" +
                    "Mã khách hàng: " + khachHang.getMa() + "\n" +
                    "Mật khẩu: " + khachHang.getMatkhau() + "\n\n" +
                    "Trân trọng,\n");

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();  // Handle exception appropriately
        }
    }

    @Override
    public Page<KhachHangReponse> getSearch(Integer pageNo, String seach) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return khachHangRepository.searchByKeyword(pageable, seach);
    }

    @Override
    public DiaChi detail(UUID id) {
        return diaChiRepository.findById(id).orElse(null);

    }


}
