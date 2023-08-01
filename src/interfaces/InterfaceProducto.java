package interfaces;

import java.util.List;

import entidades.Producto;

public interface InterfaceProducto {
	public int registrarProducto(Producto p);
	public int actualizarProducto(Producto p);
	public List<Producto> listarProductos();
	public Producto infoProducto(String id);
	public int borrarProducto(String id);
	public List<Producto> listarTopProductos();
	public List<Producto> buscarProducto(String consulta);
	public List<Producto> trashProductos();
}
