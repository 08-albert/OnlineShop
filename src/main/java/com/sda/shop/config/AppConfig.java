package com.sda.shop.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.sda.shop")
@Import({DataBaseConfig.class, WebConfig.class, WebSecurityConfigs.class})


public class AppConfig {
}
