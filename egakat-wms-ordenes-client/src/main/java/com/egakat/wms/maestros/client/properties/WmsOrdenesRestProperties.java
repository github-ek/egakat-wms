package com.egakat.wms.maestros.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.egakat.core.web.client.properties.RestProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = WmsOrdenesRestProperties.CONFIGURATION_PROPERTIES)
@Getter
@Setter
@ToString
@Validated
public class WmsOrdenesRestProperties implements RestProperties {

	static final String CONFIGURATION_PROPERTIES = "endpoint.wms.ordenes.rest";

	private String basePath;
}
