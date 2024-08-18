package com.minz.proyeklokasi_api_pt_sei.controller;

import com.minz.proyeklokasi_api_pt_sei.model.Proyek;
import com.minz.proyeklokasi_api_pt_sei.model.Lokasi;
import com.minz.proyeklokasi_api_pt_sei.repository.LokasiRepository;
import com.minz.proyeklokasi_api_pt_sei.repository.ProyekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody Proyek proyek) {
        Proyek savedProyek = proyekRepository.save(proyek);
        return ResponseEntity.ok(savedProyek);
    }

    @GetMapping
    public ResponseEntity<List<Proyek>> getAllProyek() {
        List<Proyek> proyekList = proyekRepository.findAll();
        return ResponseEntity.ok(proyekList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Integer id) {
        Proyek proyek = proyekRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyek not found with id " + id));
        return ResponseEntity.ok(proyek);
}

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Integer id, @RequestBody Proyek proyekDetails) {
        Proyek proyek = proyekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyek not found with id " + id));
        
        proyek.setNamaProyek(proyekDetails.getNamaProyek());
        proyek.setClient(proyekDetails.getClient());
        proyek.setTglMulai(proyekDetails.getTglMulai());
        proyek.setTglSelesai(proyekDetails.getTglSelesai());
        proyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
        proyek.setKeterangan(proyekDetails.getKeterangan());

        Proyek updatedProyek = proyekRepository.save(proyek);
        return ResponseEntity.ok(updatedProyek);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProyek(@PathVariable Integer id) {
        return proyekRepository.findById(id)
                .map(proyek -> {
                    proyekRepository.delete(proyek);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("Proyek not found with id " + id));
    }

    @PostMapping("/{proyekId}/lokasi/{lokasiId}")
    public ResponseEntity<Proyek> addLokasiToProyek(@PathVariable Integer proyekId, @PathVariable Integer lokasiId) {
        Proyek proyek = proyekRepository.findById(proyekId)
                .orElseThrow(() -> new RuntimeException("Proyek not found with id " + proyekId));
        Lokasi lokasi = lokasiRepository.findById(lokasiId)
                .orElseThrow(() -> new RuntimeException("Lokasi not found with id " + lokasiId));
        
        proyek.getLokasi().add(lokasi);
        Proyek updatedProyek = proyekRepository.save(proyek);
        
        return ResponseEntity.ok(updatedProyek);
    }
}