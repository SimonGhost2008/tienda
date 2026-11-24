package com.example.tienda.controller;

import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // GET /api/productos
    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

    // GET /api/productos/1
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/productos
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        producto.setId(null); // fuerza a que sea un INSERT nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(producto));
    }

    // PUT /api/productos/1
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto datos) {
        return repository.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setDescripcion(datos.getDescripcion());
            p.setPrecio(datos.getPrecio());
            p.setStock(datos.getStock());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/productos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
