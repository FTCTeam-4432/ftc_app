//By Jordan. Modified by Leon Wu 11/6/16
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Competition TeleOp Single", group="Competition Opmode")

public class Square4wbot_controller_linear extends LinearOpMode {

    HARDWARE_CompRobot1 robot = new HARDWARE_CompRobot1(); //changed to new hardware file
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        float FrontLeft, FrontRight, BackRight, BackLeft;
        float colDirection = 1;
        boolean colOn = false;

        robot.beaconer.setPosition(robot.MID_SERVO);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            // use gamepad to control the directions.

            /* 1.when Ly and Ry in opposite movement, the squrebot will rotate clock wise or anti clock wise
               2.when Ly and Ry in same movement direction, Forwards or Backwards
            */
            /*robot.LMotor1.setPower(gamepad1.left_stick_y);
            robot.LMotor2.setPower(gamepad1.left_stick_y);
            robot.RMotor1.setPower(gamepad1.right_stick_y);
            robot.RMotor2.setPower(gamepad1.right_stick_y);

            // 3.when Rx move along x , the squarebot will move parallel.
            robot.LMotor1.setPower(gamepad1.right_stick_x);
            robot.LMotor2.setPower(-gamepad1.right_stick_x);
            robot.RMotor1.setPower(-gamepad1.right_stick_x);
            robot.RMotor2.setPower(gamepad1.right_stick_x);   // disabled due to different driving style*/

            float leftStickY = gamepad1.left_stick_y;
            float leftStickX = -gamepad1.left_stick_x;
            float rightStickX = -gamepad1.right_stick_x;

            FrontLeft = -leftStickY - leftStickX - rightStickX;
            FrontRight = leftStickY - leftStickX - rightStickX;
            BackLeft = -leftStickY + leftStickX - rightStickX;
            BackRight = leftStickY + leftStickX - rightStickX;

            robot.leftfDrive.setPower(FrontLeft);
            robot.leftbDrive.setPower(BackLeft);
            robot.rightfDrive.setPower(FrontRight);
            robot.rightbDrive.setPower(BackRight);

            /* Collecting Part
              Press the A in gamepad1 to collect
            */
            //----------------------------------------------------------
            if (gamepad1.right_bumper)
            {
                colOn = true;
                colDirection = 1;
                /*telemetry.addData("Collecting mode", PressedState ? "On" : "Off" );
                telemetry.update();*/
            }

            if (gamepad1.right_trigger > 0.2){
                colOn = false;
            }

            if (gamepad1.b){
                    colDirection = -1;
            }

            if (!colOn){
                colDirection = 0;
            }

            robot.CMotor.setPower(colDirection);
            //----------------------------------------------------------
             /* Shotting Part
               Press the B in gamepad1 to shot a ball
               the Shotting motor rotate in 1 circulation
             */

            if (gamepad1.left_bumper)
            {
                /*robot.SMotor.setPower(1);
                robot.SMotor.getCurrentPosition();

                //ANDYMARKER (1120 revolution) TERIX (1440 revolution)
                robot. SMotor.setTargetPosition(1120);
                robot.SMotor.isBusy();*/
                robot.Shoot();
            }

            /*end game servo
            oh boy I can't sleep
             */

            if (gamepad1.a){
                robot.beaconer.setPosition(robot.MID_SERVO);
            }

            telemetry.addData("What's ", robot.ods.getLightDetected());
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive())
        }
    }
}
