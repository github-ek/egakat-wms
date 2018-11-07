package com.egakat.wms.ordenes.service.suscripciones.impl;

import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineCancelacionDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineDto;
import com.egakat.wms.ordenes.dto.alistamientos.OrdShipmentLineLoteDto;
import com.egakat.wms.ordenes.service.suscripciones.api.OrdenAlistamientoSuscripcionService;

import lombok.val;

@Service
public class OrdenAlistamientoSuscripcionServiceImpl extends SuscripcionServiceImpl<OrdShipmentDto>
		implements OrdenAlistamientoSuscripcionService {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	protected NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	protected String getSuscripcion() {
		return "ALISTAMIENTOS_EN_STAGE";
	}

	@Override
	protected String getIdSuscripcionParameterName() {
		return "id";
	}

	@Override
	protected String getSqlFindAllSuscripciones() {
		return "SELECT id_suscripcion FROM [eHistoricos].dbo.OrdShipmentEnStage()";
	}

	@Override
	protected String getSqlFindByIdSuscripcion() {
		return "SELECT * FROM [eHistoricos].dbo.OrdShipmentEnStage() WHERE id_suscripcion = :id";
	}

	@Override
	protected String getSqlCrearSuscripcion() {
		val sql = " INSERT INTO [eHistoricos].dbo.suscripciones\r\n"
				+ "    (suscripcion,id_externo,correlacion,arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9)\r\n"
				+ " VALUES\r\n"
				+ "    (:suscripcion,:idexterno,:correlacion,:arg0,:arg1,:arg2,:arg3,:arg4,:arg5,:arg6,:arg7,:arg8,:arg9)";
		return sql;
	}

	@Override
	protected String getSqlCancelarSuscripcion() {
		val sql = "DELETE FROM [eHistoricos].dbo.suscripciones WHERE id_suscripcion = :id";
		return sql;
	}

	@Override
	protected RowMapper<OrdShipmentDto> getRowMapper() {
		return (rs, rowNum) -> {
			OrdShipmentDto result = new OrdShipmentDto();

			result.setIdSuscripcion(rs.getLong("id_suscripcion"));
			result.setClientId(rs.getString("client_id"));
			result.setOrdnum(rs.getString("ordnum"));
			result.setWhId(rs.getString("wh_id"));
			result.setOrdtyp(rs.getString("ordtyp"));

			result.setLineas(asLineas(result));

			return result;
		};
	}

	protected List<OrdShipmentLineDto> asLineas(OrdShipmentDto model) {
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

	protected List<OrdShipmentLineDto> getLineas(BeanPropertySqlParameterSource params) {
		val sql = "SELECT a.ordlin, a.prtnum, a.invsts_prg, a.ordqty, a.stgqty, a.shpqty FROM [eHistoricos].dbo.OrdShipmentEnStageLineas(:clientId,:ordnum,:whId) a";
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

	protected List<OrdShipmentLineCancelacionDto> getCancelaciones(BeanPropertySqlParameterSource params) {
		val sql = "SELECT a.ordlin, a.prtnum, a.cancod, a.lngdsc, a.remqty, a.can_usr_id, a.candte FROM [eHistoricos].dbo.OrdShipmentEnStageCancelaciones(:clientId,:ordnum,:whId) a";
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

	protected List<OrdShipmentLineLoteDto> getLotes(BeanPropertySqlParameterSource params) {
		val sql = "SELECT a.ordlin, a.prtnum ,a.lotnum ,a.invsts ,a.orgcod ,a.expire_dte, a.untqty FROM [eHistoricos].dbo.OrdShipmentEnStageLotes(:clientId,:ordnum,:whId) a";
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
