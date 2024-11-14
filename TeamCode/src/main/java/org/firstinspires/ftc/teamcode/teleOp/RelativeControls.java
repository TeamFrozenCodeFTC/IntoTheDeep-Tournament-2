package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;


@TeleOp
public class RelativeControls extends Robot {
    @Override
    public void runOpMode() {
        initRobot();

        SpecimenControls specimenControls = new SpecimenControls(this);
        RelativeWheelControls relativeWheelControls = new RelativeWheelControls(this);

        waitForStart();

        new Thread(() -> {
            while (opModeIsActive()) {
                specimenControls.run();
            }
        }).start();

        while (opModeIsActive()) {
            relativeWheelControls.control();
        }

    }
}
