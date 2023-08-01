package interfaces;

import java.util.List;

import entidades.Consola;
import entidades.Producto;

public interface InterfaceConsola {
	public int registrarConsola(Consola c);
	public int actualizarConsola(Consola c);
	public List<Consola> listarConsolas();
	public Consola infoConsola(String id);
	public int borrarConsola(String id);
	public List<Producto> productosConsola(String id);
	public List<Consola> listarTopConsolas();
	public List<Consola> trashConsolas();
}
