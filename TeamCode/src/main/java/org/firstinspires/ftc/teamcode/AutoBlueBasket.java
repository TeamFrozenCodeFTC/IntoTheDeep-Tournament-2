package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutoBlueBasket extends Robot{
    @Override
    public void runOpMode() throws InterruptedException {

       initHardware();
       AutonomousMovement auto = new AutonomousMovement(this);

       waitForStart();
       // go forward for 20 inches
        auto.goForward(20,0.5);
        // turn right 45 degrees
        auto.turnRight(45,0.5);
        // go backwards 20 inches
        auto.goBackward(20,0.5);
        auto.releaseBlock();
        auto.setArm(2);
        auto.tiltDumper();
        auto.setArm(0);

        auto.turnRight(45,0.5);
        auto.goForward(92,0.5);

    }
}
