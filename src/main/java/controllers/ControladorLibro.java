package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Libro;
import services.Ocupado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Libro", value = "/libros")
public class ControladorLibro extends HttpServlet {
    private List<Libro> carrito = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Libro[] libros = getLibros();
        request.setAttribute("libros", libros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }

    private Libro[] getLibros() {
        Libro[] libros = {new Libro("autor1"), new Libro("autor2"), new Libro("autor3")};
        return libros;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Libro[] libros = getLibros();
        String mensaje = "";
        Libro libro = buscarLibro(request.getParameter("libro"));


        if (libro.getEstadoLibro().equals(new Ocupado())) {
            System.out.println("Libro ya ocupado");
            mensaje = "El libro no se encuentra disponible";
            request.setAttribute("mensaje", mensaje);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
            dispatcher.forward(request, response);
            return;
        }
        libro.actualizarEstado(new Ocupado());
        agregarAlCarrito(libro);
        actualizarEstadoEnLibros(libro);
        request.setAttribute("carrito", carrito);
        request.setAttribute("libros", libros);
        System.out.println(libro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarEstadoEnLibros(Libro libro) {
        Libro[] libros = getLibros();
        for (int i = 0; i < libros.length; i++) {
            if (libros[i].getAutor().equals(libro.getAutor())) {
                libros[i].actualizarEstado(new Ocupado());
                break;
            }
        }
    }


    private void agregarAlCarrito(Libro libro) {
        carrito.add(libro);
    }

    private Libro buscarLibro(String autorLibro) {
        for (Libro libro : getLibros()) {
            if (libro.getAutor().equals(autorLibro)) {
                return libro;
            }
        }
        return null;
    }
}
