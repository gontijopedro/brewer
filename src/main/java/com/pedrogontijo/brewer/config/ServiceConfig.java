package com.pedrogontijo.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pedrogontijo.brewer.service.CadastroCervejaService;
import com.pedrogontijo.brewer.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = { CadastroCervejaService.class , FotoStorage.class })
public class ServiceConfig {	

}
