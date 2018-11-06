package com.egakat.wms.maestros.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.egakat.core.web.client.properties.RestProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = WmsMaestrosRestProperties.CONFIGURATION_PROPERTIES)
@Getter
@Setter
@ToString
@Validated
public class WmsMaestrosRestProperties implements RestProperties {

	static final String CONFIGURATION_PROPERTIES = "endpoint.wms.maestros.rest";

	private String basePath;
}
