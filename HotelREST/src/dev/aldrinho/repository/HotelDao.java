package dev.aldrinho.repository;

import dev.aldrinho.entity.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sindy Borja on 1/01/2017.
 */
@Repository
@Transactional
public class HotelDao implements HotelRepository {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em = null;

    @Override
    @Transactional("transactionManager")
    public void saveHotel(Hotel hotel) throws Exception {
        em.persist(hotel);
        em.flush();
    }

    @Override
    @Transactional("transactionManager")
    public void updateHotel(Hotel hotel) throws Exception {
        em.merge(hotel);
        em.flush();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Hotel> findHotelByNombre(String nombre) throws NoResultException {
        /*Hotel hotel = null;*/
        List<Hotel> hoteles = em.createQuery("SELECT h from Hotel h where h.nombre like :nombre")
                .setParameter("nombre", "%" + nombre + "%").getResultList();

       /* if (hoteles.size() > 0) {
            hotel = hoteles.get(0);
        }*/
        return hoteles;

    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public boolean existHotel(Hotel hotel) throws NonUniqueResultException, NoResultException {
        List<Hotel> hoteles = em.createQuery("SELECT h from  Hotel h where h.nombre =:nombre " +
                "and h.pais =:pais and h.ciudad =:ciudad " +
                "and h.direccion =:direccion")
                .setParameter("nombre", hotel.getNombre())
                .setParameter("pais", hotel.getPais())
                .setParameter("ciudad", hotel.getCiudad())
                .setParameter("direccion", hotel.getDireccion()).getResultList();

        return hoteles.size() > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Hotel> getAll() {
        List<Hotel> hoteles = em.createQuery("Select h from Hotel h").getResultList();
        return hoteles.size() > 0 ? hoteles : null;
    }
}
