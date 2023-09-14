package com.desarrollo.demoJpa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="Cliente")
public class Cliente extends BaseEntidad{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();


    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    public void agregarDomicilio(Domicilio domicilio){
        domicilios.add(domicilio);
    }

    public void mostrarPedido(){
        for (Pedido pedido: pedidos){
            System.out.println("Fecha: " + pedido.getFecha() + ", total: " + pedido.getTotal());
            int contador = 0;
            for (DetallePedido detalle: pedido.getDetalles()){
                contador = contador + 1;
                System.out.println("Producto: " + contador + ": " + detalle.getProducto().getDenominacionProducto() + "cantidad: " + detalle.getCantidad() + "subtotal: " + detalle.getSubtotal());
            }
        }
    }

    public void mostrarDomicilio(){
        System.out.println("Los domicilios son: ");
        for(Domicilio domicilio: domicilios){
            System.out.println("Calle: " + domicilio.getCalle() + "Numero: " + domicilio.getNumero());
        }
    }

}
