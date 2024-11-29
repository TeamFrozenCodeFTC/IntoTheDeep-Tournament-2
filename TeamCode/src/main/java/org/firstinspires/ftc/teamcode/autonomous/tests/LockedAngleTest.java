package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class LockedAngleTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        lockedAngle = 90;
        while (gyro.getAngle() < lockedAngle) {
            angleLock(0.7, 0.7, 0.7, 0.7);
        }
        goForward(18, 0.3);
    }
}
