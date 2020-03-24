package com.itechart.container.spring.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

@Configuration
public class YamlConfiguration {

    @Bean
    public Yaml yaml() {
        var options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);
        return new Yaml(options);
    }

}
