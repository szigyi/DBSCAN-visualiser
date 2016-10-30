package hu.szigyi.ml.clustering.conf;

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
    private CartesianPolar cartesianPolar;

    private Random random = new Random();

    @Bean("testData")
    public Collection<DataPoint> createTestDataPointCollection() {
        Collection<DataPoint> data = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            DataPoint point = new DataPoint(random.nextDouble(), random.nextDouble());
            data.add(point);
        }
        return data;
    }

    @Bean("circleData")
    public Collection<DataPoint> createExampleCircle() {
        int innerSize = 3000;
        int outerSize = 6000;
        double maxAngle = 360;
        double innerRadius = 0.3;
        double gap = 0.05;
        double outterRadius = 0.3;
        Collection<DataPoint> data = new ArrayList<>();
        // inner circle
        List<Double> inRadius = random.doubles(innerSize, 0, innerRadius).boxed().collect(Collectors.toList());
        List<Double> inAngles = random.doubles(innerSize, 0, maxAngle).boxed().collect(Collectors.toList());
        for (int i = 0; i < innerSize; i++) {
            Double rad = inRadius.get(i);
            Double angle = inAngles.get(i);
            DataPoint point = cartesianPolar.polarToCartesian(angle, rad);
            data.add(point);
        }
        // outer circle
        List<Double> outRadius = random.doubles(outerSize, (innerRadius + gap), (innerRadius + gap + outterRadius)).boxed().collect(Collectors.toList());
        List<Double> outAngles = random.doubles(outerSize, 0, maxAngle).boxed().collect(Collectors.toList());
        for (int i = 0; i < outerSize; i++) {
            Double rad = outRadius.get(i);
            Double angle = outAngles.get(i);
            DataPoint point = cartesianPolar.polarToCartesian(angle, rad);
            data.add(point);
        }
        return data;
    }
}
