package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Leon Wu on 10/29/2016.
 */

@TeleOp(name="xHolonomic Control", group="xHolonomic Opmode")  // @Autonomous(...) is the other common choice
//@Disabled

public class TELEOP_linear_xHolonomicSimpleControl extends LinearOpMode{

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    HARDWARE_xHolonomic_demo robot    = new HARDWARE_xHolonomic_demo();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        float FrontLeft, FrontRight, BackRight, BackLeft;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize TemplateOpMode_Linearthe hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            float leftStickY = -gamepad1.left_stick_y;
            float leftStickX = -gamepad1.left_stick_x;
            float rightStickX = -gamepad1.right_stick_x;

            FrontLeft = -leftStickY - leftStickX - rightStickX;
            FrontRight = leftStickY - leftStickX - rightStickX;
            BackLeft = -leftStickY + leftStickX - rightStickX;
            BackRight = leftStickY + leftStickX - rightStickX;

            robot.leftfMotor.setPower(FrontLeft);
            robot.leftbMotor.setPower(BackLeft);
            robot.rightfMotor.setPower(FrontRight);
            robot.rightbMotor.setPower(BackRight);

            telemetry.addData("leftStickY: %.2f", leftStickY);
            telemetry.addData("leftStickX: %.2f", leftStickX);
            telemetry.addData("rightStickX: %.2f", rightStickX);
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}
