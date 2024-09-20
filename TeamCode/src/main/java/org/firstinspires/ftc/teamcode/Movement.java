package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Movement", group="Linear OpMode")
public class Movement extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontRightMotor;

    double frontLeftPower = 0;
    double backLeftPower = 0;
    double backRightPower = 0;
    double frontRightPower = 0;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeftMotor = hardwareMap.get(DcMotor.class, "leftFront");
        backLeftMotor = hardwareMap.get(DcMotor.class, "leftBack");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rightFront");
        backRightMotor = hardwareMap.get(DcMotor.class, "rightBack");

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            horizontalSlideControl();
            verticalControl();
            pivotControl();

            // Set Motor Wheel Power
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Reset Variables
            frontLeftPower = 0;
            backLeftPower = 0;
            backRightPower = 0;
            frontRightPower = 0;

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    void addFrontWheelsPower(double left, double right) {
        frontLeftPower += left;
        frontRightPower += right;
    }

    void addBackWheelsPower(double left, double right) {
        backLeftPower += left;
        backRightPower += right;
    }

    void pivotControl() {
        double pivot = gamepad1.right_stick_x;

        addFrontWheelsPower(pivot, pivot);
        addBackWheelsPower(pivot, pivot);
    }

    void verticalControl() {
        double y = gamepad1.left_stick_y;

        addFrontWheelsPower(-y, y);
        addBackWheelsPower(-y, y);
    }

    void horizontalSlideControl() {
        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;

        double slide = leftTrigger - rightTrigger;

        addFrontWheelsPower(-slide, +slide);
        addBackWheelsPower(+slide, -slide);
    }
}