package org.firstinspires.ftc.teamcode;

public class IntakeAndDeliverControls {
    OperationMode operationMode;

    public IntakeAndDeliverControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void intakeAndDeliverControls() {
        intakeControl();

        linearSlide();

        sweeperRotator();
        sweeper();
    }

    int startLimit = 100;
    int endLimit = 3300;

    int intakeBack() {
        double position = -operationMode.intakeExtender.getCurrentPosition();

        while (position < startLimit) {
            operationMode.intakeExtender.setPower(-1);

        }
    }

    void intakeControl() {
        double power = operationMode.gamepad2.left_stick_y;
        double position = -operationMode.intakeExtender.getCurrentPosition();

        int startLimit = 100;
        int endLimit = 3300;

        if (position > endLimit && -power > 0) {
            power = 0;
        }
        if (position < startLimit && -power < 0) {
            power = 0;
        }

        operationMode.intakeExtender.setPower(power);
    }

    boolean pressed = false;

    void linearSlide() {
        if (operationMode.gamepad2.right_bumper) {
            pressed = true;
        }
        else if (operationMode.gamepad2.left_bumper) {
            pressed = false;
        }

        int ticks = operationMode.linearSlide.getCurrentPosition();

        if (pressed && ticks > 4320) {
            // (4320, 0) (4720, 1)
            double power = ((-1/400.0) * (ticks) + 11.8) / 4;
            operationMode.linearSlide.setPower(power);
        }
        else if (pressed && ticks < 4720) {
            operationMode.linearSlide.setPower(1);
        }
        else if (!pressed && ticks > 20) {
            operationMode.linearSlide.setPower(-0.15);
        }
        else {
            operationMode.linearSlide.setPower(0);
        }
    }

    boolean pressedRotate = false;
    static final double SWEEPER_ROTATOR_MIN_POSITION = .25;
    static final double SWEEPER_ROTATOR_MAX_POSITION = .92;

    static final double LINEAR_SLIDE_MAX_TICKS = 4746;
    static final double INTAKE_MAX_TICKS = 3583;

    void sweeperRotator() {
        if (operationMode.gamepad2.right_stick_y < 0) {
            pressedRotate = true;
            // make put intake back
        }
        if (operationMode.gamepad2.right_stick_y > 0) {
            pressedRotate = false;
        }

        operationMode.sweeperRotator.setPosition(pressedRotate ? SWEEPER_ROTATOR_MAX_POSITION : SWEEPER_ROTATOR_MIN_POSITION);
    }

    void sweeper() {
         operationMode.sweeper.setPower(
                operationMode.gamepad2.square ? -1 : (operationMode.gamepad2.circle ? 0.5 : 0)
         );
     }
}
