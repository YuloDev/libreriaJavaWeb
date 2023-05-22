package models;

import javax.persistence.*;
@Entity
@Table(name = "Usuarios")
public class ModeloUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_unico")
    private Integer codigoUnico;

    @Column(name = "nombre")
    private String nombre;

    public Integer getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(Integer codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Opcionalmente, puedes sobrescribir los métodos equals() y hashCode() para una comparación adecuada.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModeloUsuarios that = (ModeloUsuarios) o;

        if (codigoUnico != null ? !codigoUnico.equals(that.codigoUnico) : that.codigoUnico != null) return false;
        return nombre != null ? nombre.equals(that.nombre) : that.nombre == null;
    }

    @Override
    public int hashCode() {
        int result = codigoUnico != null ? codigoUnico.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}

