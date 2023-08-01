package interfaces;

import java.util.List;

import entidades.Compra;

public interface InterfaceCompra {
	public int registrarCompra(Compra c);
	public int actualizarCompra(Compra c);
	public List<Compra> listarCompras();
	public List<Compra> listarComprasCliente(String id);
	public Compra infoCompra(String id);
	public int borrarCompra(String id);
	public List<Compra> listarTopCompras();
	public List<Compra> buscarCompras(String inicio, String fin);
	public List<Compra> trashCompras();
}
