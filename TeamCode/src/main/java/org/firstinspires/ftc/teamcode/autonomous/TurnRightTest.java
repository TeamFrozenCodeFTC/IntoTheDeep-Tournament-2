package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class TurnRightTest extends Autonomous {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        waitForStart();

        turnRight(360,0.3);
    }
}
