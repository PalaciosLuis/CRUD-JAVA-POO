package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entidades.TipoDocumento;
import interfaces.InterfaceTipoDocumento;

public class ModelTipoDocumento implements InterfaceTipoDocumento {

	@Override
	public int registrarTipoDocumento(TipoDocumento c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizarTipoDocumento(TipoDocumento c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TipoDocumento> listarTipoDocumentos() {
		List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConexion();
			String sql = "SELECT * FROM TIPO_DOC WHERE VISIBLE";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
			TipoDocumento p = new TipoDocumento(
					rs.getInt("ID_TIPO_DOC"),
					rs.getString("COD_TIPO_DOC"));
			listaTipoDocumento.add(p);
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
		
		
		return listaTipoDocumento;
	}

	@Override
	public TipoDocumento infoTipoDocumento(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int borrarTipoDocumento(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
