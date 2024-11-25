package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousGyroed;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class SlideTest extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        goForward(8, 0.3);

        lockedAngle = 90;
        goForward(8, 0.3);

        slideRight(8, 0.3);
    }
}
