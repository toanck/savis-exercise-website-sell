package com.example.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ho_ten")
    private String hoten;

    @Column(name = "so_dien_thoai")
    private String sodienthoai;

    @Column(name = "email")
    private String email;

    @Column(name = "gioi_tinh")
    private Boolean gioitinh;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_sinh")
    private Date ngaysinh;

    @Column(name = "ngay_tao")
    private Timestamp ngaytao;

    @Column(name = "ngay_sua")
    private Timestamp ngaysua;

    @Column(name = "anh_dai_dien")
    private String anhdaidien;

    @Column(name = "mo_ta")
    private String mota;

    @Column(name = "tinh_thanh_pho")
    private String tinhthanhpho;

    @Column(name = "quan_huyen")
    private String quanhuyen;

    @Column(name = "phuong_xa")
    private String phuongxa;

    @Column(name = "mat_khau")
    private String matkhau;

    @Column(name = "ghi_chu")
    private String ghichu;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @Column(name = "da_xoa")
    private Boolean daxoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chuc_vu_id", referencedColumnName = "id")
    private ChucVu chucVu;
}

