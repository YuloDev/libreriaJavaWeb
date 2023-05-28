package services;

public class Libro {

    EstadoLibro estadoLibro;
    Catalogo catalogo;
    String autor;
    String titulo;

    public Libro() {
    }

    public Libro(String titulo) {
        this.titulo = titulo;
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
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Catalogo getCatalogo() {
        return this.catalogo;
    }
}
