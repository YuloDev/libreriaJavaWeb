package models;

public class ModeloEstudiante {
    private int codigounico;
    private String contraseña;

    public int getCodigounico() {
        return codigounico;
    }

    public void setCodigounico(int codigounico) {
        this.codigounico = codigounico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModeloEstudiante that = (ModeloEstudiante) o;

        if (codigounico != that.codigounico) return false;
        if (contraseña != null ? !contraseña.equals(that.contraseña) : that.contraseña != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigounico;
        result = 31 * result + (contraseña != null ? contraseña.hashCode() : 0);
        return result;
    }
}
