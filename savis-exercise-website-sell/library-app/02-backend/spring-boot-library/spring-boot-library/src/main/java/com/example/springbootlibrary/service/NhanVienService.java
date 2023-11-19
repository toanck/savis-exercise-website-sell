package com.example.springbootlibrary.service;


import com.example.springbootlibrary.entity.NhanVien;
import com.example.springbootlibrary.requestmodels.NhanVienRequest;
import com.example.springbootlibrary.responsemodels.NhanVienReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    Page<NhanVienReponse> getAll(Integer pageNo);
    Page<NhanVienReponse> getAllTrangThai(Integer pageNo,String tt);
    NhanVien add(NhanVienRequest nhanVienRequest)throws Exception;
    NhanVien update(NhanVienRequest nhanVienRequest, UUID id) throws Exception;
    Page<NhanVienReponse> getSearch(Integer pageNo,String serch);
    NhanVien detail(UUID id);
}
