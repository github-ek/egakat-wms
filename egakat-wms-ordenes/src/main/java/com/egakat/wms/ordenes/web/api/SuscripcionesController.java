package com.egakat.wms.ordenes.web.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.io.core.dto.SuscripcionDto;
import com.egakat.io.core.service.api.crud.SuscripcionCrudService;
import com.egakat.wms.ordenes.constants.RestConstants;
import com.egakat.wms.ordenes.constants.SuscripcionesContants;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = RestConstants.suscripciones, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SuscripcionesController {

	@Autowired
	private SuscripcionCrudService crudService;

	@RequestMapping(value = RestConstants.suscripciones_ordenes_alistamiento_creacion, method = RequestMethod.POST)
	ResponseEntity<?> addCreacionOrdenAlistamiento(@RequestBody Map<String, String> input) {
		log.debug("Requesting for suscripcion:{}", input.toString());

		try {
			val suscripcion = new SuscripcionDto();

			suscripcion.setSuscripcion(SuscripcionesContants.ORDENES_ALISTAMIENTO_CREATE);
			suscripcion.setIdExterno(input.get("id_externo"));
			suscripcion.setArg0(input.get("wh_id"));
			suscripcion.setArg1(input.get("client_id"));
			suscripcion.setArg2(input.get("ordnum"));
			suscripcion.setArg3(input.get("id_mensaje"));
			suscripcion.setArg4(input.get("id_orden_alistamiento"));
			
			crudService.create(suscripcion);
			
			return ResponseEntity.accepted().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
}