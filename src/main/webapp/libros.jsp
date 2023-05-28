<%@ page import="java.util.ArrayList" %>
<%@ page import="services.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="models.ModeloLibro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="container">

<ul class="list-group">
    <li class="list-group-item active mt-5" aria-current="true">Libros</li>
    <%
        List<ModeloLibro> libros = (List<ModeloLibro>) request.getAttribute("libros");
        for (ModeloLibro libro : libros) {
    %>
    <li class="list-group-item">Titulo:<%= libro.getTitulo() %> <br> Autor:<%=libro.getAutor()%> <br>
        Catalogo: <%=libro.getCatalogo()%> <br>
        <form action="libros" method="post">
            <input type="hidden" name="libro" value="<%=libro.getTitulo()%>">
            <button class="btn btn-success" type="submit">Reservar</button>
        </form>
    </li>
    <% } %>
</ul>

<p class="text-danger">
    <% if (request.getParameter("mensaje") != null) { %>
    <%=request.getParameter("mensaje")%>
    }<% }%>
</p>

<ul class="list-group">
    <li class="list-group-item list-group-item-success">Carrito</li>
    <%
        List<Libro> carrito = (List<Libro>) request.getAttribute("carrito");
        if (carrito != null) {
            for (Libro libro : carrito) {
    %>
    <li class="list-group-item"><%=libro.getTitulo()%>
    </li>
    <% }
    }%>
</ul>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
