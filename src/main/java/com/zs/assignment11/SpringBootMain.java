/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Runs the SpringBoot application.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);

    }
}
