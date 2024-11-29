package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class SlideTest extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        slideRight(8, 0.3);
        slideLeft(8, 0.3);
    }
}
