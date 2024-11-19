package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.LinearEquation;

@TeleOp
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo;
        // servo = hardwareMap.get(Servo.class, "dumperServo");
        servo = hardwareMap.get(Servo.class, "sweeperRotator");

        LinearEquation servoMap = new LinearEquation(1, 1, -1, 0);

        waitForStart();

        while(opModeIsActive()) {
            double pos = servoMap.solve(gamepad1.left_stick_y);
            servo.setPosition(pos);
            telemetry.addData("position", pos);
            telemetry.update();
        }
    }
}
