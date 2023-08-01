package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.MySQLConnection;
import entidades.Cliente;
import interfaces.AuthInterface;

public class AuthModel implements AuthInterface{

	@Override
	public Cliente VerificarInicioSesion(String correo, String clave) {
		
		Cliente cliente =null;
		PreparedStatement psmt=null;
		Connection cn=null;
		ResultSet rs=null;
		try {
			cn=MySQLConnection.getConexion();
			String Mysql="SELECT * FROM CLIENTE WHERE EMAIL=? AND DOCUMENTO=?";
			psmt =cn.prepareStatement(Mysql);
			psmt.setString(1, correo);
			psmt.setString(2, clave);
			rs=psmt.executeQuery();
			if (rs.next()) {
				cliente=new Cliente();
				cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
				cliente.setNombre(rs.getString("NOMBRE_O_RZON_SOCIAL"));
				cliente.setTipoDocumento(rs.getString("TIPO_DOC"));
				cliente.setDocumento(rs.getString("DOCUMENTO"));
				cliente.setTelefono(rs.getString("TELEFONO"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setDireccion(rs.getString("DIRECCION"));
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return cliente;
	}
	

}
