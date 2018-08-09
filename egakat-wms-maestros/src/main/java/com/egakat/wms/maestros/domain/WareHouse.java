package com.egakat.wms.maestros.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.domain.IdentifiedDomainObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "wh")
@Getter
@ToString
@NoArgsConstructor
public class WareHouse implements IdentifiedDomainObject<String> {

	@Id
	@Column(name = "wh_id", length = 32, nullable = false)
	@NotNull
	@Size(max = 32)
	private String id;

	@Column(name = "adr_id", length = 20, nullable = false)
	@NotNull
	@Size(max = 20)
	private String adressId;

	@Column(name = "moddte")
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;

	@Column(name = "mod_usr_id")
	@Size(max = 40)
	private String modificadoPor;
}