package dev.aldrinho.facades;

import dev.aldrinho.entity.Hotel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sindy Borja on 1/01/2017.
 */
public interface HotelManager extends Serializable {

    boolean saveHotel(Hotel hotel) throws Exception;

    boolean updateHotel(Hotel hotel) throws Exception;

    List<Hotel> getAll();

    List<Hotel> findHotelByNombre(String nombre);

}
