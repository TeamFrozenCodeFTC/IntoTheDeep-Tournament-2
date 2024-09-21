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

    double frontLeftPower = 0;
    double backLeftPower = 0;
    double backRightPower = 0;
    double frontRightPower = 0;

    ArrayList<Double[][]> controls = new ArrayList<>();

    double rightStickXPressTime = 0;
    double leftStickYPressTime = 0;
    double rightTriggerPressTime = 0;
    double leftTriggerPressTime = 0;

    double secondsToFullSpeed = 3;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

        waitForStart();

        while (opModeIsActive()) {
            calculateButtonHoldTimes();

            horizontalSlideControl();
            verticalControl();
            pivotControl();
            boostControl();

            frontLeftPower = 0;
            backLeftPower = 0;
            backRightPower = 0;
            frontRightPower = 0;
            for (Double[][] control : controls) {
                frontLeftPower += control[0][0];
                backLeftPower += control[1][0];
                backRightPower += control[1][1];
                frontRightPower += control[0][1];
            }

            // Set Motor Wheel Power
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            //telemetry.addData("Status");
            telemetry.update();
        }
    }

    void calculateButtonHoldTimes() {
        Map<String, Double[]> pressTimes = new HashMap<>();
        pressTimes.put("rightStickXPressTime", new Double[] {(double)gamepad1.right_stick_x, rightStickXPressTime});
        pressTimes.put("leftStickYPressTime", new Double[] {(double)gamepad1.left_stick_y, leftStickYPressTime});
        pressTimes.put("leftTriggerPressTime", new Double[] {(double)gamepad1.left_trigger, leftTriggerPressTime});
        pressTimes.put("rightTriggerPressTime", new Double[] {(double)gamepad1.right_trigger, rightTriggerPressTime});

        for (Map.Entry<String, Double[]> entry : pressTimes.entrySet()) {
            String key = entry.getKey();

            Double[] value = entry.getValue();
            Double button = value[0];
            Double timePressed = value[1];

            boolean isPressed = button != 0;

            if (isPressed) {
                if (timePressed == 0) {
                    timePressed = runtime.time();
                }
                timePressed = runtime.time() - timePressed;
            }
            else {
                timePressed = 0.0;
            }

            pressTimes.put(key, new Double[] {button, timePressed});
        }

        rightStickXPressTime = pressTimes.get("rightStickXPressTime")[1];
        leftStickYPressTime = pressTimes.get("leftStickYPressTime")[1];
        leftTriggerPressTime = pressTimes.get("leftTriggerPressTime")[1];
        rightTriggerPressTime = pressTimes.get("rightTriggerPressTime")[1];
    }


    void boostControl() {
        boolean boost = gamepad1.right_bumper;
        if (boost) {
            frontLeftPower *= secondsToFullSpeed;
            frontRightPower *= secondsToFullSpeed;
            backLeftPower *= secondsToFullSpeed;
            backRightPower *= secondsToFullSpeed;
        }
    }

    void pivotControl() {
        double pivot = -gamepad1.right_stick_x * rightStickXPressTime / secondsToFullSpeed;

        controls.add(new Double[][] {
                {pivot, pivot},
                {pivot, pivot}
        });
    }

    void verticalControl() {
        double y = gamepad1.left_stick_y * leftStickYPressTime / secondsToFullSpeed;

        controls.add(new Double[][] {
                {y, -y},
                {y, -y}
        });
    }

    void horizontalSlideControl() {
        double leftTrigger = gamepad1.left_trigger * leftTriggerPressTime / secondsToFullSpeed;
        double rightTrigger = gamepad1.right_trigger * rightTriggerPressTime / secondsToFullSpeed;

        double slide = leftTrigger - rightTrigger;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }
}