/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arquitectura.gestioninventario.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void debeCrearProductoConConstructor() {

        Producto producto = new Producto(
                "Laptop",
                "Laptop Lenovo",
                850.00,
                10
        );

        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop Lenovo", producto.getDescripcion());
        assertEquals(850.00, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void debeCrearProductoConConstructorVacio() {

        Producto producto = new Producto();

        assertNotNull(producto);
        assertNull(producto.getNombre());
    }

    @Test
    void debeAsignarIdAlProducto() {

        Producto producto = new Producto();

        producto.setId(1L);

        assertEquals(1L, producto.getId());
    }

    @Test
    void debeModificarNombreDelProducto() {

        Producto producto = new Producto();

        producto.setNombre("Teclado");

        assertEquals("Teclado", producto.getNombre());
    }

    @Test
    void debeModificarDescripcionDelProducto() {

        Producto producto = new Producto();

        producto.setDescripcion("Teclado inalámbrico");

        assertEquals(
                "Teclado inalámbrico",
                producto.getDescripcion()
        );
    }

    @Test
    void debeModificarPrecioDelProducto() {

        Producto producto = new Producto();

        producto.setPrecio(45.50);

        assertEquals(45.50, producto.getPrecio());
    }

    @Test
    void debeModificarStockDelProducto() {

        Producto producto = new Producto();

        producto.setStock(25);

        assertEquals(25, producto.getStock());
    }
}
