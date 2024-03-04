package guo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("guo.mapper")
public class StartSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartSpringBootApplication.class, args);
    }
}
