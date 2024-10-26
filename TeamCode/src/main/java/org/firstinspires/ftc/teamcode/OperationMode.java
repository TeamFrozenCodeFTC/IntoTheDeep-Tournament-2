package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends Robot {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        initHardware();

        IntakeControls intakeControls = new IntakeControls(this);
        LinearSlideControls linearSlideControls = new LinearSlideControls(this);
        WheelControls movement = new WheelControls(this);

        waitForStart();

        intakeControls.intake.sweeperArmIn();
//        intakeControls.intake.moveExtenderBack();

        while (opModeIsActive()) {
            movement.wheelControls();
            intakeControls.control();
            linearSlideControls.control();
            linearSlideControls.linearSlide.moveSlide();

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
            telemetry.addData("frontLeft", backRightWheel.getCurrentPosition());
            telemetry.addData("ticks", intakeExtender.getCurrentPosition());
            telemetry.addData("linear slide ticks", linearSlide.getCurrentPosition());
            telemetry.addData("dumper Servo", dumperServo.getPosition());
            // get multiple wheels for accuracy?
            telemetry.update();
        }
    }
}
