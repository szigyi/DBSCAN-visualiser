package hu.szigyi.ml.clustering.conf;

import hu.szigyi.ml.clustering.data.ExampleData;
import hu.szigyi.ml.clustering.model.DataPoint;
import hu.szigyi.ml.clustering.util.CartesianPolar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by szabolcs on 29/10/2016.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private ExampleData exampleData;

    @Bean("testData")
    public Collection<DataPoint> createExample() {
        return exampleData.createExample();
    }

    @Bean("circleData")
    public Collection<DataPoint> createCircleExample() {
        return exampleData.createExampleCircle();
    }
}
