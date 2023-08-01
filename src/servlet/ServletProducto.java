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
import interfaces.InterfaceProducto;
import interfaces.InterfaceConsola;
import model.ModelConsola;
import model.ModelProducto;


/**
 * Servlet implementation class ServletProducto
 */
@WebServlet("/ServletProducto")
public class ServletProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "Pre":
			PreRegistrarProducto(request,response);break;
		case "Registrar":
			registrarProducto(request,response);break;
		case "Actualizar":
			actualizarProducto(request,response);break;
		case "Listar":
			listarProductos(request,response);break;
		case "Trash":
			trashProductos(request,response);break;
		case "ListarTop":
			listarProductosTop(request,response);break;
		case "Borrar":
			borrarProducto(request,response);break;
		case "Buscar":
			buscarProducto(request,response);break;
		case "Info":
			infoProducto(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	private void trashProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		
		List<Producto> data = dao.trashProductos();
		request.setAttribute("data", data);
		if(!data.isEmpty()) {
			request.setAttribute("titulo", "Productos borrados");
		}else request.setAttribute("titulo", "No hay productos borrados");
		request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		
	}

	private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String consulta = request.getParameter("txtConsulta");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		
		InterfaceConsola daoc = daoFactory.getConsola();
		List<Consola> consolas = daoc.listarConsolas();
		//ModelCliente dao = new ModelCliente();
		List<Producto> data = dao.buscarProducto(consulta);
		
		if(!data.isEmpty()) { 
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Busqueda de Productos");
			request.setAttribute("ListaConsola",consolas);
		}
		else request.setAttribute("titulo", "No hay productos con ese nombre");
		request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		
	}

	private void listarProductosTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		InterfaceConsola daoc = daoFactory.getConsola();
		List<Consola> consolas = daoc.listarConsolas();
		
		List<Producto> data = dao.listarTopProductos();
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("ListaConsola",consolas);
			request.setAttribute("titulo", "Top 10 Productos Mas Vendidos");
			request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
		}
		
	}

	private void PreRegistrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceConsola dao = daoFactory.getConsola();
		List<Consola> consolas = dao.listarConsolas();
		
		if (!consolas.isEmpty()) {
		request.setAttribute("ListaConsola",consolas);
		request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
		
	}

	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		
		int id = Integer.parseInt(request.getParameter("codProducto"));
		String nombre = request.getParameter("txtNombreV");
		String descripcion = request.getParameter("txtDescripcion");
		double precio = Double.parseDouble(request.getParameter("numPrecio"));
		int stock = Integer.parseInt(request.getParameter("numStock"));
		String consola = request.getParameter("codConsola");
		
		Producto p = new Producto(id, nombre, descripcion, consola, stock, precio);
		int value = dao.actualizarProducto(p);
		
		if(value==1) {
			request.setAttribute("mensaje", "Producto actualizado!");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se actualizó.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		
	}

	private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		
		String nombre = request.getParameter("txtNombreV");
		String descripcion = request.getParameter("txtDescripcion");
		double precio = Double.parseDouble(request.getParameter("numPrecio"));
		int stock = Integer.parseInt(request.getParameter("numCantidad"));
		String consola = request.getParameter("codConsola");
		
		Producto p = new Producto(nombre, descripcion, consola, stock, precio);
		int value = dao.registrarProducto(p);
		
		if(value==1) {
			request.setAttribute("mensaje", "Producto Registrado.");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se registró.");
		}
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
	}

	private void borrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ModelProducto ModelProducto = new ModelProducto();
		int value = ModelProducto.borrarProducto(id);
		
		if(value==1) {
			listarProductos(request, response);
		}
		else {
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
		}
	}

	protected void infoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ModelProducto ModelProducto = new ModelProducto();
		Producto Producto = ModelProducto.infoProducto(id);
		
		ModelConsola modelConsola = new ModelConsola();
		List<Consola> listaConsolas = modelConsola.listarConsolas();
		
		if(Producto!=null && !listaConsolas.isEmpty()) {
		try {
			request.setAttribute("ProductoData",Producto);
			request.setAttribute("ListaConsola",listaConsolas);
			request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			request.getRequestDispatcher("registrarProductos.jsp").forward(request,response);
		}
	}

	protected void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		InterfaceConsola daoc = daoFactory.getConsola();
		List<Consola> consolas = daoc.listarConsolas();
		
		List<Producto> data = dao.listarProductos();
		request.setAttribute("data", data);
		if(!data.isEmpty()) {
			request.setAttribute("titulo", "Lista de Productos");
			request.setAttribute("ListaConsola",consolas);
			request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
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
