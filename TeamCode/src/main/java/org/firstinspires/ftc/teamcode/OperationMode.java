package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends LinearOpMode {

    // TODO Check to make sure one of the wheels is not connected to multiple wheels.
    DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
    DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
    DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
    DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

    // TODO figure out why Reverse isn't working.
    // frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    Servo intakeServo = hardwareMap.get(Servo.class, "intake");

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        IntakeControls intakeControls = new IntakeControls(this);
        MovementControls movement = new MovementControls(this);

        waitForStart();

        while (opModeIsActive()) {
            intakeControls.intakeControl();
            movement.movementControls();
        }
    }
}