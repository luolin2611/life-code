package cn.lifecode.backmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luolin
 * @date 2021-01-08 13:45:21
 */
@EnableEurekaClient
@SpringBootApplication
public class BackMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackMusicApplication.class, args);
    }

}
