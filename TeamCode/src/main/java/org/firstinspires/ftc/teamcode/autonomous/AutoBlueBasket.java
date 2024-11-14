package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBlueBasket extends Autonomous {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        goForward(24,0.5);
        turnRight(42,0.5);
        goBackward(24,0.5);

        scoreSpecimen();

        turnRight(45,0.5);
        goForward(92,0.5);
    }
}
