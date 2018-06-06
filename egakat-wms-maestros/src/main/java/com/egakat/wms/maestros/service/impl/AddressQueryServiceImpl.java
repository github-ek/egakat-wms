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
		// @formatter:on
		val result = AddressDto
				.builder()
				.id(entity.getId())
				.clienteId(defaultString(entity.getClienteId()))
				.hostExternalId(defaultString(entity.getHostExternalId()))
				.nombre(defaultString(entity.getNombre()))
				.linea1(defaultString(entity.getLinea1()))
				.linea2(defaultString(entity.getLinea2()))
				.linea3(defaultString(entity.getLinea3()))
				.ciudad(defaultString(entity.getCiudad()))
				.build();
		// @formatter:off

		return result;
	}

}