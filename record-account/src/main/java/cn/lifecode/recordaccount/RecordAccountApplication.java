package cn.lifecode.recordaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luolin
 * @date 2021-01-15 17:51:10
 */
@EnableEurekaClient
@SpringBootApplication
public class RecordAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordAccountApplication.class, args);
    }

}
