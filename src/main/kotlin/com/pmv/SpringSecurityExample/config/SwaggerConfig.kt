package com.pmv.SpringSecurityExample.config

import io.swagger.annotations.Api
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    private fun defaultAuth(): List<SecurityReference?> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api::class.java))
            .build()
            .apiInfo(metaData())
    }

    protected fun metaData(): ApiInfo {
        return ApiInfo(
            "Swagger example",
            "API приложения",
            "1.0.0",
            "Terms of service",
            Contact("Me", "", "matvei.popchenko@gmail.com"),
            "License of API", "", emptyList()
        )
    }
}