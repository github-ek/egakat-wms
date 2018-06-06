package com.egakat.wms.maestros.client.service.api;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.egakat.core.web.client.service.api.LocalQueryService;
import com.egakat.wms.maestros.dto.Material;
import com.egakat.wms.maestros.dto.WareHouseDto;


public interface WhareHouseLocalService extends LocalQueryService<WareHouseDto, String> {
	
	@Cacheable(cacheNames = "producto-tiene-lista-de-materiales", sync = true)
	boolean productoTieneListaDeMateriales(String wh_id, String client_id, String prtnum, String invsts);

	@Cacheable(cacheNames = "bom", sync = true)
	List<Material> findBom(String wh_id, String client_id, String prtnum);

}