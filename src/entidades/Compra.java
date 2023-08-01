package entidades;


public class Compra {
	private String idCompra;
	private int cantidad;
	private String cliente, producto, fecha;
	private double pagoTotal;
	
	public Compra(String cliente, String producto, String Fecha, int cantidad) {
		super();
		this.cliente = cliente;
		this.producto = producto;
		this.fecha = Fecha;
		this.cantidad = cantidad;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}
	public String getFecha() {
		return this.fecha;
	}
	public void setFecha(String Fecha) {
		this.fecha= Fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPagoTotal() {
		return pagoTotal;
	}
	public void setPagoTotal(double pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	
	
}
