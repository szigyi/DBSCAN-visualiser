package hu.szigyi.ml.clustering.model;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * Created by szabolcs on 29/10/2016.
 */
public class DataPoint implements Clusterable {

    private double x;

    private double y;

    private double[] points;

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
        this.points = new double[]{ x, y };
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPoint dataPoint = (DataPoint) o;

        if (Double.compare(dataPoint.x, x) != 0) return false;
        return Double.compare(dataPoint.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DataPoint{x=" + x + ", y=" + y + '}';
    }

    @Override
    public double[] getPoint() {
        return points;
    }
}
