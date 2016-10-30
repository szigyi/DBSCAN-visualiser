package hu.szigyi.ml.clustering.util;

import hu.szigyi.ml.clustering.model.DataPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by szabolcs on 30/10/2016.
 */
@Component
public class CartesianPolar {

    public DataPoint polarToCartesian(double angle, double radius) {
        double angleInRadians = Math.toRadians(angle);
        double x = Math.cos(angleInRadians) * radius;
        double y = Math.sin(angleInRadians) * radius;
        return new DataPoint(x, y);
    }
}
