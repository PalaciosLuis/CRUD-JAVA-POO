package interfaces;
import entidades.Cliente;

public interface AuthInterface {
	public Cliente VerificarInicioSesion(String correo,String clave);

}
