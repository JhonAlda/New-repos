package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import entidades.Genero;
import entidades.Pelicula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PeliServlet", urlPatterns = { "/PeliServlet" })
public class PeliServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emFactory;

    @Override
    public void init() throws ServletException {
        emFactory = Persistence.createEntityManagerFactory("SemanaWeb");
    }

    @Override
    public void destroy() {
        if (emFactory != null) {
            emFactory.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String director = request.getParameter("director");
        String fechaStr = request.getParameter("fechaEstreno");
        String idGeneroStr = request.getParameter("idGenero");

        if (nombre == null || nombre.trim().isEmpty()) {
            response.getWriter().println("Error: el nombre de la película es obligatorio.");
            return;
        }

        try {
            Date fechaEstreno = null;
            if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fechaEstreno = sdf.parse(fechaStr);
            }

            int idGenero = 0;
            if (idGeneroStr != null && !idGeneroStr.trim().isEmpty()) {
                idGenero = Integer.parseInt(idGeneroStr);
            }

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Pelicula pelicula = new Pelicula();
            pelicula.setNombre(nombre.trim());
            pelicula.setDirector(director != null ? director.trim() : null);
            pelicula.setFechaEstreno(fechaEstreno);

            if (idGenero > 0) {
                Genero genero = em.find(Genero.class, idGenero);
                pelicula.setGenero(genero);
            }

            em.persist(pelicula);
            em.getTransaction().commit();
            em.close();

            response.getWriter().println("Película registrada con éxito: " + nombre);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar la película: " + e.getMessage());
        }
    }
}
