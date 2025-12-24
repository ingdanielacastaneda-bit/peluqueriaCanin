package com.mycompany.peluqueriacanina.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidad JPA que representa una mascota en el sistema.
 * Mantiene relación OneToOne con Duenio.
 * 
 * Nota: Los nombres de campos se mantienen para compatibilidad con la base de datos existente.
 */
@Entity
@Table(name = "mascota")
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_cliente")
    private int num_cliente;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "raza", nullable = false, length = 50)
    private String raza;
    
    @Column(name = "color", nullable = false, length = 50)
    private String color;
    
    @Column(name = "alergico", length = 10)
    private String alergico;
    
    @Column(name = "atencion_especial", length = 10)
    private String atencion_especial;
    
    @Column(name = "observaciones", length = 500)
    private String observaciones;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_duenio", nullable = false)
    private Duenio unDuenio;

    public Mascota() {
    }

    public Mascota(int num_cliente, String nombre, String raza, String color, 
                   String alergico, String atencion_especial, String observaciones, Duenio unDuenio) {
        this.num_cliente = num_cliente;
        this.nombre = nombre;
        this.raza = raza;
        this.color = color;
        this.alergico = alergico;
        this.atencion_especial = atencion_especial;
        this.observaciones = observaciones;
        this.unDuenio = unDuenio;
    }

    public int getNum_cliente() {
        return num_cliente;
    }

    public void setNum_cliente(int num_cliente) {
        this.num_cliente = num_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getAtencion_especial() {
        return atencion_especial;
    }

    public void setAtencion_especial(String atencion_especial) {
        this.atencion_especial = atencion_especial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Duenio getUnDuenio() {
        return unDuenio;
    }

    public void setUnDuenio(Duenio unDuenio) {
        this.unDuenio = unDuenio;
    }
    
    @Override
    public String toString() {
        return "Mascota{" + "num_cliente=" + num_cliente + ", nombre=" + nombre + 
               ", raza=" + raza + ", color=" + color + ", alergico=" + alergico + 
               ", atencion_especial=" + atencion_especial + '}';
    }
}

