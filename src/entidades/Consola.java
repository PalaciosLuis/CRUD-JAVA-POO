package entidades;

public class Consola {
	private String idConsola;
	private String nombre;
	private int cantidadProductos;
	
	public Consola(String idConsola, String nombre) {
		super();
		this.idConsola = idConsola;
		this.nombre = nombre;
	}
	public String getIdConsola() {
		return idConsola;
	}
	public void setIdConsola(String idConsola) {
		this.idConsola = idConsola;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidadProductos() {
		return cantidadProductos;
	}
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	
	
}
