package com.egakat.wms.maestros.service.impl;

import static org.apache.commons.lang3.StringUtils.defaultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.services.crud.impl.QueryServiceImpl;
import com.egakat.wms.maestros.domain.Address;
import com.egakat.wms.maestros.dto.AddressDto;
import com.egakat.wms.maestros.repository.AddressRepository;
import com.egakat.wms.maestros.service.api.AddressQueryService;

import lombok.val;

@Service
public class AddressQueryServiceImpl extends QueryServiceImpl<Address, AddressDto, String>
		implements AddressQueryService {

	@Autowired
	private AddressRepository repository;

	@Override
	protected AddressRepository getRepository() {
		return repository;
	}

	@Override
	protected AddressDto asModel(Address entity) {
		val result = new AddressDto();

		result.setId(entity.getId());
		result.setClienteId(defaultString(entity.getClienteId()));
		result.setHostExternalId(defaultString(entity.getHostExternalId()));
		result.setNombre(defaultString(entity.getNombre()));
		result.setLinea1(defaultString(entity.getLinea1()));
		result.setLinea2(defaultString(entity.getLinea2()));
		result.setLinea3(defaultString(entity.getLinea3()));
		result.setCiudad(defaultString(entity.getCiudad()));

		return result;
	}

	@Override
	protected AddressDto newModel() {
		return new AddressDto();
	}
}