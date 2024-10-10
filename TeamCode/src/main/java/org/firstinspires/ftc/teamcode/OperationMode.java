package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends LinearOpMode {
    // TODO Check to make sure one of the wheels is not connected to multiple wheels.
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;

    DcMotor intakeMotor;

    Servo intakeServo;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        initHardware();

        IntakeControls intakeControls = new IntakeControls(this);
        MovementControls movement = new MovementControls(this);

        waitForStart();

        while (opModeIsActive()) {
            intakeControls.intakeControl();
            movement.movementControls();

            if(gamepad2.left_bumper ){
                intakeMotor.setPower(-1.0);
            } else if (gamepad2.right_bumper) {
                intakeMotor.setPower(1.0);
            } else{
                intakeMotor.setPower(0);
            }

        }
    }

    void initHardware() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");
        intakeMotor =  hardwareMap.get(DcMotor.class, "intakeMotor");
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        intakeServo = hardwareMap.get(Servo.class, "intake");
    }
}