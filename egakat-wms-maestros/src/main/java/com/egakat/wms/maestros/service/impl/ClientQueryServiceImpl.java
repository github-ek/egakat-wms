package com.egakat.wms.maestros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.services.crud.impl.QueryServiceImpl;
import com.egakat.wms.maestros.domain.Client;
import com.egakat.wms.maestros.dto.ClientDto;
import com.egakat.wms.maestros.repository.ClientRepository;
import com.egakat.wms.maestros.service.api.ClientQueryService;

import lombok.val;

@Service
public class ClientQueryServiceImpl extends QueryServiceImpl<Client, ClientDto, String>
		implements ClientQueryService {

	@Autowired
	private ClientRepository repository;

	@Override
	protected ClientRepository getRepository() {
		return repository;
	}

	@Override
	protected ClientDto asModel(Client entity) {
		// @formatter:on
		val result = ClientDto
				.builder()
				.id(entity.getId())
				.adressId(entity.getAdressId())
				.build();
		// @formatter:off

		return result;
	}

}