package de.artus.artusmod.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ParametrizedFunction {

    @Getter @Setter
    private double a;

    public double of(double x) {
        return Math.pow(x, getA()) / (Math.pow(x, getA()) + Math.pow(1 - x, getA()));
    }

}
