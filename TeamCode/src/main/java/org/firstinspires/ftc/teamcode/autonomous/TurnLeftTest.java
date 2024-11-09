package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class TurnLeftTest extends Autonomous {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        
        waitForStart();
        
        turnLeft(360,0.3);

    }
}
