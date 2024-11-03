package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends Robot {
    @Override
    public void runOpMode() {
        telemetry.addData("Program", "Started");

        initHardware();

        telemetry.addData("Hardware", "Initialized");

        SpecimenControls specimenControls = new SpecimenControls(this);
        WheelControls wheelControls = new WheelControls(this);

        intake.sweeperArmIn();
        intake.moveExtenderBack(); // !?

        telemetry.addData("Robot", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            wheelControls.control();
            specimenControls.control();

//            if (gamepad2.dpad_down) {
//                intakeControls.intake.sweeperArmIn();
//                sleep(1000);
//                intakeControls.intake.spinSweeperOut();
//                sleep(1000);
//                intakeControls.intake.spinSweeperIn();
//                sleep(200);
//                intakeControls.intake.stopSweeper();
//                intakeControls.intake.sweeperArmOut();
//                sleep(200);
//                linearSlideControls.linearSlide.extend();
//                sleep(5000);
//            }

            // Get ticks to inches ratio
            telemetry.addData("frontLeft ticks", backRightWheel.getCurrentPosition());
            telemetry.addData("intake ticks", intakeExtender.getCurrentPosition());
            telemetry.addData("linear slide ticks", linearSlideMotor.getCurrentPosition());
            telemetry.addData("dumper Servo", dumperServo.getPosition());
            telemetry.update();
        }
    }
}
