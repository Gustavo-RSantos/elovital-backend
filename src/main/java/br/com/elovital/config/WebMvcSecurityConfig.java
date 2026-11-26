package br.com.elovital.config;

import br.com.elovital.security.AcessoIdosoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcSecurityConfig implements WebMvcConfigurer {

    private final AcessoIdosoInterceptor acessoIdosoInterceptor;

    public WebMvcSecurityConfig(AcessoIdosoInterceptor acessoIdosoInterceptor) {
        this.acessoIdosoInterceptor = acessoIdosoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(acessoIdosoInterceptor).addPathPatterns("/api/idosos/**");
    }
}
