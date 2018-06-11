package com.egakat.wms.maestros.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egakat.core.web.api.controllers.QueryRestController;
import com.egakat.wms.maestros.constants.RestConstants;
import com.egakat.wms.maestros.dto.MaterialDto;
import com.egakat.wms.maestros.dto.WareHouseDto;
import com.egakat.wms.maestros.service.api.WareHouseQueryService;

import lombok.val;

@RestController
@RequestMapping(value = RestConstants.bodegas, produces = MediaType.APPLICATION_JSON_VALUE)
public class WareHouseRestQueryController extends QueryRestController<WareHouseDto, String> {

	@Autowired
	private WareHouseQueryService service;

	@Override
	protected WareHouseQueryService getService() {
		return service;
	}

	@GetMapping(value = RestConstants.productoTieneListaDeMateriales, params = { "invsts" })
	public ResponseEntity<Boolean> getMethodName(@PathVariable String wh_id, @PathVariable String client_id,
			@PathVariable String prtnum, @RequestParam String invsts) {
		
		val result = getService().productoTieneListaDeMateriales(wh_id, client_id, prtnum, invsts);
		
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = RestConstants.bom)
	public ResponseEntity<List<MaterialDto>> getBom(@PathVariable String wh_id, @PathVariable String client_id,
			@PathVariable String prtnum) {
		
		val result = getService().findBom(wh_id, client_id, prtnum);
		
		return ResponseEntity.ok(result);
	}
}
