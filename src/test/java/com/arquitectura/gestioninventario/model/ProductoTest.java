
 
package com.arquitectura.gestioninventario.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductoTest {

    @Test
    void debeCrearProductoYGuardarDatos() {

        Producto producto = new Producto();

        producto.setNombre("Laptop");
        producto.setDescripcion("Laptop Lenovo");
        producto.setPrecio(850.00);
        producto.setStock(10);

        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop Lenovo", producto.getDescripcion());
        assertEquals(850.00, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void debeModificarDatosDelProducto() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor 24 pulgadas");
        producto.setPrecio(200.00);
        producto.setStock(8);

        producto.setNombre("Monitor Samsung");
        producto.setDescripcion("Monitor Samsung 27 pulgadas");
        producto.setPrecio(300.00);
        producto.setStock(15);

        assertEquals("Monitor Samsung", producto.getNombre());
        assertEquals("Monitor Samsung 27 pulgadas", producto.getDescripcion());
        assertEquals(300.00, producto.getPrecio());
        assertEquals(15, producto.getStock());
    }

    @Test
    void debeGuardarIdDelProducto() {

        Producto producto = new Producto();

        producto.setId(1L);

        assertEquals(1L, producto.getId());
    }
}
