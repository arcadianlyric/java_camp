package springboot.app;

import java.beans.BeanProperty;

import javax.security.auth.login.AppConfigurationEntry;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@Import(AppConfiguration.class)
@EnableConfigurationProperties(AppProperties.class)
public class AppAutoConfiguration{

    @Autowired
    AppProperties properties;

    @Autowired
    AppConfiguration configuration;

    @Bean
    public AppInfo createInfo(){
        return new AppInfo(configuration.name + "-" + properties.getTest());
    }
}