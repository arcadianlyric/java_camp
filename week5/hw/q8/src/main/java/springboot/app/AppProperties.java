package springboot.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties{

    private String test = "test";

    public String getTest(){
        return test;
    }

    public void setTest(String test){
        this.test = test;
    }
}