package com.egakat.wms.maestros.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.services.crud.impl.QueryServiceImpl;
import com.egakat.wms.maestros.domain.WareHouse;
import com.egakat.wms.maestros.dto.MaterialDto;
import com.egakat.wms.maestros.dto.WareHouseDto;
import com.egakat.wms.maestros.repository.WareHouseRepository;
import com.egakat.wms.maestros.service.api.WareHouseQueryService;

import lombok.val;

@Service
public class WareHouseQueryServiceImpl extends QueryServiceImpl<WareHouse, WareHouseDto, String>
		implements WareHouseQueryService {

	@Autowired
	private WareHouseRepository repository;

	@Override
	protected WareHouseRepository getRepository() {
		return repository;
	}

	@Override
	protected WareHouseDto asModel(WareHouse entity) {
		// @formatter:off
		val result = WareHouseDto
				.builder()
				.id(entity.getId())
				.adressId(entity.getAdressId())
				.fechaModificacion(entity.getFechaModificacion())
				.modificadoPor(entity.getModificadoPor())
				.build();
		// @formatter:on

		return result;
	}

	@Override
	public boolean productoTieneListaDeMateriales(String wh_id, String client_id, String prtnum, String invsts) {
		val boms = getRepository().productoTieneListaDeMateriales(wh_id, client_id, prtnum);
		val result = !boms.isEmpty();
		return result;
	}

	@Override
	public List<MaterialDto> findBom(String wh_id, String client_id, String prtnum) {
		val list = getRepository().findBom(wh_id, client_id, prtnum);

		val result = list.stream().map(a -> new MaterialDto(a.getPrtnum(), a.getCnsqty())).collect(Collectors.toList());
		return result;
	}
}