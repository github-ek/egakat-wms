package com.egakat.wms.maestros.dto;

import java.math.BigDecimal;

import com.egakat.wms.maestros.dto.ClientDto.ClientDtoBuilder;

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
public class MaterialDto {
	String prtnum;

	BigDecimal cnsqty;
}
