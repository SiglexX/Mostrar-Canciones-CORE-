package com.jossemorales.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jossemorales.modelos.Cancion;
import com.jossemorales.servicios.ServicioCanciones;

@Controller
public class ControladorCanciones {
	
	@Autowired
	private final ServicioCanciones servicioCanciones;
	
	public ControladorCanciones(ServicioCanciones servicioCanciones) {
		this.servicioCanciones = servicioCanciones;
	}
	
	@GetMapping("/canciones")
	public String desplegarCanciones(Model modelo) {
		List<Cancion> canciones = this.servicioCanciones.obtenerTodasLasCanciones();
		modelo.addAttribute("canciones", canciones);
		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/detalle/{idCancion}")
	public String desplegarDetalleCancion(@PathVariable Long idCancion, Model modelo) {
		Cancion cancion = servicioCanciones.obtenerCancionPorId(idCancion);
		
		if (cancion != null) {
			modelo.addAttribute("cancion", cancion);
			return "detalleCancion.jsp";
		} else {
			modelo.addAttribute("mensaje", "Canción no encontrada");
			return "detalleCancion.jsp";
		}
	}
}
