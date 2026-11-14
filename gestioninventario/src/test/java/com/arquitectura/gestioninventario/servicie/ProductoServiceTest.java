package com.arquitectura.gestioninventario.service;

import com.arquitectura.gestioninventario.model.Producto;
import com.arquitectura.gestioninventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void limpiarBaseDeDatos() {
        productoRepository.deleteAll();
    }

    // ============================================================
    // 1. REGISTRAR PRODUCTO
    // ============================================================

    @Test
    void debeRegistrarProducto() {

        Producto producto = new Producto();

        producto.setNombre("Laptop");
        producto.setDescripcion("Laptop para oficina");
        producto.setPrecio(800.00);
        producto.setStock(10);

        Producto registrado =
                productoService.registrarProducto(producto);

        assertNotNull(registrado);
        assertNotNull(registrado.getId());
        assertEquals("Laptop", registrado.getNombre());
        assertEquals("Laptop para oficina", registrado.getDescripcion());
        assertEquals(800.00, registrado.getPrecio());
        assertEquals(10, registrado.getStock());
    }

    // ============================================================
    // 2. NO PERMITIR PRODUCTO SIN NOMBRE
    // ============================================================

    @Test
    void noDebeRegistrarProductoSinNombre() {

        Producto producto = new Producto();

        producto.setNombre("");
        producto.setDescripcion("Producto sin nombre");
        producto.setPrecio(100.00);
        producto.setStock(5);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto)
        );
    }

    // ============================================================
    // 3. NO PERMITIR PRECIO NEGATIVO
    // ============================================================

    @Test
    void noDebeRegistrarProductoConPrecioNegativo() {

        Producto producto = new Producto();

        producto.setNombre("Teclado");
        producto.setDescripcion("Teclado USB");
        producto.setPrecio(-10.00);
        producto.setStock(5);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto)
        );
    }

    // ============================================================
    // 4. NO PERMITIR STOCK NEGATIVO
    // ============================================================

    @Test
    void noDebeRegistrarProductoConStockNegativo() {

        Producto producto = new Producto();

        producto.setNombre("Mouse");
        producto.setDescripcion("Mouse inalámbrico");
        producto.setPrecio(25.00);
        producto.setStock(-5);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.registrarProducto(producto)
        );
    }

    // ============================================================
    // 5. LISTAR PRODUCTOS
    // ============================================================

    @Test
    void debeListarProductos() {

        Producto producto1 = new Producto();

        producto1.setNombre("Laptop");
        producto1.setDescripcion("Laptop para oficina");
        producto1.setPrecio(800.00);
        producto1.setStock(10);

        Producto producto2 = new Producto();

        producto2.setNombre("Mouse");
        producto2.setDescripcion("Mouse inalámbrico");
        producto2.setPrecio(25.00);
        producto2.setStock(20);

        productoService.registrarProducto(producto1);
        productoService.registrarProducto(producto2);

        List<Producto> productos =
                productoService.listarProductos();

        assertEquals(2, productos.size());
        assertEquals("Laptop", productos.get(0).getNombre());
        assertEquals("Mouse", productos.get(1).getNombre());
    }

    // ============================================================
    // 6. BUSCAR PRODUCTO POR ID
    // ============================================================

    @Test
    void debeBuscarProductoPorId() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor 24 pulgadas");
        producto.setPrecio(200.00);
        producto.setStock(8);

        Producto registrado =
                productoService.registrarProducto(producto);

        Producto encontrado =
                productoService.buscarProductoPorId(registrado.getId());

        assertNotNull(encontrado);
        assertEquals("Monitor", encontrado.getNombre());
        assertEquals("Monitor 24 pulgadas",
                encontrado.getDescripcion());
        assertEquals(200.00, encontrado.getPrecio());
        assertEquals(8, encontrado.getStock());
    }

    // ============================================================
    // 7. BUSCAR PRODUCTO QUE NO EXISTE
    // ============================================================

    @Test
    void debeRetornarNullCuandoProductoNoExiste() {

        Producto encontrado =
                productoService.buscarProductoPorId(999999L);

        assertNull(encontrado);
    }

    // ============================================================
    // 8. NO BUSCAR PRODUCTO CON ID NULL
    // ============================================================

    @Test
    void noDebeBuscarProductoConIdNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.buscarProductoPorId(null)
        );
    }

    // ============================================================
    // 9. MODIFICAR PRODUCTO
    // ============================================================

    @Test
    void debeModificarProducto() {

        Producto producto = new Producto();

        producto.setNombre("Laptop");
        producto.setDescripcion("Laptop básica");
        producto.setPrecio(700.00);
        producto.setStock(5);

        Producto registrado =
                productoService.registrarProducto(producto);

        registrado.setNombre("Laptop Gamer");
        registrado.setDescripcion("Laptop gamer actualizada");
        registrado.setPrecio(1200.00);
        registrado.setStock(8);

        Producto modificado =
                productoService.modificarProducto(registrado);

        assertNotNull(modificado);
        assertEquals("Laptop Gamer",
                modificado.getNombre());
        assertEquals("Laptop gamer actualizada",
                modificado.getDescripcion());
        assertEquals(1200.00,
                modificado.getPrecio());
        assertEquals(8,
                modificado.getStock());
    }

    // ============================================================
    // 10. MODIFICAR SIN ID
    // ============================================================

    @Test
    void noDebeModificarProductoSinId() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor");
        producto.setPrecio(200.00);
        producto.setStock(5);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.modificarProducto(producto)
        );
    }

    // ============================================================
    // 11. MODIFICAR SIN NOMBRE
    // ============================================================

    @Test
    void noDebeModificarProductoSinNombre() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor");
        producto.setPrecio(200.00);
        producto.setStock(5);

        Producto registrado =
                productoService.registrarProducto(producto);

        registrado.setNombre("");

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.modificarProducto(registrado)
        );
    }

    // ============================================================
    // 12. MODIFICAR CON PRECIO NEGATIVO
    // ============================================================

    @Test
    void noDebeModificarProductoConPrecioNegativo() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor");
        producto.setPrecio(200.00);
        producto.setStock(5);

        Producto registrado =
                productoService.registrarProducto(producto);

        registrado.setPrecio(-100.00);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.modificarProducto(registrado)
        );
    }

    // ============================================================
    // 13. MODIFICAR CON STOCK NEGATIVO
    // ============================================================

    @Test
    void noDebeModificarProductoConStockNegativo() {

        Producto producto = new Producto();

        producto.setNombre("Monitor");
        producto.setDescripcion("Monitor");
        producto.setPrecio(200.00);
        producto.setStock(5);

        Producto registrado =
                productoService.registrarProducto(producto);

        registrado.setStock(-10);

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.modificarProducto(registrado)
        );
    }

    // ============================================================
    // 14. ELIMINAR PRODUCTO
    // ============================================================

    @Test
    void debeEliminarProducto() {

        Producto producto = new Producto();

        producto.setNombre("Impresora");
        producto.setDescripcion("Impresora multifunción");
        producto.setPrecio(300.00);
        producto.setStock(4);

        Producto registrado =
                productoService.registrarProducto(producto);

        Long id = registrado.getId();

        productoService.eliminarProducto(id);

        Producto eliminado =
                productoService.buscarProductoPorId(id);

        assertNull(eliminado);
    }

    // ============================================================
    // 15. NO ELIMINAR CON ID NULL
    // ============================================================

    @Test
    void noDebeEliminarProductoConIdNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> productoService.eliminarProducto(null)
        );
    }
    @Test
void noDebeRegistrarProductoConStockMayorA1000() {

    Producto producto = new Producto();
    producto.setNombre("Producto prueba");
    producto.setPrecio(50.0);
    producto.setStock(1001);

    assertThrows(
        IllegalArgumentException.class,
        () -> productoService.registrarProducto(producto)
    );
}
}