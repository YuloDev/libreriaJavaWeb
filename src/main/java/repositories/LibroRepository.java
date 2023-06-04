package repositories;

import java.util.List;

import models.ModeloLibro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import services.Libro;
import util.HibernateUtil;


public class LibroRepository {
    private SessionFactory sessionFactory;

    public LibroRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<ModeloLibro> obtenerLibros() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM ModeloLibro";
            return session.createQuery(hql, ModeloLibro.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Libro obtenerLibroPorTitulo(String titulo) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM ModeloLibro WHERE titulo = :titulo";
            Query<ModeloLibro> query = session.createQuery(hql, ModeloLibro.class);
            query.setParameter("titulo", titulo);
            ModeloLibro modeloLibro = query.uniqueResult();
            return obtenerLibro(modeloLibro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Libro obtenerLibro(ModeloLibro modeloLibro) {
        if (modeloLibro != null) {
            Libro libro = new Libro();
            libro.setTitulo(modeloLibro.getTitulo());
            libro.setAutor(modeloLibro.getAutor());
            return libro;
        } else {
            return null;
        }
    }
}
