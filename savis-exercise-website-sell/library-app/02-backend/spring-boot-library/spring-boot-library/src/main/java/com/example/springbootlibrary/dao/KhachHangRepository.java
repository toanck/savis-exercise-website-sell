package com.example.springbootlibrary.dao;


import com.example.springbootlibrary.entity.KhachHang;
import com.example.springbootlibrary.responsemodels.KhachHangReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    @Query(value = """  
              select a.id,a.anh_dai_dien,b.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,b.mo_ta,b.phuong_xa,
            b.tinh_thanh_pho,b.quan_huyen from  khach_hang a inner join dia_chi b on a.id=b.khach_hang_id  order by  a.ngay_tao desc 
            """, nativeQuery = true)
    Page<KhachHangReponse> getKhachHangAll(Pageable pageable);


    @Query(value = """
            select a.id,a.anh_dai_dien,b.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,b.mo_ta,b.phuong_xa,
                   b.tinh_thanh_pho,b.quan_huyen from  khach_hang a inner join dia_chi b on a.id=b.khach_hang_id where a.trang_thai like %:search% order by a.ngay_tao desc
            """, nativeQuery = true)
    Page<KhachHangReponse> getKhachHangTrangThai(Pageable pageable, @Param("search") String search);

    @Query(value = """
            select a.id,a.anh_dai_dien,b.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,b.mo_ta,b.phuong_xa,
                   b.tinh_thanh_pho,b.quan_huyen from  khach_hang a inner join dia_chi b on a.id=b.khach_hang_id  where
                        a.ma like %:search% or a.ho_ten like %:search% or a.email like %:search% or a.so_dien_thoai like %:search%
            order by a.ngay_tao desc
            """, nativeQuery = true)
    Page<KhachHangReponse> searchByKeyword(Pageable pageable, @Param("search") String search);
    Optional<KhachHang> findKhachHangByEmail(String email);
    Optional<KhachHang> findKhachHangBySodienthoai(String sdt);
    Optional<KhachHang> findKhachHangByEmailAndIdNot(String email,UUID id);
    Optional<KhachHang> findKhachHangBySodienthoaiAndIdNot(String sdt,UUID id);
}
