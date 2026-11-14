/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arquitectura.gestioninventario.repository;

import com.arquitectura.gestioninventario.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto save(Producto producto);

    public List<Producto> findAll();

    public void deleteAll();

    public void deleteById(Long id);
}