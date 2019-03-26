package com.egakat.wms.ordenes.service.impl;

import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.egakat.wms.ordenes.constants.IntegracionesConstants;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineCancelacionDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineLoteDto;
import com.egakat.wms.ordenes.service.api.SuscripcionesOrdenesDeAlistamientoService;

import lombok.val;

@Service
public class SuscripcionesOrdenesDeAlistamientoServiceImpl implements SuscripcionesOrdenesDeAlistamientoService {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	protected NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected String getSuscripcion() {
		return IntegracionesConstants.ORDENES_DE_ALISTAMIENTO;
	}

	protected String getSqlOrdenesCreadas() {
		return "SELECT id_externo FROM dbo.OrdenesAlistamientoCreadas()";
	}

	public List<String> findAllOrdenesCreadas() {
		val sql = getSqlOrdenesCreadas();
		val result = getJdbcTemplate().queryForList(sql, new MapSqlParameterSource(), String.class);
		return result;
	}

	protected String getSqlOrdenesEnStage() {
		return "SELECT id_externo FROM dbo.OrdenesAlistamientoEnStage()";
	}

	public List<String> findAllOrdenesEnStage() {
		val sql = getSqlOrdenesEnStage();
		val result = getJdbcTemplate().queryForList(sql, new MapSqlParameterSource(), String.class);
		return result;
	}

	public Optional<OrdShipmentDto> findOrden(String client_id, String ordnum, String wh_id) {
		val sql = "SELECT * FROM dbo.OrdenDeAlistamiento(:client_id, :ordnum, :wh_id)";

		val paramMap = new HashMap<String, Object>();
		paramMap.put("client_id", client_id);
		paramMap.put("ordnum", ordnum);
		paramMap.put("wh_id", wh_id);

		val result = getJdbcTemplate().queryForObject(sql, paramMap, (rs, rowNum) -> {
			OrdShipmentDto r = new OrdShipmentDto();

			r.setClientId(rs.getString("client_id"));
			r.setOrdnum(rs.getString("ordnum"));
			r.setWhId(rs.getString("wh_id"));
			r.setOrdtyp(rs.getString("ordtyp"));

			r.setLineas(asLineas(r));

			return r;
		});
		return Optional.of(result);
	}

	private List<OrdShipmentLineDto> asLineas(OrdShipmentDto model) {
		val params = new BeanPropertySqlParameterSource(model);

		val result = getLineas(params);

		val cancelaciones = getCancelaciones(params);
		val lotes = getLotes(params);

		for (val linea : result) {
			val c = cancelaciones.stream().filter(a -> a.getOrdlin().equals(linea.getOrdlin())).collect(toList());
			val l = lotes.stream().filter(a -> a.getOrdlin().equals(linea.getOrdlin())).collect(toList());
			linea.setCancelaciones(c);
			linea.setLotes(l);
		}

		return result;
	}

	private List<OrdShipmentLineDto> getLineas(BeanPropertySqlParameterSource params) {
		val sql = "SELECT * FROM dbo.OrdenDeAlistamientoLineas(:clientId, :ordnum, :whId)";

		val result = getJdbcTemplate().query(sql, params, (rs, rowNum) -> {
			OrdShipmentLineDto m = new OrdShipmentLineDto();

			m.setOrdlin(rs.getString("ordlin"));
			m.setPrtnum(rs.getString("prtnum"));
			m.setInvsts(rs.getString("invsts_prg"));
			m.setOrdqty(rs.getInt("ordqty"));
			m.setStgqty(rs.getInt("stgqty"));
			m.setShpqty(rs.getInt("shpqty"));

			return m;
		});
		return result;
	}

	private List<OrdShipmentLineCancelacionDto> getCancelaciones(BeanPropertySqlParameterSource params) {
		val sql = "SELECT * FROM dbo.OrdenDeAlistamientoCancelaciones(:clientId, :ordnum, :whId)";

		val result = getJdbcTemplate().query(sql, params, (rs, rowNum) -> {
			OrdShipmentLineCancelacionDto m = new OrdShipmentLineCancelacionDto();

			m.setOrdlin(rs.getString("ordlin"));
			m.setPrtnum(rs.getString("prtnum"));
			m.setCancod(rs.getString("cancod"));
			m.setLngdsc(rs.getString("lngdsc"));
			m.setRemqty(rs.getInt("remqty"));
			m.setCanUsrId(rs.getString("can_usr_id"));

			Timestamp datetime = rs.getTimestamp("candte");
			if (datetime != null) {
				m.setCanDte(datetime.toLocalDateTime());
			}

			return m;
		});
		return result;
	}

	private List<OrdShipmentLineLoteDto> getLotes(BeanPropertySqlParameterSource params) {
		val sql = "SELECT * FROM dbo.OrdenDeAlistamientoLotes(:clientId, :ordnum, :whId)";

		val result = getJdbcTemplate().query(sql, params, (rs, rowNum) -> {
			OrdShipmentLineLoteDto m = new OrdShipmentLineLoteDto();

			m.setOrdlin(rs.getString("ordlin"));
			m.setPrtnum(rs.getString("prtnum"));
			m.setLotnum(rs.getString("lotnum"));
			m.setInvsts(rs.getString("invsts"));
			m.setUntqty(rs.getInt("untqty"));
			m.setOrgcod(rs.getString("orgcod"));

			Timestamp datetime = rs.getTimestamp("expire_dte");
			if (datetime != null) {
				m.setExpireDte(datetime.toLocalDateTime());
			}

			return m;
		});
		return result;
	}
}
