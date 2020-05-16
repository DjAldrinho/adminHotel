package dev.aldrinho.repository;

import dev.aldrinho.entity.Persona;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Created by Sindy Borja on 27/12/2016.
 */
public interface PersonasRepository {

    List<Persona> getAll() throws NoResultException;

    List<Persona> getPersonaByIdentificacion(String identificacion) throws NonUniqueResultException, NoResultException;

    List<Persona> getPersonaById(Integer id) throws NoResultException;

    List<Persona> getPersonaByEmail(String email) throws NonUniqueResultException, NoResultException;

    boolean existPersona(Persona persona) throws NonUniqueResultException;

    void deletePersona(Persona persona) throws Exception;

    void savePersona(Persona persona) throws Exception;

    void updatePersona(Persona persona) throws Exception;

}
