package com.desarrollo.demoJpa.entidades;

import com.desarrollo.demoJpa.enums.FormaPago;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Factura")
public class Factura extends BaseEntidad {
    private int numero;
    private Date fecha;
    private double descuento;

    private FormaPago formaPago;

    private int total;

}
