/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arquitectura.gestioninventario.controller;

import com.arquitectura.gestioninventario.model.Producto;
import com.arquitectura.gestioninventario.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "productos";
    }

    @GetMapping("/nuevo-producto")
    public String nuevoProducto() {
        return "nuevo-producto";
    }

    @PostMapping("/guardar-producto")
    public String guardarProducto(Producto producto) {
        productoRepository.save(producto);
        return "redirect:/";
    }
}
        