package com.example.springbootlibrary.dao;


import com.example.springbootlibrary.entity.NhanVien;
import com.example.springbootlibrary.responsemodels.NhanVienReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query(value = """  
            select a.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.anh_dai_dien,b.ten as chuc_vu,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,a.mo_ta,a.phuong_xa,
                        a.tinh_thanh_pho,a.quan_huyen from  nhan_vien a inner join chuc_vu b on a.chuc_vu_id=b.id 
                        order by a.ngay_tao desc """, nativeQuery = true)
    Page<NhanVienReponse> getNhanVienAll(Pageable pageable);

    @Query(value = """  
            select a.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.anh_dai_dien,b.ten as chuc_vu,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,a.mo_ta,a.phuong_xa,
                        a.tinh_thanh_pho,a.quan_huyen from  nhan_vien a inner join chuc_vu b on a.chuc_vu_id=b.id where
                                                    a.trang_thai like %:search% 
                        order by a.ngay_tao desc """, nativeQuery = true)
    Page<NhanVienReponse> getNhanVienTrangThai1(Pageable pageable, @Param("search") String search);

    @Query(value = """  
            select  a.id, a.ma,a.ho_ten,a.email,a.mat_khau,a.anh_dai_dien,b.ten as chuc_vu,a.so_dien_thoai,a.gioi_tinh,a.ngay_sinh,a.trang_thai,a.ngay_tao,a.mo_ta,a.phuong_xa,
                                                    a.tinh_thanh_pho,a.quan_huyen from  nhan_vien a inner join chuc_vu b on a.chuc_vu_id=b.id  where
                                                    a.ma like %:search% or a.ho_ten like %:search% or a.email like %:search% or a.so_dien_thoai like %:search%
                                                    order by a.ngay_tao desc """, nativeQuery = true)
    Page<NhanVienReponse> searchByKeyword(Pageable pageable, @Param("search") String search);
    Optional<NhanVien> findNhanVienByEmail(String email);
    Optional<NhanVien> findNhanVienBySodienthoai(String sdt);
    Optional<NhanVien> findNhanVienByEmailAndIdNot(String email,UUID id );
    Optional<NhanVien> findNhanVienBySodienthoaiAndIdNot(String sodienthoai, UUID id);


}
