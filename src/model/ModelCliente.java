/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entidades.Cliente;
import interfaces.InterfaceCliente;

/**
 * @author Jorge
 *
 */
public class ModelCliente implements InterfaceCliente {

	@Override
	public int registrarCliente(Cliente c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "INSERT INTO CLIENTE VALUES(NULL,?,?,?,?,?,?,1)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getNombre());
			psm.setString(2,c.getTipoDocumento());
			psm.setString(3,c.getDocumento());
			psm.setString(4,c.getTelefono());
			psm.setString(5,c.getEmail());
			psm.setString(6,c.getDireccion());
			
			value = psm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}


	@Override
	public int actualizarCliente(Cliente c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql ="UPDATE CLIENTE SET"
					+ " NOMBRE_O_RZON_SOCIAL=?, "
					+ "TIPO_DOC=?, "
					+ "DOCUMENTO=?, "
					+ "TELEFONO=?, "
					+ "EMAIL=?, "
					+ "DIRECCION=? "
					+ "WHERE ID_CLIENTE="+ Integer.toString(c.getIdCliente());
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getNombre());
			psm.setString(2,c.getTipoDocumento());
			psm.setString(3,c.getDocumento());
			psm.setString(4,c.getTelefono());
			psm.setString(5,c.getEmail());
			psm.setString(6,c.getDireccion());
			
			value = psm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM Cliente C " + 
					"JOIN TIPO_DOC T " + 
					"ON T.ID_TIPO_DOC = C.TIPO_DOC " + 
					"WHERE C.VISIBLE";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Cliente p = new Cliente(
					rs.getString("NOMBRE_O_RZON_SOCIAL"),
					rs.getString("COD_TIPO_DOC"),
					rs.getString("DOCUMENTO"),
					rs.getString("TELEFONO"),
					rs.getString("EMAIL"),
					rs.getString("DIRECCION")
					);
			p.setIdCliente(rs.getInt("ID_CLIENTE"));
			listaCliente.add(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}

	@Override
	public Cliente infoCliente(String id) {
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		Cliente c = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM Cliente C " + 
					"JOIN TIPO_DOC T " + 
					"ON T.ID_TIPO_DOC = C.TIPO_DOC " + 
					"WHERE ID_CLIENTE="+id;
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			rs.next();
			c = new Cliente(
					rs.getString("NOMBRE_O_RZON_SOCIAL"),
					rs.getString("TIPO_DOC"),
					rs.getString("DOCUMENTO"),
					rs.getString("TELEFONO"),
					rs.getString("EMAIL"),
					rs.getString("DIRECCION")
					);
			c.setIdCliente(rs.getInt("ID_CLIENTE"));
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public int borrarCliente(String id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "UPDATE CLIENTE SET VISIBLE=NOT VISIBLE WHERE ID_CLIENTE="+id;
			psm = cn.prepareStatement(sql);
			value = psm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}


	@Override
	public List<Cliente> listarTopClientes() {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL topClientes()";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Cliente p = new Cliente(
					rs.getString("NOMBRE_O_RZON_SOCIAL"),
					rs.getString("COD_TIPO_DOC"),
					rs.getString("DOCUMENTO"),
					rs.getString("TELEFONO"),
					rs.getString("EMAIL"),
					rs.getString("DIRECCION")
					);
			p.setIdCliente(rs.getInt("ID_CLIENTE"));
			listaCliente.add(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listaCliente;

	}


	@Override
	public List<Cliente> buscarClientes(String consulta) {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL buscarCliente(?);";
			psm = cn.prepareStatement(sql);
			psm.setString(1,consulta);
			rs = psm.executeQuery();
			while (rs.next()) {
			Cliente p = new Cliente(
					rs.getString("NOMBRE_O_RZON_SOCIAL"),
					rs.getString("COD_TIPO_DOC"),
					rs.getString("DOCUMENTO"),
					rs.getString("TELEFONO"),
					rs.getString("EMAIL"),
					rs.getString("DIRECCION")
					);
			p.setIdCliente(rs.getInt("ID_CLIENTE"));
			listaCliente.add(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}


	@Override
	public List<Cliente> trashClientes() {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM Cliente C " + 
					"JOIN TIPO_DOC T " + 
					"ON T.ID_TIPO_DOC = C.TIPO_DOC " + 
					"WHERE NOT C.VISIBLE";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Cliente p = new Cliente(
					rs.getString("NOMBRE_O_RZON_SOCIAL"),
					rs.getString("COD_TIPO_DOC"),
					rs.getString("DOCUMENTO"),
					rs.getString("TELEFONO"),
					rs.getString("EMAIL"),
					rs.getString("DIRECCION")
					);
			p.setIdCliente(rs.getInt("ID_CLIENTE"));
			listaCliente.add(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}

}

