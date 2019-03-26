package com.egakat.wms.ordenes.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.core.web.api.controllers.CrudRestController;
import com.egakat.integration.suscripciones.dto.SuscripcionDto;
import com.egakat.integration.suscripciones.service.api.crud.SuscripcionCrudService;
import com.egakat.wms.ordenes.constants.RestConstants;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentDto;
import com.egakat.wms.ordenes.service.impl.SuscripcionesOrdenesDeAlistamientoServiceImpl;

import lombok.val;

@RestController
@RequestMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdenesAlistamientoController extends CrudRestController<SuscripcionDto, Long> {

	@Autowired
	private SuscripcionCrudService crudService;

	@Autowired
	private SuscripcionesOrdenesDeAlistamientoServiceImpl ordenesService;

	@Override
	protected SuscripcionCrudService getService() {
		return crudService;
	}

	@GetMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO_CREADAS)
	public ResponseEntity<List<String>> getAllOrdenesAlistamientoCreadas() {
		val result = ordenesService.findAllOrdenesCreadas();
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO_CREADAS_ACK)
	public ResponseEntity<?> confirmarNotificacionDeCreacion(@PathVariable String id) {
		val model = getService().findOneBySuscripcionAndIdExterno("ORDENES_DE_ALISTAMIENTO", id);
		model.setEstadoSuscripcion("CREACION_NOTIFICADA");

		getService().update(model);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO_EN_STAGE)
	public ResponseEntity<List<String>> getAllOrdenesAlistamientoEnStage() {
		val result = ordenesService.findAllOrdenesEnStage();
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO_EN_STAGE_ACK)
	public ResponseEntity<?> confirmarNotificacionDeEnStage(@PathVariable String id) {
		val model = getService().findOneBySuscripcionAndIdExterno("ORDENES_DE_ALISTAMIENTO", id);
		model.setEstadoSuscripcion("STAGE_NOTIFICADO");

		getService().update(model);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = RestConstants.SUSCRIPCIONES_ORDENES_ALISTAMIENTO_BY_PK)
	public ResponseEntity<OrdShipmentDto> getOrdenDeAlistamientoByPk(@PathVariable String client_id, @PathVariable String wh_id, @PathVariable String ordnum) {
		val optional = ordenesService.findOrden(client_id, ordnum, wh_id);
		
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}