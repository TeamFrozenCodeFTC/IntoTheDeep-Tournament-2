package org.firstinspires.ftc.teamcode.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.LinearEquation;

@TeleOp
public class GeneralServoTest extends LinearOpMode {

    class ServoTester implements ComponentTester {
        LinearEquation servoMap;
        Servo servo;

        public ServoTester() {
            servoMap = new LinearEquation(1, 1, -1, 0);
        }

        @Override
        public void setComponent(String name) {
            servo = hardwareMap.get(Servo.class, name);
        }

        @Override
        public void test() {
            double pos = servoMap.solve(gamepad1.left_stick_y);
            servo.setPosition(pos);
            telemetry.addData("Servo Position", pos);
        }
    }

    class MotorTester implements ComponentTester {
        DcMotor motor;

        @Override
        public void setComponent(String name) {
            motor = hardwareMap.get(DcMotor.class, name);
            ;
        }

        @Override
        public void test() {
            double power = gamepad1.left_stick_y;
            motor.setPower(power);
            telemetry.addData("Motor Power", power);
        }
    }

    class ComponentSet {
        public String[] componentNames;
        public String setName;
        public ComponentTester tester;

        public ComponentSet(String setName, String[] componentNames, ComponentTester tester) {
            this.componentNames = componentNames;
            this.setName = setName;
            this.tester = tester;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        ComponentSet[] sets = {
                new ComponentSet(
                        "Servo",
                        new String[]{
                                "sweeperRotator",
                                "dumperServo",
                                "clawLeft",
                                "clawRight"
                        },
                        new ServoTester()
                ),
                new ComponentSet(
                        "Motor",
                        new String[]{
                                "frontLeft",
                                "backLeft",
                                "frontRight",
                                "backRight"
                        },
                        new MotorTester()
                )
        };

        waitForStart();

        boolean downProcessed = false;
        boolean upProcessed = false;
        boolean rightProcessed = false;
        boolean leftProcessed = false;
        int setIndex = 0;
        int currentIndex = 0;

        ComponentSet cset = sets[setIndex];
        String currentSet = cset.setName;
        String[] currentComponents = cset.componentNames;
        String currentComponent = currentComponents[currentIndex];
        ComponentTester currentTester = cset.tester;

        while (opModeIsActive()) {
            if (gamepad1.dpad_right) {
                if (!rightProcessed) {
                    rightProcessed = true;

                    setIndex++;
                    if (setIndex >= sets.length) {
                        setIndex = 0;
                    }

                    currentIndex = 0;
                    cset = sets[setIndex];
                    currentSet = cset.setName;
                    currentComponents = cset.componentNames;
                    currentComponent = currentComponents[currentIndex];
                    currentTester = cset.tester;
                    currentTester.setComponent(currentComponent);
                }
            } else {
                rightProcessed = false;
            }

            if (gamepad1.dpad_left) {
                if (!leftProcessed) {
                    leftProcessed = true;

                    setIndex--;
                    if (setIndex < 0) {
                        setIndex = sets.length - 1;
                    }

                    currentIndex = 0;
                    cset = sets[setIndex];
                    currentSet = cset.setName;
                    currentComponents = cset.componentNames;
                    currentComponent = currentComponents[currentIndex];
                    currentTester = cset.tester;
                    currentTester.setComponent(currentComponent);
                }
            } else {
                leftProcessed = false;
            }

            if (gamepad1.dpad_down) {
                if (!downProcessed) {
                    downProcessed = true;

                    currentIndex++;
                    if (currentIndex >= currentComponents.length) {
                        currentIndex = 0;
                    }
                    currentComponent = currentComponents[currentIndex];
                    currentTester.setComponent(currentComponent);
                }
            } else {
                downProcessed = false;
            }

            if (gamepad1.dpad_up) {
                if (!upProcessed) {
                    upProcessed = true;

                    currentIndex--;
                    if (currentIndex < 0) {
                        currentIndex = currentComponents.length - 1;
                    }
                    currentComponent = currentComponents[currentIndex];
                    currentTester.setComponent(currentComponent);
                }
            } else {
                upProcessed = false;
            }

            telemetry.addData("Component Type", currentSet);
            telemetry.addData("Testing", currentComponent);
            currentTester.test();
            telemetry.update();
        }
    }
}