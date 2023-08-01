package interfaces;

import java.util.List;

import entidades.TipoDocumento;

public interface InterfaceTipoDocumento {
	public int registrarTipoDocumento(TipoDocumento c);
	public int actualizarTipoDocumento(TipoDocumento c);
	public List<TipoDocumento> listarTipoDocumentos();
	public TipoDocumento infoTipoDocumento(String id);
	public int borrarTipoDocumento(String id);
}
