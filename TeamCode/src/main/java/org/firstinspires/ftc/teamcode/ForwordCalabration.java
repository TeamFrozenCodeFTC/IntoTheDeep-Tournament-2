package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ForwordCalabration extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.a){
                frontLeftWheel.setPower(0.5);
                frontRightWheel.setPower(0.5);
                backLeftWheel.setPower(0.5);
                backRightWheel.setPower(0.5);

            } else{
                frontLeftWheel.setPower(0);
                frontRightWheel.setPower(0);
                backLeftWheel.setPower(0);
                backRightWheel.setPower(0);
            }

            telemetry.addData("ticks", backRightWheel.getCurrentPosition());
            telemetry.update();
        }
    }
}
