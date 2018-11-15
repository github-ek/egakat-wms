package com.egakat.wms.ordenes.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.core.services.crud.api.CrudService;
import com.egakat.core.web.api.controllers.CrudRestController;
import com.egakat.io.core.dto.SuscripcionDto;
import com.egakat.io.core.service.api.crud.SuscripcionCrudService;
import com.egakat.wms.ordenes.constants.RestConstants;

@RestController
@RequestMapping(value = RestConstants.suscripciones_ordenes_alistamiento, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdenesAlistamientoController extends CrudRestController<SuscripcionDto, Long> {

	@Autowired
	private SuscripcionCrudService crudService;

	@Override
	protected CrudService<SuscripcionDto, Long> getService() {
		return crudService;
	}
}