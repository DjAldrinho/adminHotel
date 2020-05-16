package dev.aldrinho.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sindy Borja on 30/12/2016.
 */
@Entity
@Table(name = "Personas")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    @Basic
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;
    @Basic
    @Column(name = "identificacion", nullable = false, length = 50)
    private String identificacion;
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = true)
    private Hotel hotel;


    public Persona() {
    }


    public Persona(String nombres, String apellidos, String identificacion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
    }

    public Persona(Integer id, String nombres, String apellidos, String identificacion, Usuario usuario, Hotel hotel) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id != null ? id.equals(persona.id) : persona.id == null && (nombres != null ? nombres.equals(persona.nombres) : persona.nombres == null && (apellidos != null ? apellidos.equals(persona.apellidos) : persona.apellidos == null && (identificacion != null ? identificacion.equals(persona.identificacion) : persona.identificacion == null)));

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (identificacion != null ? identificacion.hashCode() : 0);
        return result;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
