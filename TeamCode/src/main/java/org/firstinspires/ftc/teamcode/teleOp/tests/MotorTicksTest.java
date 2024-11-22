package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.LinearEquation;

public class MotorTicksTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor;

        motor = hardwareMap.get(DcMotor.class, "linearSlide");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        int pos = 0;

        while (opModeIsActive()) {
            pos += (int) gamepad1.right_stick_y;
            motor.setTargetPosition(pos);
            telemetry.addData("Motor Ticks Position", pos);
            telemetry.update();
        }
    }
}
