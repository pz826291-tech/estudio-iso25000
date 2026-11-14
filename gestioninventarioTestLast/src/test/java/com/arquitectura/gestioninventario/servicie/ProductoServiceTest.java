/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.arquitectura.gestioninventario.service;

import com.arquitectura.gestioninventario.model.Producto;
import com.arquitectura.gestioninventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void preparar() {
        producto = new Producto(
                "Laptop", "Laptop Lenovo", 850.00, 10
        );
        producto.setId(1L);
    }

    @Test
    void debeRegistrarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        assertNotNull(productoService.registrarProducto(producto));
        verify(productoRepository).save(producto);
    }

    @Test
    void noDebeRegistrarProductoSinNombre() {
        producto.setNombre("");

        assertThrows(IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto));
    }

    @Test
    void noDebeRegistrarProductoConNombreNulo() {
        producto.setNombre(null);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto));
    }

    @Test
    void noDebeRegistrarProductoConPrecioNegativo() {
        producto.setPrecio(-10.0);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto));
    }

    @Test
    void noDebeRegistrarProductoConStockNegativo() {
        producto.setStock(-5);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto));
    }

    @Test
    void noDebeRegistrarProductoConStockMayorA1000() {
        producto.setStock(1001);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto));
    }

    @Test
    void debeRegistrarProductoConStockIgualA1000() {
        producto.setStock(1000);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.registrarProducto(producto);

        assertEquals(1000, resultado.getStock());
    }

    @Test
    void debeListarProductos() {
        when(productoRepository.findAll())
                .thenReturn(Arrays.asList(producto));

        assertEquals(1, productoService.listarProductos().size());
    }

    @Test
    void debeListarProductosCuandoNoHayRegistros() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList());

        assertTrue(productoService.listarProductos().isEmpty());
    }

    @Test
    void debeModificarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.modificarProducto(producto);

        assertNotNull(resultado);
        verify(productoRepository).save(producto);
    }

    @Test
    void noDebeModificarProductoSinId() {
        producto.setId(null);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.modificarProducto(producto));
    }

    @Test
    void noDebeModificarProductoSinNombre() {
        producto.setNombre("");

        assertThrows(IllegalArgumentException.class,
                () -> productoService.modificarProducto(producto));
    }

    @Test
    void noDebeModificarProductoConPrecioNegativo() {
        producto.setPrecio(-5.0);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.modificarProducto(producto));
    }

    @Test
    void noDebeModificarProductoConStockNegativo() {
        producto.setStock(-1);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.modificarProducto(producto));
    }

    @Test
    void debeEliminarProducto() {
        productoService.eliminarProducto(1L);

        verify(productoRepository).deleteById(1L);
    }

    @Test
    void noDebeEliminarProductoConIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> productoService.eliminarProducto(null));
    }

    @Test
    void debeBuscarProductoPorId() {
        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Producto resultado = productoService.buscarProductoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void debeRetornarNuloCuandoNoEncuentraProducto() {
        when(productoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertNull(productoService.buscarProductoPorId(99L));
    }

    @Test
    void noDebeBuscarProductoConIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> productoService.buscarProductoPorId(null));
    }
}

