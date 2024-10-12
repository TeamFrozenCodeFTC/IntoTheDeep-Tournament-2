package org.firstinspires.ftc.teamcode;

public class IntakeAndDeliverControls {
    OperationMode operationMode;

    public IntakeAndDeliverControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void intakeAndDeliverControls() {
        intakeControl();
        telemetry();
        linearSlide();
    }

    void intakeControl() {
        double power = operationMode.gamepad2.left_stick_y;
        double position = -operationMode.intakeMotor.getCurrentPosition();

        int startLimit = 100;
        int endLimit = 3300;

        if (position > endLimit && -power > 0) {
            power = 0;
        }
        if (position < startLimit && -power < 0) {
            power = 0;
//            operationMode.intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            operationMode.intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        operationMode.intakeMotor.setPower(power);
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
        
        boolean up = pressed && ticks > 4720;
        boolean down = !pressed && ticks < 20;

        if (up) {
            operationMode.linearSlide.setPower(-0.25);
            pressed = false;
        }
        else if (down) {
            operationMode.linearSlide.setPower(0.15);
        }
        else {
            operationMode.linearSlide.setPower(0);
        }
    }

    // Linear Slide Max -4746
    // Intake Max -3583

    void telemetry() {
        operationMode.telemetry.addData("Linear Slide Ticks", operationMode.linearSlide.getCurrentPosition());
        operationMode.telemetry.addData("Intake Ticks", operationMode.intakeMotor.getCurrentPosition());
        operationMode.telemetry.update();
    }

}
