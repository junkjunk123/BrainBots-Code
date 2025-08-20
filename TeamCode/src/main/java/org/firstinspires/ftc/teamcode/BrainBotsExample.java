// Basic packages and libraries you are importing to code this properly
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// Will be assigned to the TeleOp section in your Rev controller
@TeleOp

// You must have a public class that extends OpMode for it to show up on the robot
public class BrainBotsExample extends OpMode {

    // Create variables in the class, especially the hardware devices
    // "Private" creates a variable only available in this file
    // DcMotorEx is the type of hardware device, in this case a motor that will move the wheels known as a DcMotor
    // leftFront is the variable name we are assigning and we will use in the rest of the file to refer to this motor
    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftRear;
    private DcMotorEx rightRear;
    private Servo servo1;
    private Servo servo2;

    private GoBildaPinpointDriver pinpoint;

    // Init runs in the initialization, when you press RUN on the Rev controller and it "gets ready to run"
    @Override
    public void init() {
        // Initialize the motors using the hardwareMap
        // The leftFront motor variable is assigned to the device called "lf", which must align with a port connected to "lf" in your Rev controller config
        // This sends the power and signal for a motor to move to the correct motor
        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");
        rightRear = hardwareMap.get(DcMotorEx.class, "rr");
        leftRear = hardwareMap.get(DcMotorEx.class, "lr");

        //Initialize the servos using the hardwareMap
        // Same thing as motors but for servos
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");


        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
    }

    // This happens when you actually RUN the code, after the init stage
    @Override
    public void loop() {
        double x = gamepad1.left_stick_x * 1.1;
        double y = -gamepad1.left_stick_y;
        double rotation = gamepad1.right_stick_x;

        double leftFrontPower = y + x + rotation;
        double rightFrontPower = y - x - rotation;
        double leftRearPower = y - x + rotation;
        double rightRearPower = y + x - rotation;

        double maxPower = Math.max(Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower)),
                Math.max(Math.abs(leftRearPower), Math.abs(rightRearPower)));
        double denominator = Math.max(maxPower, 1.0);

        // Set the power for each motor based on the joystick inputs
        leftFront.setPower(leftFrontPower / denominator);
        rightFront.setPower(rightFrontPower / denominator);
        leftRear.setPower(leftRearPower / denominator);
        rightRear.setPower(rightRearPower / denominator);

        pinpoint.update();

        double xPosition = pinpoint.getPosX(DistanceUnit.INCH);
        double yPosition = pinpoint.getPosY(DistanceUnit.INCH);
        double heading = pinpoint.getHeading(AngleUnit.DEGREES);

        if (gamepad1.aWasPressed()) {
            servo1.setPosition(0.5);
        }

        if (gamepad1.bWasPressed()) {
            servo2.setPosition(1.0);
        }

        if (gamepad2.aWasPressed()) {
            leftFront.setPower(1);
        }

        if (gamepad2.xWasPressed()) {
            rightFront.setPower(1.0);
        }

        if (gamepad2.bWasPressed()) {
            rightRear.setPower(1.0);
        }

        if (gamepad2.yWasPressed()) {
            leftRear.setPower(1.0);
        }

        // Sets 100% power to all 4 wheels, should move forward if all motors are going in the same direction
        /*
        leftFront.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        leftRear.setPower(1);
         */
    }
}
