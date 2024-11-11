package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBlueObservation extends Autonomous {
    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();

        double power = 0.5;

        goForward(24, power);
        turnRight(90, power);
        goBackward(24*2, power);
        turnLeft(45, power);
        goBackward(24, power);

        scoreSpecimen();

        intake.armOut();
        turnLeft(45, power);
        goForward(24, power);
        intake.spinSweeperIn();
        sleep(2000);
        intake.armIn();
        intake.waitForExtension();

        turnRight(45,power);
        goForward(92,power);
    }
}
