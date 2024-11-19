package org.firstinspires.ftc.teamcode.util;

public class LinearEquation {

    double m;
    double b;

    public LinearEquation(double x1, double y1, double x2, double y2) {
        m = (y1 - y2) / (x1 - x2);
        b = y1 - (m * x1);
    }

    public double solve(double x) {
        return (m * x) + b;
    }


}
