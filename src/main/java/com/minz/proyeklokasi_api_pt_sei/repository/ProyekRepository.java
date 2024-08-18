package com.minz.proyeklokasi_api_pt_sei.repository;

import com.minz.proyeklokasi_api_pt_sei.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Integer> {
}