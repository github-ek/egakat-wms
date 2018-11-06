package com.egakat.wms.maestros.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.core.web.api.controllers.QueryRestController;
import com.egakat.wms.maestros.constants.RestConstants;
import com.egakat.wms.maestros.dto.AddressDto;
import com.egakat.wms.maestros.service.api.AddressQueryService;

@RestController
@RequestMapping(value = RestConstants.direcciones, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressRestQueryController extends QueryRestController<AddressDto, String> {

	@Autowired
	private AddressQueryService service;

	@Override
	protected AddressQueryService getService() {
		return service;
	}
}
