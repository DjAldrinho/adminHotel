package dev.aldrinho.utils;

import dev.aldrinho.entity.Hotel;
import dev.aldrinho.entity.Persona;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DjAldrinho on 8/01/2017.
 */
public class PersonaMessageBodyReader implements MessageBodyReader<List<Persona>> {
    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public List<Persona> readFrom(Class<List<Persona>> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        if (mediaType.getType().equals("application") && mediaType.getSubtype().equals("json")) {
            Persona persona = new Persona();
            @SuppressWarnings("unchecked")
            List<Persona> personas = new ArrayList();
            JsonParser parser = Json.createParser(inputStream);
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        persona = new Persona();
                        break;
                    case END_OBJECT:
                        personas.add(persona);
                        break;
                    case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        switch (key) {
                            case "id":
                                persona.setId(parser.getInt());
                                break;
                            case "nombres":
                                persona.setNombres(parser.getString());
                                break;
                            case "apelidos":
                                persona.setApellidos(parser.getString());
                                break;
                            case "identificacion":
                                persona.setIdentificacion(parser.getString());
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            return personas;
        }
        throw new UnsupportedOperationException("Not supported MediaType: " + mediaType);
    }
}
