package org.firstinspires.ftc.teamcode;

public class Portfolio {
    Robot operationMode;

    public Portfolio(Robot operationMode) {
        this.operationMode = operationMode;
    }

    void linearSlideEquation() {
        int ticks = operationMode.linearSlideMotor.getCurrentPosition();

        int x1 = 4800, y1 = 1;
        int x2 = 4975, y2 = 0;

        double slope = (double) (y1 - y2) / (x1 - x2);

        double y_int = y1 - slope * x1;

        double power = Math.min((slope * ticks + y_int), 1);

        operationMode.linearSlideMotor.setPower(power);

    }

    double cubicPowerEquation(double power) {
        return Math.pow(power, 3);
    }
}
