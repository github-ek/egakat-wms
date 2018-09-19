package com.egakat.wms.maestros.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.web.client.configuration.RestProperties;
import com.egakat.core.web.client.service.impl.LocalQueryServiceImpl;
import com.egakat.wms.maestros.client.configuration.properties.WmsRestProperties;
import com.egakat.wms.maestros.client.service.api.OrdStageLocalService;
import com.egakat.wms.maestros.configuration.constants.RestConstants;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentDto;

import lombok.val;

@Service
public class OrdStageLocalServiceImpl extends LocalQueryServiceImpl<OrdShipmentDto, Long>
		implements OrdStageLocalService {

	@Autowired
	private WmsRestProperties properties;

	@Override
	protected RestProperties getProperties() {
		return properties;
	}

	@Override
	protected String getResourceName() {
		return RestConstants.bodegas + RestConstants.ordenes_alistamiento;
	}

	@Override
	protected Class<OrdShipmentDto> getResponseType() {
		return OrdShipmentDto.class;
	}

	@Override
	protected Class<OrdShipmentDto[]> getArrayReponseType() {
		return OrdShipmentDto[].class;
	}

	@Override
	public List<OrdShipmentDto> findAllEnStage() {
		val response = getRestClient().getAllQuery(getResourcePath(), "", OrdShipmentDto[].class);
		val result = Arrays.asList(response.getBody());
		return result;
	}

	@Override
	public void ack(OrdShipmentDto model) {
		val query = "/suscripcion/{suscripcion}";
		getRestClient().delete(getResourcePath() + query, model.getIdSuscripcion());
	}
}
