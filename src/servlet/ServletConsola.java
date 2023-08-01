package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Consola;
import entidades.Producto;
import interfaces.InterfaceConsola;


/**
 * Servlet implementation class ServletConsola
 */
@WebServlet("/ServletConsola")
public class ServletConsola extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsola() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "Registrar":
			registrarConsola(request,response);break;
		case "Actualizar":
			actualizarConsola(request,response);break;
		case "Listar":
			listarConsolas(request,response);break;
		case "Trash":
			trashConsolas(request,response);break;
		case "ListarTop":
			listarTopConsolas(request,response);break;
		case "Borrar":
			borrarConsola(request,response);break;
		case "Info":
			infoConsola(request,response);break;
		case "Productos":
			productosConsola(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}

	

	private void trashConsolas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		List<Consola> data = dao.trashConsolas();
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Consolas borradas");
		}else request.setAttribute("titulo", "No hay consolas borradas");
		request.getRequestDispatcher("listarConsolas.jsp").forward(request, response);
		
	}

	private void listarTopConsolas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		List<Consola> data = dao.listarTopConsolas();
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Top 10 Consolas con mas Ventas");
			request.getRequestDispatcher("listarConsolas.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
		
	}

	private void productosConsola(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		InterfaceConsola daoc = daoFactory.getConsola();
		List<Consola> consolas = daoc.listarConsolas();
		
		
		List<Producto> data = dao.productosConsola(id);
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("ListaConsola",consolas);
			request.setAttribute("titulo", "Lista de Productos de "+data.get(0).getConsola());
			request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
		}
		
	}

	private void actualizarConsola(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("txtNombre");
		
		Consola p = new Consola(id, nombre);
		int value = dao.actualizarConsola(p);
		
		if(value==1) {
			request.setAttribute("mensaje", "Consola actualizada.");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se actualizó.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);

		
	}

	private void registrarConsola(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		
		String nombre = request.getParameter("txtNombre");
		
		Consola p = new Consola("", nombre);
		int value = dao.registrarConsola(p);
		
		if(value==1) {
			request.setAttribute("mensaje", "Consola Registrado.");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se registró.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);

		
		
	}

	private void borrarConsola(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		int value = dao.borrarConsola(id);
		
		if(value==1) {
			listarConsolas(request, response);
		}
		else {
			request.getRequestDispatcher("registrarConsolas.jsp").forward(request,response);
		}
	}

	protected void infoConsola(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		
		Consola Consola = dao.infoConsola(id);
		
		if(Consola!=null) {
		try {
			request.setAttribute("data",Consola);
			request.getRequestDispatcher("actualizarConsola.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			request.getRequestDispatcher("registrarConsolas.jsp").forward(request,response);
		}
	}

	protected void listarConsolas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		//ModelConsola dao = new ModelConsola();
		List<Consola> data = dao.listarConsolas();
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Lista de Consolas");
			request.getRequestDispatcher("listarConsolas.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
	}

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
