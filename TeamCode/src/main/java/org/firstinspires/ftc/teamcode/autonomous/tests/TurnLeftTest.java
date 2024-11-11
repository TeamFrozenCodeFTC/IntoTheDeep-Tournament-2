package org.firstinspires.ftc.teamcode.autonomous.tests;

import org.firstinspires.ftc.teamcode.autonomous.Autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class TurnLeftTest extends Autonomous {
    @Override
    public void runOpMode() {
        initHardware();
        
        waitForStart();
        
        turnLeft(360,0.3);

    }
}
