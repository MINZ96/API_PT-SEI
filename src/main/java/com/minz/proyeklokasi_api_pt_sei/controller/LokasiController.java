package com.minz.proyeklokasi_api_pt_sei.controller;

import com.minz.proyeklokasi_api_pt_sei.model.Lokasi;
import com.minz.proyeklokasi_api_pt_sei.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {

    @Autowired
    private LokasiRepository lokasiRepository;

    @PostMapping
    public ResponseEntity<Lokasi> createLokasi(@RequestBody Lokasi lokasi) {
        Lokasi savedLokasi = lokasiRepository.save(lokasi);
        return ResponseEntity.ok(savedLokasi);
    }

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAllLokasi() {
        List<Lokasi> lokasiList = lokasiRepository.findAll();
        return ResponseEntity.ok(lokasiList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lokasi> getLokasiById(@PathVariable Integer id) {
        Lokasi lokasi = lokasiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lokasi not found with id " + id));
        return ResponseEntity.ok(lokasi);
}

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Integer id, @RequestBody Lokasi lokasiDetails) {
        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lokasi not found with id " + id));
        
        lokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
        lokasi.setNegara(lokasiDetails.getNegara());
        lokasi.setProvinsi(lokasiDetails.getProvinsi());
        lokasi.setKota(lokasiDetails.getKota());

        Lokasi updatedLokasi = lokasiRepository.save(lokasi);
        return ResponseEntity.ok(updatedLokasi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLokasi(@PathVariable Integer id) {
        return lokasiRepository.findById(id)
                .map(lokasi -> {
                    lokasiRepository.delete(lokasi);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("Lokasi not found with id " + id));
    }
}