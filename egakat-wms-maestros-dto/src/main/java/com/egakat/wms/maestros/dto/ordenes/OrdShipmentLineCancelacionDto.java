package com.egakat.wms.maestros.dto.ordenes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdShipmentLineCancelacionDto {

	@NotNull
	@Size(max = 10)
	private String ordlin;

	@NotNull
	@Size(max = 50)
	private String prtnum;

	@NotNull
	@Size(max = 40)
	private String cancod;

	@NotNull
	private String lngdsc;

	private int remqty;

}
