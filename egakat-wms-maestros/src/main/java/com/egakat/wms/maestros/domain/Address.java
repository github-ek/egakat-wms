package com.egakat.wms.maestros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.egakat.core.domain.IdentifiedDomainObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "adrmst")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements IdentifiedDomainObject<String> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "adr_id", length = 20, nullable = false)
	@NotNull
	@Size(max = 20)
	private String id;

	@Column(name = "client_id", length = 32, nullable = true)
	@Size(max = 32)
	private String clienteId;

	@Column(name = "host_ext_id", length = 40, nullable = true)
	@Size(max = 40)
	private String hostExternalId;

	@Column(name = "adrnam", length = 40, nullable = false)
	@NotNull
	@Size(max = 40)
	private String nombre;

	@Column(name = "adrln1", length = 40, nullable = false)
	@Size(max = 40)
	private String linea1;

	@Column(name = "adrln2", length = 40, nullable = false)
	@Size(max = 40)
	private String linea2;

	@Column(name = "adrln3", length = 40, nullable = false)
	@Size(max = 40)
	private String linea3;

	@Column(name = "adrcty", length = 70, nullable = false)
	@Size(max = 70)
	private String ciudad;
}