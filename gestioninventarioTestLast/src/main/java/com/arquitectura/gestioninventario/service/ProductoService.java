package com.arquitectura.gestioninventario.service;

import com.arquitectura.gestioninventario.model.Producto;
import com.arquitectura.gestioninventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // 1. Registrar producto
    public Producto registrarProducto(Producto producto) {

        if (producto.getNombre() == null ||
            producto.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio"
            );
        }

        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo"
            );
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo"
            );
        }

        // NUEVO REQUISITO:
        // El stock no puede ser superior a 1000
        if (producto.getStock() > 1000) {
            throw new IllegalArgumentException(
                    "El stock no puede ser superior a 1000"
            );
        }

        return productoRepository.save(producto);
    }

    // 2. Listar productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // 3. Modificar producto
    public Producto modificarProducto(Producto producto) {

        if (producto.getId() == null) {
            throw new IllegalArgumentException(
                    "El producto debe tener un ID para modificarse"
            );
        }

        if (producto.getNombre() == null ||
            producto.getNombre().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio"
            );
        }

        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo"
            );
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo"
            );
        }

        return productoRepository.save(producto);
    }

    // 4. Eliminar producto
    public void eliminarProducto(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "El ID del producto es obligatorio"
            );
        }

        productoRepository.deleteById(id);
    }

    // 5. Buscar producto por ID
    public Producto buscarProductoPorId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "El ID del producto es obligatorio"
            );
        }

        Optional<Producto> producto =
                productoRepository.findById(id);

        return producto.orElse(null);
    }
}