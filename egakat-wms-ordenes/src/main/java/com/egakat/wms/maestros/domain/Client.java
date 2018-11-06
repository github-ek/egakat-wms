package com.egakat.wms.maestros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.egakat.core.domain.IdentifiedDomainObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "client")
@Getter
@ToString
@NoArgsConstructor
public class Client implements IdentifiedDomainObject<String> {

	@Id
	@Column(name = "client_id", length = 32, nullable = false)
	@NotNull
	@Size(max = 32)
	private String id;

	@Column(name = "adr_id", length = 20, nullable = false)
	@NotNull
	@Size(max = 20)
	private String adressId;
}