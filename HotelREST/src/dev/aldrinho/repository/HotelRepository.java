package dev.aldrinho.repository;

import dev.aldrinho.entity.Hotel;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Created by Sindy Borja on 1/01/2017.
 */
public interface HotelRepository {

    void saveHotel(Hotel hotel) throws Exception;

    void updateHotel(Hotel hotel) throws Exception;

    boolean existHotel(Hotel hotel) throws NonUniqueResultException, NoResultException;

    List<Hotel> findHotelByNombre(String nombre) throws NoResultException;

    List<Hotel> getAll() throws NoResultException;

}
