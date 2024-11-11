package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class TurnRightTest extends Autonomous {
    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();

        turnRight(360,0.3);
    }
}
