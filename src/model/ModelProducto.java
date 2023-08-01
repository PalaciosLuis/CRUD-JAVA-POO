package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entidades.Producto;
import interfaces.InterfaceProducto;

public class ModelProducto implements InterfaceProducto{

	@Override
	public int registrarProducto(Producto p) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "INSERT INTO PRODUCTO VALUES(NULL,?, ?, ?, ?, ?, 1)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,p.getNombreProducto());
			psm.setString(2,Double.toString(p.getPrecioVenta()));
			psm.setString(3,  p.getDescripcion());
			psm.setString(4,Integer.toString(p.getStock()));
			psm.setString(5,p.getConsola());
			
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
	public int actualizarProducto(Producto p) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "UPDATE PRODUCTO SET "
					+ "NOMBRE_PRO=?, "
					+ "PRECIO_VENTA = ?, "
					+ "DESCRIPCION = ?, "
					+ "STOCK = ?, ID_CONSOLA = ? "
					+ "WHERE ID_PRODUCTO="+p.getIdProducto();
			psm = cn.prepareStatement(sql);
			psm.setString(1,p.getNombreProducto());
			psm.setString(2,Double.toString(p.getPrecioVenta()));
			psm.setString(3,  p.getDescripcion());
			psm.setString(4,Integer.toString(p.getStock()));
			psm.setString(5,p.getConsola());
			
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
	public List<Producto> listarProductos() {
		List<Producto> listaProducto = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM PRODUCTO P\r\n" + 
					"INNER JOIN CONSOLA C\r\n" + 
					"ON P.ID_CONSOLA = C.ID_CONSOLA\r\n" + 
					"WHERE P.VISIBLE";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Producto p = new Producto(
					rs.getInt("ID_PRODUCTO"),
					rs.getString("NOMBRE_PRO"),
					rs.getString("DESCRIPCION"),
					rs.getString("NOMBRE_CONSOLA"),
					rs.getInt("STOCK"),
					rs.getDouble("PRECIO_VENTA")
					);
			listaProducto.add(p);
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
		
		
		return listaProducto;
	}

	@Override
	public Producto infoProducto(String id) {
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		Producto p = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM producto P\r\n" + 
					"INNER JOIN CONSOLA C\r\n" + 
					"ON P.ID_CONSOLA = C.ID_CONSOLA\r\n" + 
					"WHERE P.ID_PRODUCTO = "+id;
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			rs.next();
			p = new Producto(
					rs.getInt("ID_PRODUCTO"),
					rs.getString("NOMBRE_PRO"),
					rs.getString("DESCRIPCION"),
					rs.getString("NOMBRE_CONSOLA"),
					rs.getInt("STOCK"),
					rs.getDouble("PRECIO_VENTA")
					);
					
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
		return p;
	}

	@Override
	public int borrarProducto(String id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "UPDATE PRODUCTO SET VISIBLE=NOT VISIBLE WHERE ID_PRODUCTO="+id;
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
	public List<Producto> listarTopProductos() {
		List<Producto> listaProducto = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL topProductos()";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Producto p = new Producto(
					rs.getInt("ID_PRODUCTO"),
					rs.getString("NOMBRE_PRO"),
					rs.getString("DESCRIPCION"),
					rs.getString("NOMBRE_CONSOLA"),
					rs.getInt("STOCK"),
					rs.getDouble("PRECIO_VENTA")
					);
			listaProducto.add(p);
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
		return listaProducto;
	}

	@Override
	public List<Producto> buscarProducto(String consulta) {
		List<Producto> listaProducto = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL buscarProductos(?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,consulta);
			rs = psm.executeQuery();
			while (rs.next()) {
			Producto p = new Producto(
					rs.getInt("ID_PRODUCTO"),
					rs.getString("NOMBRE_PRO"),
					rs.getString("DESCRIPCION"),
					rs.getString("NOMBRE_CONSOLA"),
					rs.getInt("STOCK"),
					rs.getDouble("PRECIO_VENTA")
					);
			listaProducto.add(p);
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
		return listaProducto;
	}

	@Override
	public List<Producto> trashProductos() {
		List<Producto> listaProducto = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM PRODUCTO P\r\n" + 
					"INNER JOIN CONSOLA C\r\n" + 
					"ON P.ID_CONSOLA = C.ID_CONSOLA\r\n" + 
					"WHERE NOT P.VISIBLE";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Producto p = new Producto(
					rs.getInt("ID_PRODUCTO"),
					rs.getString("NOMBRE_PRO"),
					rs.getString("DESCRIPCION"),
					rs.getString("NOMBRE_CONSOLA"),
					rs.getInt("STOCK"),
					rs.getDouble("PRECIO_VENTA")
					);
			listaProducto.add(p);
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
		return listaProducto;

	}
	
}
