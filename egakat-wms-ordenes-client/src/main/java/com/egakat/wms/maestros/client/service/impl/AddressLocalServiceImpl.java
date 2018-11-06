package com.egakat.wms.maestros.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.web.client.components.RestClient;
import com.egakat.core.web.client.service.impl.LocalQueryServiceImpl;
import com.egakat.wms.maestros.client.components.WmsMaestrosRestClient;
import com.egakat.wms.maestros.client.properties.WmsMaestrosRestProperties;
import com.egakat.wms.maestros.client.service.api.AddressLocalService;
import com.egakat.wms.maestros.constants.RestConstants;
import com.egakat.wms.maestros.dto.AddressDto;

@Service
public class AddressLocalServiceImpl extends LocalQueryServiceImpl<AddressDto, String> implements AddressLocalService {

	@Autowired
	private WmsMaestrosRestProperties properties;

	@Autowired
	private WmsMaestrosRestClient restClient;

	protected WmsMaestrosRestProperties getProperties() {
		return properties;
	}

	@Override
	protected RestClient getRestClient() {
		return restClient;
	}

	@Override
	protected String getResourceName() {
		return RestConstants.direcciones;
	}

	@Override
	protected Class<AddressDto> getResponseType() {
		return AddressDto.class;
	}

	@Override
	protected Class<AddressDto[]> getArrayReponseType() {
		return AddressDto[].class;
	}
}