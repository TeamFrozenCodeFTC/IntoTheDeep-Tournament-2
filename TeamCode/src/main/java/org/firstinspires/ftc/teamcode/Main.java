package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


@TeleOp(name="Main", group="Linear OpMode")
public class Main extends LinearOpMode {

    DcMotor frontLeftMotor = hardwareMap.get(DcMotor .class, "frontLeft");
    DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
    DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
    DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

    Gamepad gamePad1 = gamepad1;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        IntakeControls intakeControls = new IntakeControls();
        MovementControls movement = new MovementControls(this);

        waitForStart();

        while (opModeIsActive()) {
//            intakeControls.intakeControl();
            movement.movementControls();
        }
    }
}