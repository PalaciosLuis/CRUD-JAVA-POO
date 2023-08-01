package entidades;

public class Producto {
	private int idProducto, stock;
	private String nombreProducto, consola, descripcion;
	private double precioVenta;
	
	
	public Producto(int idProducto, String nombreProducto, String descripcion, String consola, int stock,
			double precioVenta) {
		super();
		this.idProducto = idProducto;
		this.stock = stock;
		this.descripcion = descripcion;
		this.nombreProducto = nombreProducto;
		this.consola = consola;
		this.precioVenta = precioVenta;
	}
	public Producto(String nombreProducto, String descripcion, String consola, int stock,
			double precioVenta) {
		super();
		this.stock = stock;
		this.nombreProducto = nombreProducto;
		this.consola = consola;
		this.precioVenta = precioVenta;
		this.descripcion = descripcion;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getConsola() {
		return consola;
	}
	public void setConsola(String consola) {
		this.consola = consola;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
