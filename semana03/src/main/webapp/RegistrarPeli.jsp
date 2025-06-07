<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Películas</title>
</head>
<body>
    <h1>Registrar Película</h1>

    <form action="PeliculassServlet" method="post">
        <label for="nombre">Nombre de la película:</label><br>
        <input type="text" name="nombre" id="nombre" required><br><br>

        <label for="director">Director:</label><br>
        <input type="text" name="director" id="director" required><br><br>

        <label for="fechaEstreno">Fecha de estreno:</label><br>
        <input type="date" name="fechaEstreno" id="fechaEstreno" required><br><br>

        <label for="idGenero">Género:</label><br>
        <select name="idGenero" id="idGenero" >
            <option value="">-- Seleccione un género --</option>
            <c:forEach var="gen" items="${listaGeneros}">
                <option value="${gen.idGenero}">${gen.nombre}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Registrar Película">
    </form>
</body>
</html>
