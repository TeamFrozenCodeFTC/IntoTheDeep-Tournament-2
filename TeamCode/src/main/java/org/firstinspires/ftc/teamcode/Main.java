package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main", group="Linear OpMode")
public class Main extends LinearOpMode {
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        IntakeControls intakeControls = new IntakeControls();
        MovementControls movement = new MovementControls();

        waitForStart();

        while (opModeIsActive()) {
            intakeControls.intakeControl();
            movement.movementControls();
        }
    }
}