package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class ForwardCalibration extends Robot {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.a) {
                frontLeftWheel.setPower(0.5);
                frontRightWheel.setPower(0.5);
                backLeftWheel.setPower(0.5);
                backRightWheel.setPower(0.5);
            } else {
                frontLeftWheel.setPower(0);
                frontRightWheel.setPower(0);
                backLeftWheel.setPower(0);
                backRightWheel.setPower(0);
            }

            telemetry.addData("ticks", backRightWheel.getCurrentPosition());
            telemetry.update();
        }
    }
}
