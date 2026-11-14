
package com.arquitectura.gestioninventario.repository;

import com.arquitectura.gestioninventario.model.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Optional<Producto> findById(Long id);
}