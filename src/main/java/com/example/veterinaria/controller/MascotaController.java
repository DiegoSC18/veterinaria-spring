package com.example.veterinaria.controller;

import com.example.veterinaria.model.Mascota;
import com.example.veterinaria.service.MascotaService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> obtenerMascotas() {
        return mascotaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota>
    obtenerMascotaPorId(@PathVariable Integer id) {
        return mascotaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?>
    crearMacota(@RequestBody Mascota mascota) {
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre es obligatorio");
        }

        if (mascota.getEdad() == null) {
            return ResponseEntity.badRequest().body("El campo edad es obligatorio");
        }

        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo especie es obligatorio");
        }

        if (mascota.getPeso() == null) {
            return ResponseEntity.badRequest().body("El campo peso es obligatorio");

        }
        return ResponseEntity.ok(mascotaService.crearMascota(mascota));
    }

}