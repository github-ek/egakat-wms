package com.egakat.wms.maestros.dto.ordenes;

import java.time.LocalDateTime;

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

	@NotNull
	private String canUsrId;

	@NotNull
	private LocalDateTime canDte;
}
