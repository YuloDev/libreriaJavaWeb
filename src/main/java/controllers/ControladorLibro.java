package controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.ModeloLibro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import services.Libro;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Libro", value = "/libros")
public class ControladorLibro extends HttpServlet {
    private List<Libro> carrito = new ArrayList<Libro>();
    private List<Libro> libros = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        try {
            for (ModeloLibro libro : obtenerLibrosBdd()) {
                this.libros.add(new Libro(libro.getTitulo()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ModeloLibro> libros = obtenerLibrosBdd();
        request.setAttribute("libros", libros);
        System.out.println(this.libros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ModeloLibro> libros = obtenerLibrosBdd();
        String mensaje = "";
        Libro libro = buscarLibro(request.getParameter("libro"));
        agregarAlCarrito(libro);
        actualizarEstadoEnLibros(libro);
        request.setAttribute("carrito", carrito);
        request.setAttribute("libros", libros);
        System.out.println(libro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/libros.jsp");
        dispatcher.forward(request, response);
    }


    private List<ModeloLibro> obtenerLibrosBdd() throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<ModeloLibro> libros = null;
        try (Session session = sessionFactory.openSession()) {
            // Obtener todos los libros
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ModeloLibro> criteriaQuery = criteriaBuilder.createQuery(ModeloLibro.class);
            Root<ModeloLibro> root = criteriaQuery.from(ModeloLibro.class);
            criteriaQuery.select(root);
            Query<ModeloLibro> query = session.createQuery(criteriaQuery);
            libros = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return libros;
    }

    private Libro buscarLibro(String titulo) {
        Libro libroBuscado = null;
        for (Libro item : this.libros) {
            if (titulo.equals(item.getTitulo())) {
                libroBuscado = item;
            }
        }
        return libroBuscado;
    }

    private void actualizarEstadoEnLibros(Libro libro) {
//        ModeloLibro[] libros = getLibros().toArray(new ModeloLibro[0]);
//        for (int i = 0; i < libros.length; i++) {
//            if (libros[i].getAutor().equals(libro.getAutor())) {
//                libros[i].actualizarEstado(new Ocupado());
//                break;
//            }
//        }
    }


    private void agregarAlCarrito(Libro libro) {
        carrito.add(libro);
    }


}
