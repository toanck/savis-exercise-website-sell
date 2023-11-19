
package com.example.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Table(name = "dia_chi")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "dia_chi_mac_dinh")
    private String diachimacdinh;

    @Column(name = "mo_ta")
    private String mota;

    @Column(name = "tinh_thanh_pho")
    private String tinhthanhpho;

    @Column(name = "quan_huyen")
    private String quanhuyen;

    @Column(name = "phuong_xa")
    private String phuongxa;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaytao;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", referencedColumnName = "id")
    private KhachHang khachHang;


}
