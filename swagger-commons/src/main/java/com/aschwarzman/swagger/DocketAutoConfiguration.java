package com.aschwarzman.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@PropertySource("classpath:META-INF/swagger-specification-defaults.properties")
@EnableConfigurationProperties(value = { ApiSpecificationProperties.class })
@ConditionalOnProperty(value = "swagger.enabled", havingValue = "true", matchIfMissing = true)
public class DocketAutoConfiguration {

	@Autowired
	private BuildProperties buildProperties;

	@Autowired
	private ServerProperties serverProperties;

	@Autowired
	private ApiSpecificationProperties apiSpecificationProperties;

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(apis()).paths(PathSelectors.any()).build();

		if (apiSpecificationProperties.isIncludeProtocols()) {
			List<String> protocols = apiSpecificationProperties.getProtocols();
			if (protocols == null) {
				Ssl ssl = serverProperties.getSsl();
				if (ssl != null && ssl.isEnabled()) {
					protocols = Arrays.asList("https");
				} else {
					protocols = Arrays.asList("http");
				}

			}
			if (!protocols.isEmpty()) {
				docket.protocols(new HashSet(protocols));
			}
		}

		return docket;
	}

	private ApiInfo apiInfo() {
		com.aschwarzman.swagger.ApiSpecificationProperties.ApiInfo apiInfo = apiSpecificationProperties.getApiInfo();

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		String title = apiInfo.getTitle();
		if (title == null) {
			title = buildProperties.getName();
		}

		if (title != null) {
			apiInfoBuilder.title(title);
		}
		String description = apiInfo.getDescription();
		if (description != null) {
			apiInfoBuilder.description(description);
		}
		String version = buildProperties.getVersion();
		if (version != null) {
			apiInfoBuilder.version(version);
		}

		com.aschwarzman.swagger.ApiSpecificationProperties.ApiInfo.Contact contact = apiInfo.getContact();
		if (contact != null) {
			String contactName = contact.getName();
			String contactUrl = contact.getUrl();
			String contactEmail = contact.getEmail();

			if (StringUtils.hasText(contactName) || StringUtils.hasText(contactUrl) || StringUtils.hasText(contactEmail)) {
				apiInfoBuilder = apiInfoBuilder.contact(new Contact(contactName, contactUrl, contactEmail));
			}
		}
		List<ApiSpecificationExtension> extenstions = apiInfo.getExtenstions();
		if (extenstions != null) {
			apiInfoBuilder = apiInfoBuilder.extensions(extenstions.stream().map(v -> new StringVendorExtension(v.getName(), v.getValue())).collect(Collectors.toList()));
		}

		String licenseInfo = apiInfo.getLicense().getInfo();
		if (licenseInfo != null) {
			apiInfoBuilder = apiInfoBuilder.license(licenseInfo);
		}
		String licenseUrl = apiInfo.getLicense().getUrl();
		if (licenseUrl != null) {
			apiInfoBuilder = apiInfoBuilder.licenseUrl(licenseUrl);
		}
		String termsOfServiceUrl = apiInfo.getTermsOfServiceUrl();
		if (termsOfServiceUrl != null) {
			apiInfoBuilder = apiInfoBuilder.termsOfServiceUrl(termsOfServiceUrl);
		}
		return apiInfoBuilder.build();
	}

	protected Predicate<RequestHandler> apis() {
		return RequestHandlerSelectors.withClassAnnotation(Api.class);
	}
}
