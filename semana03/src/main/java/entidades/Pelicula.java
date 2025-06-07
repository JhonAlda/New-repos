package entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pelicula database table.
 * 
 */
@Entity
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenerar ID
	private int idpelicula;

	private String director;

	@Temporal(TemporalType.DATE)
	private Date fechaEstreno;

	private String nombre;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="idGenero")
	private Genero genero;

	public Pelicula() {
	}

	public int getIdpelicula() {
		return this.idpelicula;
	}

	public void setIdpelicula(int idpelicula) {
		this.idpelicula = idpelicula;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getFechaEstreno() {
		return this.fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}