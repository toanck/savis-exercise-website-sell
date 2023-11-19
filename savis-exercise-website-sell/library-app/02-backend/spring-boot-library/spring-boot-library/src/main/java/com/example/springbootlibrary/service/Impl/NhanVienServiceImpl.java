package com.example.springbootlibrary.service.Impl;

import com.example.springbootlibrary.dao.ChucVuRepository;
import com.example.springbootlibrary.dao.NhanVienRepository;
import com.example.springbootlibrary.entity.ChucVu;
import com.example.springbootlibrary.entity.NhanVien;
import com.example.springbootlibrary.requestmodels.NhanVienRequest;
import com.example.springbootlibrary.responsemodels.NhanVienReponse;
import com.example.springbootlibrary.service.NhanVienService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private ChucVuRepository chucVuRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    long currentTimestampMillis = System.currentTimeMillis();
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Page<NhanVienReponse> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return nhanVienRepository.getNhanVienAll(pageable);
    }

    @Override
    public Page<NhanVienReponse> getAllTrangThai(Integer pageNo, String tt) {
        Pageable pageable = PageRequest.of(pageNo, 100);
        return nhanVienRepository.getNhanVienTrangThai1(pageable, tt);
    }


    @Override
    public NhanVien add(NhanVienRequest nhanVienRequest) throws Exception {
        Optional<NhanVien> emailnv= nhanVienRepository.findNhanVienByEmail(nhanVienRequest.getEmail());
        Optional<NhanVien> sdtnv=nhanVienRepository.findNhanVienBySodienthoai(nhanVienRequest.getSodienthoai());
        if (emailnv.isPresent()) {
            throw new Exception("Email is already present!");
        }
        if(sdtnv.isPresent()){
            throw new Exception("So dien thoai is already present!");
        }
        NhanVien nhanVien = NhanVien.builder().chucVu(chucVuRepository.findById(getId(nhanVienRequest.getChucVu())).get())
                .ma(nhanVienRequest.getMa())
                .hoten(nhanVienRequest.getHoten())
                .email(nhanVienRequest.getEmail())
                .matkhau(nhanVienRequest.getMatkhau())
                .sodienthoai(nhanVienRequest.getSodienthoai())
                .gioitinh(nhanVienRequest.getGioitinh())
                .ngaysinh(nhanVienRequest.getNgaysinh())
                .trangthai(Integer.valueOf(nhanVienRequest.getTrangthai()))
                .anhdaidien(nhanVienRequest.getAnhdaidien())
                .ngaytao(nhanVienRequest.getNgaytao())
                .mota(nhanVienRequest.getMota())
                .phuongxa(nhanVienRequest.getPhuongxa())
                .quanhuyen(nhanVienRequest.getQuanhuyen())
                .tinhthanhpho((nhanVienRequest.getTinhthanhpho()))
                .build();
        nhanVienRepository.save(nhanVien);
        sendEmail(nhanVien);
        return nhanVien;
    }

    private void sendEmail(NhanVien nhanVien) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(nhanVien.getEmail());
            helper.setSubject("Chào mừng bạn đến với công ty");
            helper.setText("Xin chào " + nhanVien.getHoten() + ",\n\n" +
                    "Chúc mừng bạn đã trở thành thành viên của công ty chúng tôi.\n" +
                    "Dưới đây là một số thông tin về tài khoản của bạn:\n\n" +
                    "Mã nhân viên: " + nhanVien.getMa() + "\n" +
                    "Mật khẩu: " + nhanVien.getMatkhau() + "\n\n" +
                    "Trân trọng,\n");

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();  // Handle exception appropriately
        }
    }


    @Override
    public NhanVien update(NhanVienRequest nhanVienRequest, UUID id) throws Exception {
        Optional<NhanVien> emailnv= nhanVienRepository.findNhanVienByEmailAndIdNot(nhanVienRequest.getEmail(),id);
        Optional<NhanVien> sdtnv=nhanVienRepository.findNhanVienBySodienthoaiAndIdNot(nhanVienRequest.getSodienthoai(),id);
        if (emailnv.isPresent()) {
            throw new Exception("Email is already present!");
        }
        if(sdtnv.isPresent()){
            throw new Exception("So dien thoai is already present!");
        }
        NhanVien nhanVien = NhanVien.builder().id(id)
                .ma(nhanVienRequest.getMa())
                .hoten(nhanVienRequest.getHoten())
                .chucVu(chucVuRepository.findById(getId(nhanVienRequest.getChucVu())).get())
                .ngaytao(nhanVienRequest.getNgaytao())
                .email(nhanVienRequest.getEmail())
                .matkhau(nhanVienRequest.getMatkhau())
                .sodienthoai(nhanVienRequest.getSodienthoai())
                .gioitinh(nhanVienRequest.getGioitinh())
                .ngaysinh(nhanVienRequest.getNgaysinh())
                .trangthai(Integer.valueOf(nhanVienRequest.getTrangthai()))
                .anhdaidien(nhanVienRequest.getAnhdaidien())
                .ngaysua(nhanVienRequest.getNgaysua())
                .mota(nhanVienRequest.getMota())
                .phuongxa(nhanVienRequest.getPhuongxa())
                .quanhuyen(nhanVienRequest.getQuanhuyen())
                .tinhthanhpho((nhanVienRequest.getTinhthanhpho()))
                .build();
        return nhanVienRepository.save(nhanVien);

    }

    @Override
    public Page<NhanVienReponse> getSearch(Integer pageNo, String seach) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return nhanVienRepository.searchByKeyword(pageable, seach);
    }

    @Override
    public NhanVien detail(UUID id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public UUID getId(String ten) {
        for (ChucVu chucVu : chucVuRepository.findAll()) {
            if (ten.equals(chucVu.getTen())) {
                return chucVu.getId();
            }
        }
        return null;


    }

}
