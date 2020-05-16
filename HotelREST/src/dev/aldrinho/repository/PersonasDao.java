package dev.aldrinho.repository;

import dev.aldrinho.entity.Persona;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Sindy Borja on 28/12/2016.
 */
@Repository
@Transactional
public class PersonasDao implements PersonasRepository {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em = null;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> getAll() throws NoResultException {
        return em.createQuery("Select p from Persona p").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> getPersonaByIdentificacion(String identificacion) throws NonUniqueResultException, NoResultException {
        return em.createQuery("Select p from Persona p where p.identificacion = :identificacion").
                setParameter("identificacion", identificacion).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> getPersonaById(Integer id) throws NoResultException {
        return em.createQuery("SELECT p from Persona  p where p.id = :id")
                .setParameter("id", id).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> getPersonaByEmail(String email) throws NonUniqueResultException, NoResultException {
        return em.createQuery("SELECT u from Persona.usuario u where u.email = :email")
                .setParameter("email", email).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existPersona(Persona persona) throws NonUniqueResultException {
        Persona p = (Persona) em.createQuery("SELECT p FROM Persona p where" +
                " p.nombres=:nombres and p.apellidos=:apellidos " +
                "and p.identificacion =:identificacion")
                .setParameter("nombres", persona.getNombres())
                .setParameter("apellidos", persona.getApellidos())
                .setParameter("identificacion", persona.getIdentificacion())
                .getSingleResult();

        return p != null;

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePersona(Persona persona) throws Exception {
        em.persist(persona);
        em.flush();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePersona(Persona persona) throws Exception {
        em.merge(persona);
        em.flush();
    }

    @Override
    @Transactional
    public void deletePersona(Persona persona) throws Exception {
        persona = em.getReference(Persona.class, persona.getId());
        em.remove(persona);
        em.flush();
    }

}
