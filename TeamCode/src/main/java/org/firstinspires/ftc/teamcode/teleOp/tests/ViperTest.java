package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class ViperTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.square) {
                viperSlideMotor.setPower(0.5);
            } else if (gamepad1.triangle) {
                viperSlideMotor.setPower(-0.5);
            } else {
                viperSlideMotor.setPower(0);
            }
        }
    }
}