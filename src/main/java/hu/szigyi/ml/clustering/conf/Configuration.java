package hu.szigyi.ml.clustering.conf;

import hu.szigyi.ml.clustering.data.ExampleData;
import hu.szigyi.ml.clustering.model.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collection;

/**
 * Created by szabolcs on 29/10/2016.
 */
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "hu.szigyi.ml.clustering")
public class Configuration {

    private static final double DEFAULT_GAP = 0.05;

    private static final int DEFAULT_INNER_SIZE = 1000;

    private static final int DEFAULT_OUTER_SIZE = 1000;

    @Autowired
    private ExampleData exampleData;

    @Bean("randomData")
    public Collection<DataPoint> createRandomData() {
        return exampleData.createRandom();
    }

    @Bean("noiseData")
    public Collection<DataPoint> createNoiseData() {
        return exampleData.createNoise();
    }

    @Bean("circleData")
    public Collection<DataPoint> createCircleExample() {
        return exampleData.createCircle(DEFAULT_GAP, DEFAULT_INNER_SIZE, DEFAULT_OUTER_SIZE);
    }
}
