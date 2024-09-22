package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class IntakeControls {
    Servo intakeServo = hardwareMap.get(Servo.class, "intake");

    void intakeControl() {
        boolean a = gamepad2.a;

        intakeServo.setPosition(a ? 1 : 0);
    }
}


