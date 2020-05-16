package dev.aldrinho.facades;

import dev.aldrinho.entity.Hotel;
import dev.aldrinho.repository.HotelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sindy Borja on 1/01/2017.
 */
@Component
public class HotelFacade implements HotelManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HotelDao hotelDao;

    @Override
    public boolean saveHotel(Hotel hotel) throws Exception {
        if (!existHotel(hotel) || findHotelByNombre(hotel.getNombre()).isEmpty()) {
            hotelDao.saveHotel(hotel);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateHotel(Hotel hotel) throws Exception {
        hotelDao.updateHotel(hotel);
        return true;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    @Override
    public List<Hotel> findHotelByNombre(String nombre) {
        return hotelDao.findHotelByNombre(nombre);
    }

    private boolean existHotel(Hotel hotel) {
        return hotelDao.existHotel(hotel);
    }
}
