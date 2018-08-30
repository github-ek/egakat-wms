package com.egakat.wms.maestros.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.core.services.crud.impl.QueryServiceImpl;
import com.egakat.wms.maestros.domain.WareHouse;
import com.egakat.wms.maestros.dto.MaterialDto;
import com.egakat.wms.maestros.dto.WareHouseDto;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentDto;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentLineCancelacionDto;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentLineDto;
import com.egakat.wms.maestros.dto.ordenes.OrdShipmentLineLoteDto;
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

	@Override
	public List<OrdShipmentDto> findAllOrdShipmentEnStage() {
		val list = getRepository().findAllOrdShipmentEnStage();

		val result = new ArrayList<OrdShipmentDto>();

		for (val entity : list) {
			val lineas = asLineas(entity.getClientId(), entity.getOrdnum(), entity.getWhId());

			// @formatter:off
			val model = OrdShipmentDto
					.builder()
					.idSuscripcion(entity.getIdSuscripcion())
					.clientId(entity.getClientId())
					.ordnum(entity.getOrdnum())
					.whId(entity.getWhId())
					.ordtyp(entity.getOrdtyp())
					.lineas(lineas)
					.build();
			// @formatter:on
			result.add(model);

		}
		return result;
	}

	protected List<OrdShipmentLineDto> asLineas(String client_id, String ordnum, String wh_id) {
		val list = getRepository().findOrdenesDeAlistamientoEnStageLineas(client_id, ordnum, wh_id);

		val cancelaciones = findOrdenesDeAlistamientoEnStageCancelaciones(client_id, ordnum, wh_id);
		val lotes = findOrdenesDeAlistamientoEnStageLotes(client_id, ordnum, wh_id);

		// @formatter:off
		val result = list
				.stream()
				.map(a -> new OrdShipmentLineDto(a.getOrdlin(), a.getPrtnum(), a.getInvsts(), a.getOrdqty(), a.getShpqty(), a.getStgqty(), cancelaciones, lotes))
				.collect(Collectors.toList());
		// @formatter:on
		
		return result;
	}

	protected List<OrdShipmentLineCancelacionDto> findOrdenesDeAlistamientoEnStageCancelaciones(String client_id,
			String ordnum, String wh_id) {
		val list = getRepository().findOrdenesDeAlistamientoEnStageCancelaciones(client_id, ordnum, wh_id);

		val result = list.stream().map(a -> new OrdShipmentLineCancelacionDto(a.getOrdlin(), a.getPrtnum(),
				a.getCancod(), a.getLngdsc(), a.getRemqty())).collect(Collectors.toList());
		return result;
	}

	protected List<OrdShipmentLineLoteDto> findOrdenesDeAlistamientoEnStageLotes(String client_id, String ordnum,
			String wh_id) {
		val list = getRepository().findOrdenesDeAlistamientoEnStageLotes(client_id, ordnum, wh_id);

		val result = list.stream().map(a -> new OrdShipmentLineLoteDto(a.getOrdlin(), a.getPrtnum(), a.getLotnum(),
				a.getInvsts(), a.getOrgcod(), a.getExpireDte(), a.getUntqty())).collect(Collectors.toList());
		return result;
	}

	@Override
	public void ack(long id) {
		getRepository().deleteSuscripcion(id);
		getRepository().flush();
	}
}