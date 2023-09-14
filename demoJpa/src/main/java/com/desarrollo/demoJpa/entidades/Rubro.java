package com.desarrollo.demoJpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="Rubro")
public class Rubro extends BaseEntidad{

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();


    public void agregarProducto(Producto producto){

        productos.add(producto);
    }
    public void mostrarProductos(){
        System.out.println("Productos de " + denominacion + ": ");
        for (Producto producto : productos){
            System.out.println("Nombre producto: " + producto.getDenominacionProducto() + ", receta: " + producto.getReceta() + ", unidad de medida: " + producto.getUnidadMedida() + ", precio compra: " + producto.getPrecioCompra() + ", precio venta: " + producto.getPrecioVenta()+ ", stock anual: " + producto.getStockAnual() + ", stock minimo: " + producto.getStockMinimo() + ", tiempo estimado de cocina: " + producto.getTiempoEstimadoCocina());
        }
    }
}
