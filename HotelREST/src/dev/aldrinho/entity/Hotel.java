package dev.aldrinho.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sindy Borja on 30/12/2016.
 */
@Entity(name = "Hotel")
@Table(name = "Hoteles")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    @Basic
    @Column(name = "pais", nullable = false, length = 60)
    private String pais;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 60)
    private String ciudad;
    @Basic
    @Column(name = "direccion", nullable = false, length = 60, unique = true)
    private String direccion;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Persona> personas;

    public Hotel(String nombre, String pais, String ciudad, String direccion) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Hotel(String nombre, String pais, String ciudad, String direccion, Persona administrador) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Hotel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != null ? !id.equals(hotel.id) : hotel.id != null) return false;
        if (nombre != null ? !nombre.equals(hotel.nombre) : hotel.nombre != null) return false;
        if (pais != null ? !pais.equals(hotel.pais) : hotel.pais != null) return false;
        if (ciudad != null ? !ciudad.equals(hotel.ciudad) : hotel.ciudad != null) return false;
        if (direccion != null ? !direccion.equals(hotel.direccion) : hotel.direccion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        return result;
    }


}
