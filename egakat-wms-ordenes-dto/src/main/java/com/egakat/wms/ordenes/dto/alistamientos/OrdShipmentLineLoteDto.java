package com.egakat.wms.ordenes.dto.alistamientos;

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
public class OrdShipmentLineLoteDto {

	@NotNull
	@Size(max = 10)
	private String ordlin;

	@NotNull
	@Size(max = 50)
	private String prtnum;

	@NotNull
	@Size(max = 25)
	private String lotnum;

	@NotNull
	@Size(max = 4)
	private String invsts;

	private int untqty;
	
	@NotNull
	@Size(max = 25)
	private String orgcod;

	private LocalDateTime expireDte;
}
