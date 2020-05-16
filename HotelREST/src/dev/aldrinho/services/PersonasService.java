package dev.aldrinho.services;

import dev.aldrinho.entity.Persona;
import dev.aldrinho.entity.Usuario;
import dev.aldrinho.facades.PersonasFacade;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;


@Path("personas")
public class PersonasService {


    @Autowired
    private PersonasFacade personasFacade;

    @GET
    @Path("/test")
    @Produces("text/plain")
    public String HolaMundo() {
        return "Hola mundo";
    }


    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Persona> getJson() {
        return personasFacade.getAll();
    }


    @GET
    @Path("/search")
    @Produces("application/json")
    public List<Persona> search(@DefaultValue("Undefined") @QueryParam("email") String email, @DefaultValue("Undefined")
    @QueryParam("identficacion") String identificacion,
                                @DefaultValue("0") @QueryParam("id") Integer id) {
        if (!email.equals("Undefined")) {
            return personasFacade.getByEmail(email);
        } else if (!identificacion.equals("Undefined")) {
            return personasFacade.getByIdentificacion(identificacion);
        } else {
            return personasFacade.getById(id);
        }
    }


    @POST
    @Path("/save")
    @Produces("text/html")
    @Consumes("multipart/form-data")
    public Response save(@FormDataParam("persona") Persona persona, @FormDataParam("usuario") Usuario usuario) throws Exception {
        Persona p = new Persona(persona.getNombres(), persona.getApellidos(), persona.getIdentificacion());
        Calendar cal = Calendar.getInstance();
        Usuario u = new Usuario(usuario.getEmail(), usuario.getPassword(), usuario.getCargo(), cal);
        p.setUsuario(u);
        personasFacade.savePersona(p);
        return Response.ok().entity("Usuario Creado con exito!").build();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Persona persona) throws Exception {
        personasFacade.updatePersona(persona);
        return Response.ok().entity("Contraseña actualizada con exito!").build();
    }

    @DELETE
    @Path("/delete")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(Persona persona) throws Exception {
        personasFacade.deletePersona(persona);
        return Response.ok().entity("Usuario eliminado con exito!").build();
    }

}

