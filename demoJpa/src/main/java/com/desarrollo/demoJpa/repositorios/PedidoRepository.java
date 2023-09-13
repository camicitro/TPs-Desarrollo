package com.desarrollo.demoJpa.repositorios;

import com.desarrollo.demoJpa.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}
