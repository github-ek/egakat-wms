package com.egakat.wms.maestros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egakat.core.data.jpa.repository.IdentifiedDomainObjectRepository;
import com.egakat.wms.maestros.domain.WareHouse;
import com.egakat.wms.maestros.dto.Material;

public interface WareHouseRepository extends IdentifiedDomainObjectRepository<WareHouse, String> {

	@Query(value = "SELECT bomnum FROM bomhdr a WHERE wh_id = :wh_id AND client_id = :client_id AND prtnum = :prtnum ", nativeQuery = true)
	List<String> productoTieneListaDeMateriales(@Param("wh_id") String wh_id, @Param("client_id") String client_id,
			@Param("prtnum") String prtnum);

	@Query(value = "SELECT b.prtnum ,SUM(b.cnsqty) AS cnsqty FROM bomhdr a INNER JOIN bomdtl b ON b.bomnum = a.bomnum AND b.wh_id = a.wh_id AND b.client_id = a.client_id WHERE a.wh_id = :wh_id AND a.client_id = :client_id AND a.prtnum = :prtnum GROUP BY b.prtnum", nativeQuery = true)
	List<Material> findBom(@Param("wh_id") String wh_id, @Param("client_id") String client_id,
			@Param("prtnum") String prtnum);

}
