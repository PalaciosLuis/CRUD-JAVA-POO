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
import entidades.Producto;
import interfaces.InterfaceCliente;
import interfaces.InterfaceCompra;
import interfaces.InterfaceProducto;
import model.ModelCompra;

/**
 * Servlet implementation class ServletCompra
 */
@WebServlet("/ServletCompra")
public class ServletCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "PreProducto":
			PreregistrarCompraProducto(request,response);break;
		case "Registrar":
			registrarCompra(request,response);break;
		case "Actualizar":
			actualizarCompra(request,response);break;
		case "Listar":
			listarCompras(request,response);break;
		case "Trash":
			trashCompras(request,response);break;
		case "ListarTop":
			listarTopCompras(request,response);break;
		case "ListarCliente":
			listarComprasCliente(request,response);break;
		case "Borrar":
			borrarCompra(request,response);break;
		case "Buscar":
			buscarCompras(request,response);break;
		case "Info":
			infoCompra(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrio un error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}


	private void trashCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ModelCompra ModelCompra = new ModelCompra();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();

		List<Compra> data = dao.trashCompras();
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Compras borradas");
		}else request.setAttribute("titulo", "No hay compras borradas");
		request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
		
	}

	private void buscarCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inicio = request.getParameter("dateInicio");
		if (inicio=="") inicio="1900-01-01"; 
		
		String fin = request.getParameter("dateFinal");
		if (fin=="") fin="9999-01-01";
		//ModelCompra ModelCompra = new ModelCompra();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();
		

		List<Compra> data = dao.buscarCompras(inicio, fin);
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Busqueda de Compras");
			request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
		
	}

	private void listarTopCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ModelCompra ModelCompra = new ModelCompra();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();

		List<Compra> data = dao.listarTopCompras();
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Ultimas Compras");
			request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
			request.setAttribute("mensaje", "Ocurrio un problema.");
		}
		
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
			request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
		
	}

	private void PreregistrarCompraProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		//ModelConsola dao = new ModelProducto();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		InterfaceProducto dao = daoFactory.getProducto();
		Producto data = dao.infoProducto(id);

		InterfaceCliente daoc = daoFactory.getCliente();
		List<Cliente> listaClientes = daoc.listarClientes();
		
		
		if(data != null && !listaClientes.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("dataClientes", listaClientes);
			request.getRequestDispatcher("registrarCompraProducto.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
	}

	private void actualizarCompra(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
		request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		
	}

	private void borrarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ModelCompra ModelCompra = new ModelCompra();
		int value = ModelCompra.borrarCompra(id);
		
		if(value==1) {
			listarCompras(request, response);
		}
		else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
	}

	protected void infoCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		//ModelCompra dao = new ModelCompra();

		InterfaceCompra dao = daoFactory.getCompra();
		Compra data = dao.infoCompra(id);
		
		InterfaceProducto daoP = daoFactory.getProducto();
		List<Producto> listaProductos = daoP.listarProductos();

		InterfaceCliente daoc = daoFactory.getCliente();
		List<Cliente> listaClientes = daoc.listarClientes();

		
		if(data != null && !listaProductos.isEmpty() && !listaClientes.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("productos", listaProductos);
			request.setAttribute("clientes", listaClientes);
			request.getRequestDispatcher("actualizarCompra.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ocurrio un problema.");
			request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);
		}
	}

	protected void listarCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//ModelCompra ModelCompra = new ModelCompra();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		InterfaceCompra dao = daoFactory.getCompra();

		List<Compra> data = dao.listarCompras();
		
		if(!data.isEmpty()) {
			request.setAttribute("data", data);
			request.setAttribute("titulo", "Lista de Compras");
			request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
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

