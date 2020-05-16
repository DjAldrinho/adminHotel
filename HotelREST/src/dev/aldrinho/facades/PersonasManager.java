package dev.aldrinho.facades;

import dev.aldrinho.entity.Persona;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sindy Borja on 28/12/2016.
 */
public interface PersonasManager extends Serializable {

    List<Persona> getAll();

    List<Persona> getByIdentificacion(String identificacion);

    List<Persona> getById(Integer id);

    List<Persona> getByEmail(String email);

    boolean deletePersona(Persona persona) throws Exception;

    boolean savePersona(Persona persona) throws Exception;

    boolean updatePersona(Persona persona) throws Exception;
}
