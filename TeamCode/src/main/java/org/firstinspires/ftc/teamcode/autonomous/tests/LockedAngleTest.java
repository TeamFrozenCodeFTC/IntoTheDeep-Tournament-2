package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousGyroed;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class LockedAngleTest extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        lockedAngle = 90;
        goForward(12, 0.3);
    }
}
