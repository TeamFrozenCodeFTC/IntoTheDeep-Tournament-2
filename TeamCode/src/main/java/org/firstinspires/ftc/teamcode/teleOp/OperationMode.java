package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends Robot {
    @Override
    public void runOpMode() {
        initHardware();

        SpecimenControls specimenControls = new SpecimenControls(this);
        WheelControls wheelControls = new WheelControls(this);

        intake.armIn();
        intake.moveExtenderBack();

        waitForStart();

        new Thread(() -> {
            while (opModeIsActive()) {
                specimenControls.run();
            }
        }).start();

        while (opModeIsActive()) {
            wheelControls.control();

            // Get ticks to inches ratio
//            telemetry.addData("linear slide complete", linearSlide.completedExtension);
//            telemetry.addData("intake complete", intake.completedExtension);
//            telemetry.addData("frontLeft ticks", backRightWheel.getCurrentPosition());
//            telemetry.addData("intake ticks", intakeExtender.getCurrentPosition());
//            telemetry.addData("linear slide ticks", linearSlideMotor.getCurrentPosition());
//            telemetry.update();
        }
    }
}
