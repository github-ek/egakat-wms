package com.egakat.wms.maestros.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.domain.IdentifiedDomainObject;

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
public class WareHouseDto implements IdentifiedDomainObject<String> {

	@NotNull
	@Size(max = 32)
	private String id;

	@NotNull
	@Size(max = 20)
	private String adressId;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;

	@NotNull
	@Size(max = 40)
	private String modificadoPor;
}
