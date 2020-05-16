package dev.aldrinho.utils;


import dev.aldrinho.entity.Hotel;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DjAldrinho on 4/01/2017.
 */
@Provider
@Consumes("application/json")
public class HotelMessageBodyReader implements MessageBodyReader<List<Hotel>> {


    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public List<Hotel> readFrom(Class<List<Hotel>> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        if (mediaType.getType().equals("application") && mediaType.getSubtype().equals("json")) {
            Hotel hotel = new Hotel();
            @SuppressWarnings("unchecked")
            List<Hotel> hoteles = new ArrayList();
            JsonParser parser = Json.createParser(inputStream);
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        hotel = new Hotel();
                        break;
                    case END_OBJECT:
                        hoteles.add(hotel);
                        break;
                    case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        switch (key) {
                            case "id":
                                hotel.setId(parser.getInt());
                                break;
                            case "nombre":
                                hotel.setNombre(parser.getString());
                                break;
                            case "pais":
                                hotel.setPais(parser.getString());
                                break;
                            case "ciudad":
                                hotel.setCiudad(parser.getString());
                                break;
                            case "direccion":
                                hotel.setDireccion(parser.getString());
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            return hoteles;
        }
        throw new UnsupportedOperationException("Not supported MediaType: " + mediaType);
    }


}
