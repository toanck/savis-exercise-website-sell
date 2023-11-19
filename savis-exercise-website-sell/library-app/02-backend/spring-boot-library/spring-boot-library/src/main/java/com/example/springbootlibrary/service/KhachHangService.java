package com.example.springbootlibrary.service;

import com.example.springbootlibrary.entity.DiaChi;
import com.example.springbootlibrary.requestmodels.KhachHangRequest;
import com.example.springbootlibrary.responsemodels.KhachHangReponse;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface KhachHangService {
    Page<KhachHangReponse> getAll(Integer pageNo);
    DiaChi add(KhachHangRequest khachHangRequest) throws Exception;
    DiaChi addid(KhachHangRequest khachHangRequest , UUID id);
    Page<KhachHangReponse> getAllTrangThai(Integer pageNo, String tt);
    DiaChi update(KhachHangRequest khachHangRequest, UUID id1,UUID id2) throws Exception;
    Page<KhachHangReponse> getSearch(Integer pageNo,String serch);
    DiaChi detail(UUID id);

}
