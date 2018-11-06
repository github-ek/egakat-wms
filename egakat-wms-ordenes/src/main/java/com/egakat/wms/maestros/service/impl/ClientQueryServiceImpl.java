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
public class ClientQueryServiceImpl extends QueryServiceImpl<Client, ClientDto, String> implements ClientQueryService {

	@Autowired
	private ClientRepository repository;

	@Override
	protected ClientRepository getRepository() {
		return repository;
	}

	@Override
	protected ClientDto asModel(Client entity) {
		val result = new ClientDto();

		result.setId(entity.getId());
		result.setAdressId(entity.getAdressId());

		return result;
	}

	@Override
	protected ClientDto newModel() {
		return new ClientDto();
	}

}