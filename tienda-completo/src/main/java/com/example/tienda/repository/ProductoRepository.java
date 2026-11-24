package com.example.tienda.repository;

import com.example.tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring genera la implementación (usa EntityManager por debajo):
 * save, findAll, findById, deleteById, existsById...
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
