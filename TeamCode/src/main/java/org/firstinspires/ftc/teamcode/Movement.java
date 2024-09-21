package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@TeleOp(name="Movement", group="Linear OpMode")
public class Movement extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontRightMotor;

    DcMotor intakeMotor;

    double frontLeftPower = 0;
    double backLeftPower = 0;
    double backRightPower = 0;
    double frontRightPower = 0;

    ArrayList<Double[][]> controls = new ArrayList<>();

    double slowerFactor = 3;
    double factor = 0.75;
    double boostFactor = 3;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        waitForStart();

        while (opModeIsActive()) {
            horizontalSlideControl();
            verticalControl();
            pivotControl();
            intakeControl();

            frontLeftPower = 0;
            backLeftPower = 0;
            backRightPower = 0;
            frontRightPower = 0;
            for (Double[][] control : controls) {
                frontLeftPower -= control[0][0];
                backLeftPower += control[1][0];
                backRightPower += control[1][1];
                frontRightPower += control[0][1];
            }

            boostControl();
            slowControl();

            // Set Motor Wheel Power
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            controls = new ArrayList<>();
        }
    }

    void slowControl() {
        boolean slow = gamepad1.left_bumper;
        if (slow) {
            frontLeftPower *= slowerFactor;
            frontRightPower *= slowerFactor;
            backLeftPower *= slowerFactor;
            backRightPower *= slowerFactor;
        }
    }

   void boostControl() {
        boolean boost = gamepad1.right_bumper;
        if (boost) {
            frontLeftPower *= boostFactor;
            frontRightPower *= boostFactor;
            backLeftPower *= boostFactor;
            backRightPower *= boostFactor;
        }
    }

    void pivotControl() {
        double pivot = gamepad1.right_stick_x * factor;

        controls.add(new Double[][] {
                {pivot, -pivot},
                {pivot, -pivot}
        });
    }

    void verticalControl() {
        double y = -gamepad1.left_stick_y * factor;

        controls.add(new Double[][] {
                {y, y},
                {y, y}
        });
    }

    void horizontalSlideControl() {
        double leftTrigger = gamepad1.left_trigger * factor;
        double rightTrigger = gamepad1.right_trigger * factor;

        double slide = leftTrigger - rightTrigger;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }

    void intakeControl() {
        boolean a = gamepad1.a;

        intakeMotor.setPower(a ? 1 : 0);
    }
}