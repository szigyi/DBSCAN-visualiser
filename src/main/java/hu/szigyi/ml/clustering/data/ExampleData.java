package hu.szigyi.ml.clustering.data;

import com.flowpowered.noise.Noise;
import com.flowpowered.noise.NoiseQuality;
import hu.szigyi.ml.clustering.model.DataPoint;
import hu.szigyi.ml.clustering.util.CartesianPolar;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Collection<DataPoint> createRandom() {
        Collection<DataPoint> data = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            DataPoint point = new DataPoint(random.nextDouble(), random.nextDouble());
            data.add(point);
        }
        return data;
    }

    public Collection<DataPoint> createCircle(double gap, int innerSize, int outerSize) {
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

    public Collection<DataPoint> createNoise() {
        NoiseQuality quality = NoiseQuality.BEST;
        Collection<DataPoint> dataPoints = new ArrayList<>();
        int size = 200;
        double x = 0.01;
        double y = 0.07;
        double z = 0;
        double xOff = 0.001;
        double yOff = 0.015;
        double zOff = 0.01;
        int seed = 0;
        for (int i = 0; i < size; i++) {
            double X = Noise.gradientCoherentNoise3D(x, y, z, seed, quality);
            y += yOff;
            double Y = Noise.gradientCoherentNoise3D(x, y, z, seed, quality);
            dataPoints.add(new DataPoint(X, Y));
            x += xOff;
            //z += zOff;
        }
        return dataPoints;
    }
}
