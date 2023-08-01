package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entidades.Compra;
import interfaces.InterfaceCompra;

public class ModelCompra implements InterfaceCompra {

	@Override
	public int registrarCompra(Compra c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL efectuarCompra(?,?,?,?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getCliente());
			psm.setString(2,c.getProducto());
			psm.setString(3,Integer.toString(c.getCantidad()));
			psm.setString(4,c.getFecha());
			
			
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
	public int actualizarCompra(Compra c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql ="UPDATE Compra\r\n" + 
					"SET  ID_CLIENTE = ?,\r\n" + 
					"ID_PRODUCTO = ?,\r\n" + 
					"FECHA_Compra = ?,\r\n" + 
					"PAGO_TOTAL = ?,\r\n" +
					"WHERE ID_Compra = "+c.getIdCompra();
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getCliente());
			psm.setString(2,c.getProducto());
			psm.setString(3,c.getFecha());
			//psm.setString(4,Double.toString(c.getPagoTotal()));
			
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
	public List<Compra> listarCompras() {
		List<Compra> listaCompra = new ArrayList<Compra>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL listarCompras(1)";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Compra p = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			p.setIdCompra(rs.getString("ID"));
			p.setPagoTotal(rs.getDouble("PAGOTOTAL"));
			listaCompra.add(p);
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
		return listaCompra;
	}

	@Override
	public Compra infoCompra(String id) {
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		Compra c = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "call infoCompra("+id+");";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			rs.next();
			c = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			c.setIdCompra(rs.getString("ID"));
			c.setPagoTotal(rs.getDouble("PAGOTOTAL"));
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
	public int borrarCompra(String id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "UPDATE COMPRA SET VISIBLE = NOT VISIBLE WHERE ID_COMPRA = "+id;
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
	public List<Compra> listarComprasCliente(String id) {
		List<Compra> listaCompra = new ArrayList<Compra>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL listarComprasCliente("+id+")";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Compra p = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			p.setIdCompra(rs.getString("ID"));
			p.setPagoTotal(rs.getDouble("PAGOTOTAL"));
			listaCompra.add(p);
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
		
		
		return listaCompra;
	}


	@Override
	public List<Compra> listarTopCompras() {
		List<Compra> listaCompra = new ArrayList<Compra>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL topCompras()";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Compra p = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			p.setIdCompra(rs.getString("ID"));
			p.setPagoTotal(rs.getDouble("PAGOTOTAL"));
			listaCompra.add(p);
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
		return listaCompra;
	}


	@Override
	public List<Compra> buscarCompras(String inicio, String fin) {
		List<Compra> listaCompra = new ArrayList<Compra>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL buscarCompras(?,?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,inicio);
			psm.setString(2,fin);
			rs = psm.executeQuery();
			while (rs.next()) {
			Compra p = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			p.setIdCompra(rs.getString("ID"));
			p.setPagoTotal(rs.getDouble("PAGOTOTAL"));
			listaCompra.add(p);
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
		return listaCompra;
	}


	@Override
	public List<Compra> trashCompras() {
		List<Compra> listaCompra = new ArrayList<Compra>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL listarCompras(0)";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Compra p = new Compra(
					rs.getString("CLIENTE"),
					rs.getString("PRODUCTO") ,
					rs.getString("FECHA_COMPRA"), 
					rs.getInt("CANTIDAD"));
			p.setIdCompra(rs.getString("ID"));
			p.setPagoTotal(rs.getDouble("PAGOTOTAL"));
			listaCompra.add(p);
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
		return listaCompra;

	}


}














