package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousRelative;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class RelativeTest extends AutonomousRelative {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        turnRight(90,0.3);
        turnLeft(90,0.3);
    }
}