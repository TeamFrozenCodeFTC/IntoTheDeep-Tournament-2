package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket1 extends AutoBasket {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        dumpFirst();

        level1Ascent();
    }
}
