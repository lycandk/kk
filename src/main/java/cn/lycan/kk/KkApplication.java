package cn.lycan.kk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Makkapakka
 */
@SpringBootApplication
@MapperScan("cn.lycan.kk.mapper")
public class KkApplication {

    public static void main(String[] args) {
        SpringApplication.run(KkApplication.class, args);
    }

}
