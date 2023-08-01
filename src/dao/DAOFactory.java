package dao;

import interfaces.AuthInterface;
import interfaces.InterfaceCliente;
import interfaces.InterfaceConsola;
import interfaces.InterfaceProducto;
import interfaces.InterfaceTipoDocumento;
import interfaces.InterfaceCompra;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
	private static final int SQLSERVER = 2;
	private static final int ORACLE = 3;
	
	public abstract InterfaceProducto getProducto();
	public abstract InterfaceCliente getCliente();
	public abstract InterfaceCompra getCompra();
	public abstract InterfaceConsola getConsola();
	public abstract AuthInterface getAuth();
	public abstract InterfaceTipoDocumento getTipoDocumento();
	
	public static DAOFactory getDAOFactory(int tipo) {
		switch(tipo) {
		case MYSQL:
			return new MySQLDAOFactory();
		case SQLSERVER:
			return null;
		case ORACLE:
			return null;
		default: return null;
		}
	}
	
}
