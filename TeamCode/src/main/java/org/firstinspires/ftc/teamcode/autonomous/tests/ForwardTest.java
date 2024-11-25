package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousGyroed;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class ForwardTest extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();

        waitForStart();

        goForward(9,0.3);
        goBackward(9,0.3);
    }
}
