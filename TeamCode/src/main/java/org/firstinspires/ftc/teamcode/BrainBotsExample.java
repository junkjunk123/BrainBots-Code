// Basic packages and libraries you are importing to code this properly
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

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
    }

    // This happens when you actually RUN the code, after the init stage
    @Override
    public void loop() {
        // Sets 100% power to all 4 wheels, should move forward if all motors are going in the same direction
        leftFront.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        leftRear.setPower(1);
    }
}
