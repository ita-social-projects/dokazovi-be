package com.softserveinc.dokazovi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

	@Value("${fs.root}")
	private String fsPath;

	@Value("${url.assets.path}")
	private String urlAssetsPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/" + urlAssetsPath + "/**")
				.addResourceLocations("file:///" + Paths.get(fsPath).toAbsolutePath() + File.separator);
	}
}
