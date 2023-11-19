package com.example.springbootlibrary.dao;

import com.example.demo.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
}
