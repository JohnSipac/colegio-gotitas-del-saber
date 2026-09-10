package main.java.edu.ingsoft.colegio.gotitas.service;

import main.java.edu.ingsoft.colegio.gotitas.model.Usuario;
import main.java.edu.ingsoft.colegio.gotitas.repository.RegistroRepository;


public class RegistroService {
    
  private final RegistroRepository registroRepository;

    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    public boolean registrar(String username, String email, String password) {
        // Validacion del correo
        if (registroRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El correo electrónico ya se encuentra registrado.");
        }

        // pasamos el usuario
        Usuario nuevoUsuario = new Usuario(null, username, email, null);
        return registroRepository.registrarUsuario(nuevoUsuario, password);
    }
    
}
