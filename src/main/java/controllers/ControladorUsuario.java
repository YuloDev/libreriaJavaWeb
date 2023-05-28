package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Persona", value = "/persona")
public class ControladorUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String usuarioBdd = "Rafa";
        String passwordBdd = "1234";
        String usuario = req.getParameter("usuario");
        String contraseña = req.getParameter("contraseña");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Bienvenido" + "</h1>");
        out.println("</body></html>");
    }
}
