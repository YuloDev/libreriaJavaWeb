package services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


import java.io.IOException;
import java.util.ArrayList;

public class Estudiante {

    String codigoUnico;
    String contraseña;

    public static void comprobarConeccion(SessionFactory sessionFactory, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sessionFactory.openSession();
            response.getWriter().println("Conexión exitosa con la base de datos");
        } catch (Exception e ) {
            response.getWriter().println("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    public static boolean iniciarSesion(HttpServletRequest request, HttpServletResponse response) {
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nombre_de_la_unidad_de_persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Realizar la consulta en la base de datos para verificar la existencia de usuario y contraseña
            // Asumiendo que existe una entidad Usuario con atributos "usuario" y "contraseña"
            String query = "SELECT COUNT(u) FROM Usuario u WHERE u.usuario = :usuario AND u.contraseña = :contraseña";
            Long count = entityManager.createQuery(query, Long.class)
                    .setParameter("usuario", usuario)
                    .setParameter("contraseña", contraseña)
                    .getSingleResult();

                transaction.commit();

            return count > 0; // Si el contador es mayor que cero, se encontró una coincidencia en la base de datos
            } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, registrar el error, mostrar un mensaje al usuario, etc.)
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }




    public void solicitarReservacion() {

    }

}
