package com.desarrollo.demoJpa;

import com.desarrollo.demoJpa.entidades.*;
import com.desarrollo.demoJpa.enums.EstadoPedido;
import com.desarrollo.demoJpa.enums.FormaPago;
import com.desarrollo.demoJpa.enums.TipoEnvio;
import com.desarrollo.demoJpa.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.*;
import java.util.Date;
import java.util.GregorianCalendar;
//import org.springframework.context.annotation.ConfigurableApplicationContext;
import jakarta.annotation.PostConstruct;
import com.desarrollo.demoJpa.repositorios.*;
import java.time.LocalDate;
import java.time.Month;


@SpringBootApplication
public class DemoJpaApplication {

	@Autowired
	 RubroRepository rubroRepository; //inyecta el repositorio
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	DomicilioRepository domicilioRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);

	}

	@Bean
	public CommandLineRunner initRubroProd(RubroRepository rubroRepository, ClienteRepository clienteRepository){
		return args -> {
			//codigo a ejecutar despues de la inicializacion de la aplicacion
			System.out.println("La aplicación se ha iniciado. Realizando tareas iniciales...");


			/*uso del metodo builder de lombok*/
			/* crear productos*/
			Producto producto1 = Producto.builder()
					.denominacionProducto("Coca Cola")
					.precioCompra(1000)
					.precioVenta(1500)
					.stockMinimo(500)
					.stockAnual(3800)
					.unidadMedida("mL")
					.build();

			Producto producto2 = Producto.builder()
					.denominacionProducto("Fanta Naranja")
					.precioCompra(1000)
					.precioVenta(1500)
					.stockMinimo(500)
					.stockAnual(3200)
					.unidadMedida("mL")
					.build();

			Producto producto3 = Producto.builder()
					.denominacionProducto("Limonada Casera")
					.precioCompra(1000)
					.precioVenta(1700)
					.stockAnual(1200)
					.stockMinimo(300)
					.unidadMedida("mL")
					.receta("Exprimir 3 limones, agregar: 700mL de agua, 200g de azúcar, 5 hojas de menta y 1 cucharada de ralladura de jengibre")
					.tiempoEstimadoCocina(15)
					.build();

			Producto producto4 = Producto.builder()
					.denominacionProducto("Hamburguesa simple")
					.precioCompra(1500)
					.precioVenta(3200)
					.stockAnual(4000)
					.unidadMedida("gr")
					.receta("Colocar un pan, la hamburguesa, una rodaja de queso chedar, dos tomates y una hoja de lechuga, cerrar con el otro pan")
					.tiempoEstimadoCocina(30)
					.build();

			/* crear rubros*/
			Rubro rubro1 = Rubro.builder()
					.denominacion("Bebidas")
					.build();

			Rubro rubro2 = Rubro.builder()
					.denominacion("Hamburguesas")
							.build();



			/*agregar productos a los rubros*/
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			rubro1.agregarProducto(producto3);
			rubro2.agregarProducto(producto4);

			/* crear pedidos y sus detalles y facturas*/
			LocalDate localDate1 = LocalDate.of(2023, Month.SEPTEMBER,05);
			Date date1 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			//detalles del pedido
			DetallePedido detalle1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(6400)
					.build();
			DetallePedido detalle2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(1700)
					.build();
			//pedido
			Pedido pedido1 = Pedido.builder()
					.estadoPedido(EstadoPedido.INICIADO)
					.fecha(date1)
					.total(8100)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.build();
			//factura del pedido
			Factura factura1 = Factura.builder()
					.numero(1)
					.fecha(date1)
					.descuento(0)
					.formaPago(FormaPago.EFECTIVO)
					.total(8100)
					.build();

			/*agregar detalles de pedido al pedido*/
			pedido1.agregarDetallePedido(detalle1);
			pedido1.agregarDetallePedido(detalle2);


			//guardar el objeto Rubro en la BD
			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);


			/* crear domicilios*/
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Coronel Rodriguez")
					.localidad("Ciudad")
					.numero("872")
					.build();
			/* crear clientes*/
			Cliente cliente1 = Cliente.builder()
					.nombre("Camila")
					.apellido("Citro")
					.email("camilacitro1106@gmail.com")
					.telefono("261211496")
					.build();

			/*agrego domicilios al cliente*/
			cliente1.agregarDomicilio(domicilio1);

			/*agregar pedidos al cliente*/
			cliente1.agregarPedido(pedido1);

			//guardar cliente
			clienteRepository.save(cliente1);

			/* ************************************
			LO QUE SAQUE PQ SINO NO SE EJECUTABA
			*************************************** */
			/*
			* domicilioRepository.save(domicilio1);
			*
			* detallePedidoRepository.save(detalle1);
			* detallePedidoRepository.save(detalle2);
			*
			* facturaRepository.save(factura1);
			* pedido1.setFactura(factura1);
			*
			* pedidoRepository.save(pedido1);
			*
			* detalle1.setProducto(producto4);
			* detalle2.setProducto(producto3);
			*
			* */


			//recuperar el objeto Rubro1 desde la Bd
			Rubro rubroBebidas = rubroRepository.findById(rubro1.getId()).orElse(null);

			if (rubroBebidas != null){
				System.out.println("Denominacion: " + rubroBebidas.getDenominacion());

			}

			//recuperar el objeto Rubro2 desde la Bd
			Rubro rubroHamburguesas = rubroRepository.findById(rubro2.getId()).orElse(null);

			if (rubroBebidas != null){
				System.out.println("Denominacion: " + rubroHamburguesas.getDenominacion());
			}


		};
	}
}
