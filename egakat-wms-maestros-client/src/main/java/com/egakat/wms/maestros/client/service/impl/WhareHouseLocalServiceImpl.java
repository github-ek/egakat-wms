package com.egakat.wms.maestros.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.web.client.service.impl.LocalQueryServiceImpl;
import com.egakat.wms.maestros.client.components.WmsRestProperties;
import com.egakat.wms.maestros.client.service.api.WhareHouseLocalService;
import com.egakat.wms.maestros.constants.RestConstants;
import com.egakat.wms.maestros.dto.Material;
import com.egakat.wms.maestros.dto.WareHouseDto;

import lombok.val;

@Service
public class WhareHouseLocalServiceImpl extends LocalQueryServiceImpl<WareHouseDto, String>
		implements WhareHouseLocalService {

	@Autowired
	private WmsRestProperties properties;

	protected WmsRestProperties getProperties() {
		return properties;
	}

	@Override
	protected String getResourceName() {
		return RestConstants.bodegas;
	}

	@Override
	protected Class<WareHouseDto> getResponseType() {
		return WareHouseDto.class;
	}

	@Override
	protected Class<WareHouseDto[]> getArrayReponseType() {
		return WareHouseDto[].class;
	}

	@Override
	public boolean productoTieneListaDeMateriales(String wh_id, String client_id, String prtnum, String invsts) {
		val response = getRestClient().getOneQuery(getResourcePath(),
				RestConstants.productoTieneListaDeMateriales + "?invsts={invsts}", Boolean.class, wh_id, client_id,
				prtnum, invsts);
		val result = response.getBody();
		return result;
	}

	@Override
	public List<Material> findBom(String wh_id, String client_id, String prtnum) {
		val response = getRestClient().getAllQuery(getResourcePath(), RestConstants.bom, Material[].class, wh_id,
				client_id, prtnum);
		val result = Arrays.asList(response.getBody());
		return result;
	}
}