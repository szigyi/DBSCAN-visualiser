package hu.szigyi.ml.clustering.data;

import hu.szigyi.ml.clustering.model.DataPoint;
import hu.szigyi.ml.clustering.util.CartesianPolar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by szabolcs on 30/10/2016.
 */
@Component
public class ExampleData {

    @Autowired
    private CartesianPolar cartesianPolar;

    private Random random;

    public ExampleData() {
        random = new Random();
    }

    public Collection<DataPoint> createExample() {
        Collection<DataPoint> data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            DataPoint point = new DataPoint(random.nextDouble(), random.nextDouble());
            data.add(point);
        }
        return data;
    }

    public Collection<DataPoint> createExampleCircle(double gap, int innerSize, int outerSize) {
        double maxAngle = 360;
        double innerRadius = 0.3;
        double outerRadius = 0.3;
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
        List<Double> outRadius = random.doubles(outerSize, (innerRadius + gap), (innerRadius + gap + outerRadius)).boxed().collect(Collectors.toList());
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
