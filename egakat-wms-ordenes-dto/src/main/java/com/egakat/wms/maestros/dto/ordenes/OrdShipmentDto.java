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
public class OrdShipmentDto {

	private Long idSuscripcion;
	
	@NotNull
	@Size(max = 32)
	private String clientId;

	@NotNull
	@Size(max = 35)
	private String ordnum;

	@NotNull
	@Size(max = 32)
	private String whId;

	@NotNull
	@Size(max = 4)
	private String ordtyp;
	
	List<OrdShipmentLineDto> lineas;
}
