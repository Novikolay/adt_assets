package web.assets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.assets.repository.FileStorage;

import javax.annotation.Resource;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

    @Resource
    FileStorage fileStorage;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorage.deleteAll();
        fileStorage.init();
    }

}