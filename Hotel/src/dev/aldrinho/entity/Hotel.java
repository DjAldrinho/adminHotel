package dev.aldrinho.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DjAldrinho on 4/01/2017.
 */
public class Hotel implements Serializable {

    private Integer id;
    private String nombre;
    private String pais;
    private String ciudad;
    private String direccion;
    private List<Persona> personas;


    public Hotel() {
    }

    public Hotel(String nombre, String pais, String ciudad, String direccion) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
