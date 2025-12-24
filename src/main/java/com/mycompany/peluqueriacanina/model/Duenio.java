package com.mycompany.peluqueriacanina.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad JPA que representa al dueño de una mascota.
 * Almacena información de contacto y ubicación del dueño.
 * 
 * Nota: Los nombres de campos se mantienen para compatibilidad con la base de datos existente.
 */
@Entity
@Table(name = "duenio")
public class Duenio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duenio")
    private int id_duenio;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "cel_duenio", nullable = false, length = 20)
    private String celDuenio;
    
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    
    public Duenio() {
    }

    public Duenio(int id_duenio, String nombre, String celDuenio, String direccion) {
        this.id_duenio = id_duenio;
        this.nombre = nombre;
        this.celDuenio = celDuenio;
        this.direccion = direccion;
    }

    public int getId_duenio() {
        return id_duenio;
    }

    public void setId_duenio(int id_duenio) {
        this.id_duenio = id_duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelDuenio() {
        return celDuenio;
    }

    public void setCelDuenio(String celDuenio) {
        this.celDuenio = celDuenio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return "Duenio{" + "id_duenio=" + id_duenio + ", nombre=" + nombre + 
               ", celDuenio=" + celDuenio + ", direccion=" + direccion + '}';
    }
}

