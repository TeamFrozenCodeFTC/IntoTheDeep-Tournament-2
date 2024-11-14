package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class ViperTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.square) {
                linearSlideMotor.setPower(0.5);
            } else if(gamepad1.triangle) {
                linearSlideMotor.setPower(-0.5);
            } else {
                linearSlideMotor.setPower(0);
            }
        }
    }
}