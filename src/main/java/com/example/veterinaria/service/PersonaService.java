package com.example.veterinaria.service;

import com.example.veterinaria.model.Persona;
import com.example.veterinaria.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {this.personaRepository = personaRepository;}

    //1. Listar todas las Personas
    public List<Persona> listarTodas() {return personaRepository.findAll();}

    //2. Obtener Persona por ID
    public Optional<Persona> buscarPorId(Integer id){return personaRepository.findById(id);}

    //3. Crear Persona
    public Persona crearPersona(Persona persona){return personaRepository.save(persona);}

    //4. Actualizar Persona
    public Persona actualizarPersona(Integer id, Persona persona){
        persona.setId(id);
        return personaRepository.save(persona);
    }

    //5. Eliminar Persona
    public void eliminarMascota(Integer id){personaRepository.deleteById(id);}

}
