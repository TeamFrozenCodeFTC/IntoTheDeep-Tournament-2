package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class TurnRightTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        turnRight(90,0.3);
        turnLeft(90,0.3);
    }
}