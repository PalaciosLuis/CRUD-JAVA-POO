package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Cliente;
import entidades.TipoDocumento;
import interfaces.AuthInterface;
import interfaces.InterfaceCliente;
import interfaces.InterfaceTipoDocumento;
import model.Constantes;
import model.ModelCliente;
import model.SessionProject;


/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "Pre":
			PreRegistrarCliente(request,response);break;
		case "Registrar":
			registrarCliente(request,response);break;
		case "Actualizar":
			actualizarCliente(request,response);break;
		case "Listar":
			listarClientes(request,response);break;
		case "ListarTop":
			listarTopClientes(request,response);break;
		case "Buscar":
			buscarClientes(request,response);break;
		case "Borrar":
			borrarCliente(request,response);break;
		case "Info":
			infoCliente(request,response);break;
		case "Trash":
			trashCliente(request,response);break;
		case "Ingresar":
			ValidarCliente(request, response);break;
		
		default:
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}

	
	//==========================================
	
	protected void ValidarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type=request.getParameter("tipo");
				
				if (type.equals("Ingresar")) {
					String correo=request.getParameter("txtCorreo");
					String clave=request.getParameter("txtPass");
					
					DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					AuthInterface dao=daoFactory.getAuth();
					
					Cliente cliente = dao.VerificarInicioSesion(correo, clave);
					
					if (cliente!=null) {
						
						SessionProject sessionProject=new SessionProject();
						sessionProject.saveSessionTimeOut(request,300);
						
						sessionProject.saveSessionString(request, Constantes.ID,Integer.toString(cliente.getIdCliente()));
						sessionProject.saveSessionString(request, Constantes.NAME,cliente.getNombre());
						sessionProject.saveSessionString(request, Constantes.DOC,cliente.getDocumento());
						sessionProject.saveSessionString(request, Constantes.EMAIL,cliente.getEmail());
						sessionProject.saveSessionString(request, Constantes.DIREC,cliente.getDireccion());
						//response.sendRedirect("indexAdmin.jsp");
						ServletConsultasCliente sp = new ServletConsultasCliente();
						sp.indexProductosTop(request, response);
						
						
					}else if (correo.equals("admin@admin.com") && clave.equals("admin")) {
						SessionProject sessionProject=new SessionProject();
						sessionProject.saveSessionTimeOut(request,30);
						response.sendRedirect("indexAdmin.jsp");
						
					}else {
						request.setAttribute("mensaje", "Error de usuario");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}else if (type.equals("logout")) {
					SessionProject sessionProject=new SessionProject();
					sessionProject.invalidateSession(request);
					response.sendRedirect("index.jsp");
				}
			}
	
	//==========================================
	private void trashCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		//ModelCliente dao = new ModelCliente();
		List<Cliente> data = dao.trashClientes();
		
		if(!data.isEmpty()) { 
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Clientes borrados");
		}
		else request.setAttribute("titulo", "No hay clientes borrados");
		request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
		
		
	}

	private void buscarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String consulta = request.getParameter("txtConsulta");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		//ModelCliente dao = new ModelCliente();
		List<Cliente> data = dao.buscarClientes(consulta);
		
		if(!data.isEmpty()) { 
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Busqueda de Clientes");
			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
		
	}

	private void listarTopClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		//ModelCliente dao = new ModelCliente();
		List<Cliente> data = dao.listarTopClientes();
		
		if(!data.isEmpty()) { 
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Top 10 Clientes con mas Compras");
			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
			}
		
	}

	private void PreRegistrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceTipoDocumento dao = daoFactory.getTipoDocumento();
		List<TipoDocumento> docs = dao.listarTipoDocumentos();
		
		if (!docs.isEmpty()) {
		request.setAttribute("docs",docs);
		request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
	}

	private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		String nombre = request.getParameter("txtNombre");
		String tipodocumento = request.getParameter("txtTipoDocumento");
		String documento = request.getParameter("numDocumento");
		String telefono  = request.getParameter("txtTelefono");
		String email =  request.getParameter("txtEmail");
		String direccion =  request.getParameter("txtDireccion");
		
		Cliente c = new Cliente(nombre, tipodocumento, documento, telefono, email, direccion);
		
		int value = dao.registrarCliente(c);
		if(value==1) {
			request.setAttribute("mensaje", "Cliente Registrado!");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se registró.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		
	}

	private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		int id = Integer.parseInt(request.getParameter("codCliente"));
		String nombre = request.getParameter("txtNombre");
		String tipodocumento = request.getParameter("txtTipoDocumento");
		String documento = request.getParameter("numDocumento");
		String telefono  = request.getParameter("txtTelefono");
		String email =  request.getParameter("txtEmail");
		String direccion =  request.getParameter("txtDireccion");
		
		Cliente c = new Cliente(nombre, tipodocumento, documento, telefono, email, direccion);
		c.setIdCliente(id);
		
		int value = dao.actualizarCliente(c);
		if(value==1) {
			request.setAttribute("mensaje", "Cliente Actualizado!");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se actualizó.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		
	}
	
	

	private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ModelCliente ModelCliente = new ModelCliente();
		int value = ModelCliente.borrarCliente(id);
		
		if(value==1) {
			listarClientes(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se borró.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
	}
	
	

	protected void infoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");

		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente daoC = daoFactory.getCliente();
		InterfaceTipoDocumento daoT = daoFactory.getTipoDocumento();
		
		//ModelCliente ModelCliente = new ModelCliente();
		Cliente Cliente = daoC.infoCliente(id);
		List<TipoDocumento> docs = daoT.listarTipoDocumentos();
		
		
		if(Cliente!=null && !docs.isEmpty()) {
		try {
			request.setAttribute("ClienteData",Cliente);
			request.setAttribute("docs",docs);
			request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema :(");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
	}

	protected void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCliente dao = daoFactory.getCliente();
		
		//ModelCliente dao = new ModelCliente();
		List<Cliente> data = dao.listarClientes();
		
		if(!data.isEmpty()) { 
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Lista de Clientes");
			request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
			}
	}
	
	//=============================================================================



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
