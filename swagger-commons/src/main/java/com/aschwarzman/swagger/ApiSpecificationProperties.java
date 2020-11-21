package com.aschwarzman.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
@ConfigurationProperties(prefix = "api-specification", ignoreUnknownFields = true)
public class ApiSpecificationProperties {

	@NestedConfigurationProperty
	private ApiInfo apiInfo = new ApiInfo();

	private boolean includeProtocols = true;
	private List<String> protocols;

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	public boolean isIncludeProtocols() {
		return includeProtocols;
	}

	public void setIncludeProtocols(boolean includeProtocols) {
		this.includeProtocols = includeProtocols;
	}

	public List<String> getProtocols() {
		return protocols;
	}

	public void setProtocols(List<String> protocols) {
		this.protocols = protocols;
	}

	public static class ApiInfo {

		@NotNull
		private String title;

		private String description;

		@NestedConfigurationProperty
		private Contact contact;

		@NestedConfigurationProperty
		private License license;

		private String termsOfServiceUrl;

		private List<ApiSpecificationExtension> extenstions = new ArrayList();

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}

		public License getLicense() {
			return license;
		}

		public void setLicense(License license) {
			this.license = license;
		}

		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}

		public void setTermsOfServiceUrl(String termsOfServiceUrl) {
			this.termsOfServiceUrl = termsOfServiceUrl;
		}

		public List<ApiSpecificationExtension> getExtenstions() {
			return extenstions;
		}

		public void setExtenstions(List<ApiSpecificationExtension> extenstions) {
			this.extenstions = extenstions;
		}

		public static class Contact {

			private String name;
			private String url;
			private String email;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

		}

		public static class License {

			private String info;
			private String url;

			public String getInfo() {
				return info;
			}

			public void setInfo(String info) {
				this.info = info;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

		}
	}
}
