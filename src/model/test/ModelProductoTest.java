package model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;


import entidades.Producto;
import interfaces.InterfaceProducto;
import model.ModelProducto;

class ModelProductoTest {


	 InterfaceProducto interfaceProducto=new ModelProducto();
	
	@Test
	 public void testListarProductos() {
		List<Producto>productos=interfaceProducto.listarProductos();
		for (Producto producto:productos) {
			System.out.println("Los siuguientes productos son:" + producto.getNombreProducto());
			
		}
		
	}

}
