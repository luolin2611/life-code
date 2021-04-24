package cn.lifecode.recordaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author luolin
 * @date 2021-01-15 17:51:10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RecordAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordAccountApplication.class, args);
    }

}
