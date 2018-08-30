package com.egakat.wms.maestros.service.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.egakat.core.services.crud.api.QueryService;
import com.egakat.wms.maestros.dto.MaterialDto;
import com.egakat.wms.maestros.dto.WareHouseDto;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentDto;

public interface WareHouseQueryService extends QueryService<WareHouseDto, String> {
	boolean productoTieneListaDeMateriales(String wh_id, String client_id, String prtnum, String invsts);

	List<MaterialDto> findBom(String wh_id, String client_id, String prtnum);

	List<OrdShipmentDto> findAllOrdShipmentEnStage();

	@Transactional(readOnly = false)
	void ack(long id);
}
