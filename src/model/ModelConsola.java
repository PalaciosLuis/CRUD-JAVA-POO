package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entidades.Consola;
import entidades.Producto;
import interfaces.InterfaceConsola;

public class ModelConsola implements InterfaceConsola {

	@Override
	public int registrarConsola(Consola c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "INSERT INTO Consola VALUES(NULL,?,1)";
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getNombre());
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
	public int actualizarConsola(Consola c) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql ="UPDATE CONSOLA SET "
					+ "NOMBRE_CONSOLA=? "
					+ "WHERE ID_CONSOLA="+c.getIdConsola();
			
			psm = cn.prepareStatement(sql);
			psm.setString(1,c.getNombre());
			
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
	public List<Consola> listarConsolas() {
		List<Consola> listaConsola = new ArrayList<Consola>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL listarConsolas(1)";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Consola p = new Consola(
					rs.getString("ID_CONSOLA"),
					rs.getString("NOMBRE_CONSOLA"));
			p.setCantidadProductos(rs.getInt("CANTIDAD_PRODUCTOS"));
			listaConsola.add(p);
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
		
		
		return listaConsola;
	}

	@Override
	public Consola infoConsola(String id) {
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		Consola c = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM CONSOLA WHERE ID_Consola="+id;
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			rs.next();
			c = new Consola(
					rs.getString("ID_CONSOLA"),
					rs.getString("NOMBRE_CONSOLA"));
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
	public int borrarConsola(String id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "UPDATE CONSOLA SET VISIBLE=NOT VISIBLE WHERE ID_CONSOLA="+id;
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
	public List<Producto> productosConsola(String id) {
		List<Producto> listaProducto = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL productoConsola(?);";
			psm = cn.prepareStatement(sql);
			psm.setString(1,id);
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
	public List<Consola> listarTopConsolas() {
		List<Consola> listaConsola = new ArrayList<Consola>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL topConsolas()";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Consola p = new Consola(
					rs.getString("ID_CONSOLA"),
					rs.getString("NOMBRE_CONSOLA"));
			p.setCantidadProductos(rs.getInt("TOTAL_VENTAS"));
			listaConsola.add(p);
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
		return listaConsola;
	}


	@Override
	public List<Consola> trashConsolas() {
		List<Consola> listaConsola = new ArrayList<Consola>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "CALL listarConsolas(0)";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			Consola p = new Consola(
					rs.getString("ID_CONSOLA"),
					rs.getString("NOMBRE_CONSOLA"));
			p.setCantidadProductos(rs.getInt("CANTIDAD_PRODUCTOS"));
			listaConsola.add(p);
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
		
		
		return listaConsola;
	}


}
