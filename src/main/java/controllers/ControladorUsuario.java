package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class ControladorUsuario extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sessionFactory.openSession();
            response.getWriter().println("Conexión exitosa con la base de datos");
        } catch (Exception e) {
            response.getWriter().println("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");
        response.sendRedirect("/demo1_war_exploded/libros");
    }

    @Override
    public void init() throws ServletException {
        // Configurar Hibernate
        Configuration configuration = new Configuration().configure();

        // Opcional: Si tienes clases de entidad mapeadas, agrega las clases al Configuration
        // Ejemplo: configuration.addAnnotatedClass(TuClaseEntidad.class);

        // Crear el ServiceRegistry
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
    }
}
