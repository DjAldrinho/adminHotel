package dev.aldrinho.facades;

import dev.aldrinho.entity.Persona;
import dev.aldrinho.repository.PersonasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sindy Borja on 27/12/2016.
 */
@Component
public class PersonasFacade implements PersonasManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonasDao personasDao;


    @Override
    public List<Persona> getAll() {
        return personasDao.getAll();
    }

    @Override
    public List<Persona> getByIdentificacion(String identificacion) {
        return personasDao.getPersonaByIdentificacion(identificacion);
    }

    @Override
    public List<Persona> getById(Integer id) {
        return personasDao.getPersonaById(id);
    }

    @Override
    public List<Persona> getByEmail(String email) {
        return personasDao.getPersonaByEmail(email);
    }


    @Override
    public boolean deletePersona(Persona persona) throws Exception {
        personasDao.deletePersona(persona);
        return true;
    }

    @Override
    public boolean savePersona(Persona persona) throws Exception {
        if (!existPersona(persona) && getByEmail(persona.getUsuario().getEmail()) != null) {
            personasDao.savePersona(persona);
        }
        return true;
    }

    @Override
    public boolean updatePersona(Persona persona) throws Exception {
        personasDao.updatePersona(persona);
        return true;
    }


    private boolean existPersona(Persona persona) {
        return personasDao.existPersona(persona);
    }
}
