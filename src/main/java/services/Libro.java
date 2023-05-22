package services;

public class Libro {

    EstadoLibro estadoLibro;
    Catalogo catalogo;
    String autor;

    public Libro() {
    }

    public Libro(String autor) {
        this.autor = autor;
        this.estadoLibro = new Disponible();
    }

    public void actualizarEstado(EstadoLibro nuevoEstado) {
        this.estadoLibro = nuevoEstado;
    }

    public void reservar() {
        estadoLibro.reservar();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "estadoLibro=" + estadoLibro +
                ", autor='" + autor + '\'' +
                '}';
    }

    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    public String getAutor() {
        return this.autor;
    }
}
