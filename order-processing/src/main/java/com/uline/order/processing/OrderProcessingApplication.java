package com.uline.order.processing;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import io.hawt.springboot.EnableHawtio;
import io.hawt.web.AuthenticationFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHawtio
public class OrderProcessingApplication extends SpringBootServletInitializer {
	private static final String CAMEL_URL_MAPPING = "/api/*";
	private static final String CAMEL_SERVLET_NAME = "CamelServlet";

	public static void main(String[] args) {
		System.setProperty(AuthenticationFilter.HAWTIO_AUTHENTICATION_ENABLED, "false");
		SpringApplication.run(OrderProcessingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OrderProcessingApplication.class);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				CAMEL_URL_MAPPING);
		registration.setName(CAMEL_SERVLET_NAME);
		return registration;
	}
}
