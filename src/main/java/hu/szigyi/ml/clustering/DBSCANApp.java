package hu.szigyi.ml.clustering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by szabolcs on 29/10/2016.
 */
@SpringBootApplication
@Import(hu.szigyi.ml.clustering.conf.Configuration.class)
public class DBSCANApp {

    public static void main(String[] args) {
        SpringApplication.run(DBSCANApp.class, args);
    }
}
