package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Cliente;
import entidades.Compra;
import entidades.Consola;
import entidades.Producto;
import interfaces.InterfaceCliente;
import interfaces.InterfaceCompra;
import interfaces.InterfaceConsola;
import interfaces.InterfaceProducto;

/**
 * Servlet implementation class ServletConsultasCliente
 */
@WebServlet("/ServletConsultasCliente")
public class ServletConsultasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultasCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "Listar":
			indexProductos(request,response);break;
		case "ListarTop":
			indexProductosTop(request,response);break;
		case "Buscar":
			buscarProducto(request,response);break;
		case "Consola":
			productosConsola(request,response);break;
		case "PreCompra":
			preCompra(request,response); break;
		case "Compra":
			registrarCompra(request,response); break;
		case "ListarConsolas":
			listarConsolas(request,response); break;
		case "misCompras":
			listarComprasCliente(request,response); break;
		default:
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
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
			request.getRequestDispatcher("indexConsolas.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("indexProductos.jsp").forward(request, response);
		}
	}
    
    private void registrarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        //Preparo el dao
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();
		
		//Recolecto datos
		String idCliente = request.getParameter("idCliente");
		String idProducto = request.getParameter("idProducto");
		String fecha =  LocalDate.now().toString();
		int cantidad = Integer.parseInt(request.getParameter("numCantidad"));
		
		//Mando la info
		Compra c = new Compra(idCliente, idProducto, fecha, cantidad);
		int value = dao.registrarCompra(c);

		if(value==1) {
			request.setAttribute("mensaje", "Compra Registrada.");
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema. No se registró.");
		}
		//listarComprasCliente(request, response);
		indexProductos(request, response);
	}

    
    private void preCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
String id = request.getParameter("id");
		
		//ModelConsola dao = new ModelProducto();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		InterfaceProducto dao = daoFactory.getProducto();
		Producto data = dao.infoProducto(id);

		if(data != null) {
			request.setAttribute("data", data);
			request.getRequestDispatcher("registrarCompraCliente.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexProductos.jsp").forward(request,response);
		}
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
			request.setAttribute("titulo", "Busqueda de Juegos");
			request.setAttribute("ListaConsola",consolas);
		}
		else request.setAttribute("titulo", "No hay juegos con ese nombre");
		request.getRequestDispatcher("indexProductos.jsp").forward(request, response);
		
	}

	private void listarComprasCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//ModelCompra ModelCompra = new ModelCompra();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();
		InterfaceCliente daoc = daoFactory.getCliente();

		List<Compra> data = dao.listarComprasCliente(id);
		Cliente cliente = daoc.infoCliente(id);
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Compras de "+cliente.getNombre());
			request.getRequestDispatcher("listarComprasCliente.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}
	
	public void indexProductosTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceProducto dao = daoFactory.getProducto();
		//ModelProducto dao = new ModelProducto();
		InterfaceConsola daoc = daoFactory.getConsola();
		List<Consola> consolas = daoc.listarConsolas();
		
		List<Producto> data = dao.listarTopProductos();
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("ListaConsola",consolas);
			request.setAttribute("titulo", "Top 10 Juegos Mas Vendidos");
			request.getRequestDispatcher("indexProductos.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
		}
		
	}

	
	protected void indexProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			request.getRequestDispatcher("indexProductos.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
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
			request.setAttribute("titulo", "Lista de Juegos de "+data.get(0).getConsola());
			request.getRequestDispatcher("indexProductos.jsp").forward(request, response);
		}else {
			indexProductos(request, response);
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
