package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeAndDeliverControls {
    OperationMode operationMode;

    public IntakeAndDeliverControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void intakeAndDeliverControls() {
        intakeControl();
        calculateHeight();
        intakeControl();
        linearSlide();
    }

//    void intakeControl() {
//        operationMode.intakeMotor.setPower(operationMode.gamepad2.left_stick_y);
//        operationMode.linearSlide.setPower(operationMode.gamepad2.right_stick_y);
//    }

    void intakeControl() {
        double power = operationMode.gamepad2.left_stick_y;
        double position = -operationMode.intakeMotor.getCurrentPosition();

        int startLimit = 100;
        int endLimit = 3300;

        if (position > endLimit && -power > 0) {
            power = 0;
            //operationMode.intakeMotor.setTargetPosition(startLimit);
        }
        if (position < startLimit && -power < 0) {
            power = 0;
            operationMode.intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            operationMode.intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //operationMode.intakeMotor.setTargetPosition(startLimit);
        }

        operationMode.intakeMotor.setPower(power);
    }

    boolean pressing = false;

    void linearSlide() {
        if (operationMode.gamepad2.right_bumper) {
            pressing = true;
        }
        else if (operationMode.gamepad2.left_bumper) {
            pressing = false;
        }

        if (pressing && operationMode.linearSlide.getCurrentPosition() > -4720) {
            operationMode.linearSlide.setPower(-0.25);
        }
        else if (!pressing && operationMode.linearSlide.getCurrentPosition() < 20) {
            operationMode.linearSlide.setPower(0.15);
        }
        else {
            operationMode.linearSlide.setPower(0);
        }
    }

    // -4746
    // -3583

    void calculateHeight() {
        boolean power = operationMode.gamepad2.circle;
        operationMode.telemetry.addData("Power", power);
        operationMode.telemetry.addData("Linear Slide Ticks", operationMode.linearSlide.getCurrentPosition());
        operationMode.telemetry.addData("Intake Ticks", operationMode.intakeMotor.getCurrentPosition());
        operationMode.telemetry.update();
    }

}


