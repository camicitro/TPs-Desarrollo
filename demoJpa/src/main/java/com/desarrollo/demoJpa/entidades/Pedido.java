package com.desarrollo.demoJpa.entidades;

import com.desarrollo.demoJpa.enums.EstadoPedido;
import com.desarrollo.demoJpa.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Pedido")
public class Pedido extends BaseEntidad{

    private Date fecha;
    private EstadoPedido estadoPedido;
    private TipoEnvio tipoEnvio;
    private double total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="factura_id")
    private Factura factura;

    public void agregarDetallePedido(DetallePedido detalle){

        detalles.add(detalle);
    }
}
