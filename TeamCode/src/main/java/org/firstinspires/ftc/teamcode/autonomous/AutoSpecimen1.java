package org.firstinspires.ftc.teamcode.autonomous;


// 8 + 2 = 10
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen1 extends AutoSpecimen {
    @Override
    public void runOpMode() {
        initRobot();
        viperSlide.clawGrab();

        waitForStart();

        hookFirst();
        observationZonePark();
    }
}
