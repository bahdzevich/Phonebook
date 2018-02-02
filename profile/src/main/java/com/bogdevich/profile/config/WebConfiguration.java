package com.bogdevich.profile.config;

import com.bogdevich.profile.controller.util.viewresolver.CsvViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
                .favorPathExtension(true);
    }

    /**
     * The {@link ContentNegotiatingViewResolver} does not resolve views itself,
     * but delegates to other ViewResolvers. By default,
     * these other view resolvers are picked up automatically
     * from the application context, though they can also be set explicitly
     * by using the viewResolvers property.
     *
     * @param negotiationManager
     * @return {@link ContentNegotiatingViewResolver} bean.
     */
    @Bean
    public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager negotiationManager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(negotiationManager);
        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(csvViewResolver());
        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    /**
     * Configure View resolver to provide Csv output using Super Csv library to
     * generate Csv output for an object content.
     *
     * @return {@link CsvViewResolver} bean.
     */
    @Bean
    public ViewResolver csvViewResolver() {
        return new CsvViewResolver();
    }
}
