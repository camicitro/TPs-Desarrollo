package com.desarrollo.demoJpa.entidades;

import com.desarrollo.demoJpa.enums.TipoProducto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Producto")
public class Producto extends BaseEntidad{

    private enum tipo{
        MANUFACTURADO, INSUMO
    }
    private int tiempoEstimadoCocina;
    private String denominacionProducto;
    private double precioVenta;
    private double precioCompra;
    private int stockAnual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;
    private TipoProducto tipoProducto;
}
