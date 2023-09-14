package com.desarrollo.demoJpa.repositorios;

import com.desarrollo.demoJpa.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{

}
