package com.minz.proyeklokasi_api_pt_sei.repository;

import com.minz.proyeklokasi_api_pt_sei.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Integer> {
}