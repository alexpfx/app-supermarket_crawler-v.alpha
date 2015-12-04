package br.com.alexpfx.supermarket.crawler.model;

/**
 * Created by alexandre on 04/12/2015.
 */
public class QuantityMeasurement {
    private MeasurementUnity unity;
    private double value;

    public QuantityMeasurement(MeasurementUnity unity, double value) {
        this.unity = unity;
        this.value = value;
    }

    public MeasurementUnity getUnity() {
        return unity;
    }

    public double getValue() {
        return value;
    }
}
