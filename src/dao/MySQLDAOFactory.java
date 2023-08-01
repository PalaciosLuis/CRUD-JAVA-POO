package dao;

import interfaces.AuthInterface;
import interfaces.InterfaceCliente;
import interfaces.InterfaceConsola;
import interfaces.InterfaceCompra;
import interfaces.InterfaceProducto;
import interfaces.InterfaceTipoDocumento;
import model.AuthModel;
import model.ModelCliente;
import model.ModelConsola;
import model.ModelCompra;
import model.ModelProducto;
import model.ModelTipoDocumento;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public InterfaceProducto getProducto() {
		return new ModelProducto();
	}

	public AuthInterface getAuth() {
		return new AuthModel();
		
	}
	@Override
	public InterfaceCliente getCliente() {
		// TODO Auto-generated method stub
		return new ModelCliente();
	}

	@Override
	public InterfaceCompra getCompra() {
		// TODO Auto-generated method stub
		return new ModelCompra();
	}

	@Override
	public InterfaceConsola getConsola() {
		// TODO Auto-generated method stub
		return new ModelConsola();
	}

	@Override
	public InterfaceTipoDocumento getTipoDocumento() {
		// TODO Auto-generated method stub
		return new ModelTipoDocumento();
	}

}
