package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBlueBasket extends Autonomous {
    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();

        waitForStart();

        goForward(20,0.5);
        turnRight(45,0.5);
        goBackward(20,0.5);

        scoreSpecimen();

        turnRight(45,0.5);
        goForward(92,0.5);
    }
}
