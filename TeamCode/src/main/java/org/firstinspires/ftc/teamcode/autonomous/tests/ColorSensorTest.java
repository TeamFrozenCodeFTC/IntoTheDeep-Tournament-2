package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class ColorSensorTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("colorLeft", (double)colorLeft.blue());
            telemetry.addData("colorRight", (double)colorRight.blue());
        }
    }
}
