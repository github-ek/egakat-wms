package com.egakat.wms.maestros.client.service.api;

import java.util.List;

import com.egakat.wms.maestros.dto.ordenes.OrdShipmentDto;


public interface OrdStageLocalService {

	List<OrdShipmentDto> findAllEnStage();
	
	void ack(OrdShipmentDto model);
}