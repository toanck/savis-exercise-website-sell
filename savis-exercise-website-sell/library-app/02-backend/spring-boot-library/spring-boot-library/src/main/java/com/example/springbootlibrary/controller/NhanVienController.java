package com.example.springbootlibrary.controller;


import com.example.springbootlibrary.entity.NhanVien;
import com.example.springbootlibrary.requestmodels.NhanVienRequest;
import com.example.springbootlibrary.responsemodels.NhanVienReponse;
import com.example.springbootlibrary.service.NhanVienService;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/nhan-vien/")
@RestController
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @GetMapping("hien-thi")
    public Page<NhanVienReponse> getAll(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo){
        return nhanVienService.getAll(pageNo);

    }
    @GetMapping("hien-thiTT")
    public Page<NhanVienReponse> getAllTT(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo, @RequestParam(name = "search") String search ){
        return nhanVienService.getAllTrangThai(pageNo,search);

    }
    @GetMapping("/search")
    public Page<NhanVienReponse> searchProductsByName(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo, @RequestParam(name = "search") String search ) {
        return nhanVienService.getSearch(pageNo,search);
    }
    @GetMapping("detail/{id}")
    public NhanVien detail(@PathVariable("id") String id) {
        return nhanVienService.detail(UUID.fromString(id));
    }

    @PostMapping("add")
    public NhanVien post(@RequestBody NhanVienRequest nhanVienRequest)throws Exception{
        nhanVienRequest.setEmail(nhanVienRequest.getEmail());
        nhanVienRequest.setSodienthoai(nhanVienRequest.getSodienthoai());
        return nhanVienService.add(nhanVienRequest) ;
    }
    @PutMapping("update/{id}")
    public NhanVien update(@RequestBody NhanVienRequest nhanVienRequest, @PathVariable("id") String id) throws Exception{
        nhanVienRequest.setEmail(nhanVienRequest.getEmail());
        nhanVienRequest.setSodienthoai(nhanVienRequest.getSodienthoai());

        return nhanVienService.update(nhanVienRequest, UUID.fromString(id));
    }
}
