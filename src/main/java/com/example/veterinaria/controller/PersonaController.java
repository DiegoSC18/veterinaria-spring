package com.example.veterinaria.controller;

import com.example.veterinaria.model.Mascota;
import com.example.veterinaria.model.Persona;
import com.example.veterinaria.service.PersonaService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/persona")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona>
    obtenerPersonaPorId(@PathVariable Integer id) {
        return personaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?>
    crearPersona(@RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre es obligatorio");
        }

        if (persona.getApellido() == null || persona.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo apellido es obligatorio");
        }

        if (persona.getDni() == null) {
            return ResponseEntity.badRequest().body("El campo DNI es obligatorio");
        }

        if (persona.getEmail() == null || persona.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email es obligatorio");
        }

        if (persona.getTelefono() == null || persona.getTelefono().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El campo telefono es obligatorio");
        }
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }
}