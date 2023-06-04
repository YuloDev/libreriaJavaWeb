package controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.LibroRepository;
import services.Libro;
import services.Ocupado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Libro", value = "/libros")
public class ControladorLibro extends HttpServlet {
    private List<Libro> carrito = new ArrayList<Libro>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibroRepository libroRepository = new LibroRepository();
        request.setAttribute("libros", libroRepository.obtenerLibros());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibroRepository libroRepository = new LibroRepository();
        agregarAlCarrito(libroRepository.obtenerLibroPorTitulo(request.getParameter("libro")));
        request.setAttribute("carrito", carrito);
        request.setAttribute("libros", libroRepository.obtenerLibros());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }

    private void agregarAlCarrito(Libro libro) {
        libro.actualizarEstado(new Ocupado());
        carrito.add(libro);
    }


}
