package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBlueBasket extends Autonomous {
    @Override
    public void runOpMode() throws InterruptedException {

        initHardware();
        //Autonomous auto = new Autonomous(this);

        waitForStart();

        goForward(20,0.5);
        turnRight(45,0.5);
        goBackward(20,0.5);
        releaseBlock();
        setArm(2);
        tiltDumper();
        setArm(0);

        turnRight(45,0.5);
        goForward(92,0.5);
    }
}
