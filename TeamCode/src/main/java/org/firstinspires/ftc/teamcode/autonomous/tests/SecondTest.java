package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class SecondTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        goForwardsSeconds(1, 0.4);
    }
}
