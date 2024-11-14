package org.firstinspires.ftc.teamcode.autonomous.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class SetToPositionTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        DcMotor motor = linearSlideMotor;

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int targetPosition = 1000;
        motor.setTargetPosition(targetPosition);

        motor.setPower(0.5); // Set power

        while (motor.isBusy()) {
            // Optional: Telemetry update or check if you want to monitor progress
        }

        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
