package com.egakat.wms.maestros.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.core.web.api.controllers.QueryRestController;
import com.egakat.wms.maestros.configuration.constants.RestConstants;
import com.egakat.wms.maestros.dto.ClientDto;
import com.egakat.wms.maestros.service.api.ClientQueryService;

@RestController
@RequestMapping(value = RestConstants.clientes, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRestQueryController extends QueryRestController<ClientDto, String> {

	@Autowired
	private ClientQueryService service;

	@Override
	protected ClientQueryService getService() {
		return service;
	}
}
