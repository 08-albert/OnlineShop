package com.sda.shop.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.sda.shop")
@Import({DataBaseConfig.class, WebConfig.class})


public class AppConfig {
}
