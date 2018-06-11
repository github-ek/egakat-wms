package com.egakat.wms.maestros.service.api;

import java.util.List;

import com.egakat.core.services.crud.api.QueryService;
import com.egakat.wms.maestros.dto.MaterialDto;
import com.egakat.wms.maestros.dto.WareHouseDto;

public interface WareHouseQueryService extends QueryService<WareHouseDto, String> {
	boolean productoTieneListaDeMateriales(String wh_id, String client_id, String prtnum, String invsts);

	List<MaterialDto> findBom(String wh_id, String client_id, String prtnum);
}
