package com.egakat.wms.maestros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egakat.core.data.jpa.repository.IdentifiedDomainObjectRepository;
import com.egakat.wms.maestros.domain.Material;
import com.egakat.wms.maestros.domain.WareHouse;
import com.egakat.wms.maestros.domain.ordenes.OrdShipment;
import com.egakat.wms.maestros.domain.ordenes.OrdShipmentLineCancelacion;
import com.egakat.wms.maestros.domain.ordenes.OrdShipmentLine;
import com.egakat.wms.maestros.domain.ordenes.OrdShipmentLineLote;

public interface WareHouseRepository extends IdentifiedDomainObjectRepository<WareHouse, String> {

	@Query(value = "SELECT bomnum FROM bomhdr a WHERE wh_id = :wh_id AND client_id = :client_id AND prtnum = :prtnum ", nativeQuery = true)
	List<String> productoTieneListaDeMateriales(@Param("wh_id") String wh_id, @Param("client_id") String client_id,
			@Param("prtnum") String prtnum);

	@Query(value = "SELECT b.prtnum ,SUM(b.cnsqty) AS cnsqty FROM bomhdr a INNER JOIN bomdtl b ON b.bomnum = a.bomnum AND b.wh_id = a.wh_id AND b.client_id = a.client_id WHERE a.wh_id = :wh_id AND a.client_id = :client_id AND a.prtnum = :prtnum GROUP BY b.prtnum", nativeQuery = true)
	List<Material> findBom(@Param("wh_id") String wh_id, @Param("client_id") String client_id,
			@Param("prtnum") String prtnum);

	
	
	
	@Query(value = "SELECT a.id_suscripcion AS idSuscripcion, a.client_id AS clientId, a.ordnum, a.wh_id AS whId, a.ordtyp FROM [eHistoricos].dbo.OrdShipmentEnStage() a", nativeQuery = true)
	List<OrdShipment> findAllOrdShipmentEnStage();

	@Query(value = "SELECT a.ordlin, a.prtnum, a.invsts_prg AS invsts, a.ordqty, a.shpqty, a.stgqty FROM [eHistoricos].dbo.OrdShipmentEnStageLineas(:client_id,:ordnum,:wh_id) a", nativeQuery = true)
	List<OrdShipmentLine> findOrdenesDeAlistamientoEnStageLineas(@Param("client_id") String client_id,
			@Param("ordnum") String ordnum, @Param("wh_id") String wh_id);

	@Query(value = "SELECT a.ordlin, a.prtnum, a.cancod, a.remqty, a.candte, a.can_usr_id, a.lngdsc FROM [eHistoricos].dbo.OrdShipmentEnStageCancelaciones(:client_id,:ordnum,:wh_id) a", nativeQuery = true)
	List<OrdShipmentLineCancelacion> findOrdenesDeAlistamientoEnStageCancelaciones(
			@Param("client_id") String client_id, @Param("ordnum") String ordnum, @Param("wh_id") String wh_id);

	@Query(value = "SELECT a.ordlin ,a.prtnum ,a.lotnum ,a.invsts ,a.orgcod ,a.expire_dte,a.untqty FROM [eHistoricos].dbo.OrdShipmentEnStageLotes(:client_id,:ordnum,:wh_id) a", nativeQuery = true)
	List<OrdShipmentLineLote> findOrdenesDeAlistamientoEnStageLotes(@Param("client_id") String client_id,
			@Param("ordnum") String ordnum, @Param("wh_id") String wh_id);

	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM [eHistoricos].[dbo].suscripciones WHERE id_suscripcion = :id_suscripcion ", nativeQuery = true)
	void deleteSuscripcion(@Param("id_suscripcion") Long id_suscripcion);

}
