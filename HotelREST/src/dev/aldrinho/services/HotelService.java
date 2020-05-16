package dev.aldrinho.services;

import dev.aldrinho.entity.Hotel;
import dev.aldrinho.facades.HotelFacade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Sindy Borja on 1/01/2017.
 */
@Path("hoteles")
public class HotelService {

    @Autowired
    private HotelFacade hotelFacade;

    @GET
    @Path("/test")
    @Produces("text/plain")
    public String HolaMundo() {
        return "Hola mundo";
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Hotel> getJson() {
        return hotelFacade.getAll();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Hotel hotel) throws Exception {
        Hotel h = new Hotel(hotel.getNombre(), hotel.getPais(), hotel.getCiudad(), hotel.getDireccion());
        if (hotelFacade.saveHotel(h)) {
            return Response.status(Response.Status.ACCEPTED).entity(h).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(h).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Hotel hotel) throws Exception {
        if (hotelFacade.updateHotel(hotel)) {
            return Response.status(Response.Status.ACCEPTED).entity(hotel).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(hotel).build();
        }
    }


    @GET
    @Path("/search")
    @Produces("application/json")
    public List<Hotel> getHotelByNombre(@QueryParam("nombre") String nombre) {
        return hotelFacade.findHotelByNombre(nombre);
    }
}
