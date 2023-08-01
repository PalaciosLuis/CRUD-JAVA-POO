package interfaces;

import java.util.List;

import entidades.Cliente;

public interface InterfaceCliente {
	public int registrarCliente(Cliente c);
	public int actualizarCliente(Cliente c);
	public List<Cliente> listarClientes();
	public Cliente infoCliente(String id);
	public int borrarCliente(String id);
	public List<Cliente> listarTopClientes();
	public List<Cliente> buscarClientes(String consulta);
	public List<Cliente> trashClientes();
}
