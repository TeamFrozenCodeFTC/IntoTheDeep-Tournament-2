package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Program", "Started");

        initHardware();

        telemetry.addData("Hardware", "Initialized");

        SpecimenControls specimenControls = new SpecimenControls(this);
        WheelControls wheelControls = new WheelControls(this);

        intake.armIn();
        intake.moveExtenderBack(); // !?

        telemetry.addData("Robot", "Initialized");
        telemetry.update();

        waitForStart();

        new Thread(() -> {
            while (opModeIsActive()) {
                specimenControls.run();
            }
        }).start();

        while (opModeIsActive()) {
            wheelControls.control();

            // Get ticks to inches ratio
            telemetry.addData("linear slide complete", linearSlide.completedExtension);
            telemetry.addData("intake complete", intake.completedExtension);
            telemetry.addData("frontLeft ticks", backRightWheel.getCurrentPosition());
            telemetry.addData("intake ticks", intakeExtender.getCurrentPosition());
            telemetry.addData("linear slide ticks", linearSlideMotor.getCurrentPosition());
            telemetry.addData("dumper Servo", dumperServo.getPosition());
            double radians = gyro.getAngle() * Math.PI/180;
            telemetry.addData("angle",
                    (float)Math.cos(radians)
            );
            telemetry.addData("angle2",
                     (float)Math.sin(radians)
            );
            telemetry.update();
        }
    }
}
