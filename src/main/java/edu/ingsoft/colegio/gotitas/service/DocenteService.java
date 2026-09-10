package main.java.edu.ingsoft.colegio.gotitas.service;

import main.java.edu.ingsoft.colegio.gotitas.model.Docente;
import main.java.edu.ingsoft.colegio.gotitas.repository.DocenteRepository;

public class DocenteService {

    private DocenteRepository docenteRepository = new DocenteRepository();

    public boolean registrarDocente(Docente docente) {
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()
                || docente.getApellido() == null || docente.getApellido().trim().isEmpty()) {
            System.out.println("Llenar todos los campos");
            return false;
        }

        if (docente.getCorreoElectronico() != null && !docente.getCorreoElectronico().contains("@")) {
            System.out.println("Validación: El correo electrónico no es válido. @ ");
            return false;
        }

        return docenteRepository.createDocente(docente);
    }

    public boolean updateDocente(Docente docente) {
    return docenteRepository.updateDocente(docente);
    
    }

    public boolean eliminarDocente(Docente docente) {
        return docenteRepository.deleteDocente(docente);
    }
}
