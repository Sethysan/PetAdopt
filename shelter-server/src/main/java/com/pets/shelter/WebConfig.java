package com.pets.shelter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve images from the 'images/pets' directory under the root of the project
        registry.addResourceHandler("/images/pets/**")
                .addResourceLocations("file:./images/pets/");
    }
}
