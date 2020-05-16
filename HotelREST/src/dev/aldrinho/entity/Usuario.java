package dev.aldrinho.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Sindy Borja on 30/12/2016.
 */
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "cargo", nullable = false, length = 60)
    private String cargo;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = true)
    private Calendar fechaRegistro;


    public Usuario(String email, String password, String cargo, Calendar fechaRegistro) {
        this.email = email;
        this.password = password;
        this.cargo = cargo;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (password != null ? !password.equals(usuario.password) : usuario.password != null) return false;
        if (cargo != null ? !cargo.equals(usuario.cargo) : usuario.cargo != null) return false;
        if (fechaRegistro != null ? !fechaRegistro.equals(usuario.fechaRegistro) : usuario.fechaRegistro != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
        return result;
    }

}
