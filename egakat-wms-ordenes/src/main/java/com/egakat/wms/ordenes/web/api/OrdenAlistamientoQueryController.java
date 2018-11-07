package com.egakat.wms.ordenes.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.wms.ordenes.constants.RestConstants;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentDto;
import com.egakat.wms.ordenes.service.suscripciones.api.OrdenAlistamientoSuscripcionService;

import lombok.val;

@RestController
@RequestMapping(value = RestConstants.ordenes_alistamiento, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdenAlistamientoQueryController {

	@Autowired
	private OrdenAlistamientoSuscripcionService service;

	protected OrdenAlistamientoSuscripcionService getService() {
		return service;
	}

	@GetMapping(value = RestConstants.ordenes_alistamiento_suscripciones)
	public ResponseEntity<List<Long>> getAllSuscripcionesOrdenesEnStage() {
		val result = getService().findAllSuscripciones();
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = RestConstants.ordenes_alistamiento_suscripciones + "/{id}")
	public ResponseEntity<OrdShipmentDto> getByIdSuscripcion(@PathVariable long id) {
		val optional = getService().findByIdSuscripcion(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = RestConstants.ordenes_alistamiento_suscripciones_ack)
	public ResponseEntity<?> ack(@PathVariable long id) {
		getService().cancelarSuscripcion(id);
		return ResponseEntity.ok().build();
	}
}
