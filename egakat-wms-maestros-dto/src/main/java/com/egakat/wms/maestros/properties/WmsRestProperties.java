package com.egakat.wms.maestros.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.egakat.core.web.client.properties.RestProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "com.egakat.wms.maestros.rest")
@Getter
@Setter
@ToString
@Validated
public class WmsRestProperties implements RestProperties {

	private String basePath;
}

