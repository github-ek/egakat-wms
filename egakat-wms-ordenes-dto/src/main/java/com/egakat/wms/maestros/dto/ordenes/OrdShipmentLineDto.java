package com.egakat.wms.maestros.dto.ordenes;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdShipmentLineDto {

	@NotNull
	@Size(max = 10)
	private String ordlin;

	@NotNull
	@Size(max = 50)
	private String prtnum;

	@NotNull
	@Size(max = 4)
	private String invsts;

	private int ordqty;

	private int stgqty;

	private int shpqty;

	private List<OrdShipmentLineCancelacionDto> cancelaciones;
	
	private List<OrdShipmentLineLoteDto> lotes;
}