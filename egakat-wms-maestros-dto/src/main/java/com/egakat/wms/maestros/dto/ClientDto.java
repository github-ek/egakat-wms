package com.egakat.wms.maestros.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.egakat.core.domain.IdentifiedDomainObject;

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
public class ClientDto implements IdentifiedDomainObject<String> {
	
	@NotNull
	@Size(max = 32)
	private String id;

	@NotNull
	@Size(max = 20)
	private String adressId;
}
