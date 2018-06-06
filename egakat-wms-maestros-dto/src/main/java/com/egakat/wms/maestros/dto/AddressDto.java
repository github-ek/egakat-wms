package com.egakat.wms.maestros.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class AddressDto implements IdentifiedDomainObject<String> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 20)
	private String id;

	@NotNull
	@Size(max = 32)
	private String clienteId;

	@NotNull
	@Size(max = 40)
	private String hostExternalId;

	@NotNull
	@Size(max = 40)
	private String nombre;

	@NotNull
	@Size(max = 40)
	private String linea1;

	@NotNull
	@Size(max = 40)
	private String linea2;

	@NotNull
	@Size(max = 40)
	private String linea3;

	@NotNull
	@Size(max = 70)
	private String ciudad;
}