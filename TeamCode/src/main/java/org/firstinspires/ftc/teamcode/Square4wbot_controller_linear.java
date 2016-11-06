//By Jordan
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Sqarebot;

@TeleOp(name="Squarebot Control", group="Sqaurebot Opmode")

public class Square4wbot_controller_linear extends LinearOpMode {

    Sqarebot            robot = new Sqarebot();
    private ElapsedTime runtime = new ElapsedTime();

    boolean State1 = false;
    boolean State2 = false;

    @Override
    public void runOpMode() throws InterruptedException {
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
            robot.LMotor1.setPower(gamepad1.left_stick_y);
            robot.LMotor2.setPower(gamepad1.left_stick_y);
            robot.RMotor1.setPower(gamepad1.right_stick_y);
            robot.RMotor2.setPower(gamepad1.right_stick_y);

            // 3.when Rx move along x , the squarebot will move parallel.
            robot.LMotor1.setPower(gamepad1.right_stick_x);
            robot.LMotor2.setPower(-gamepad1.right_stick_x);
            robot.RMotor1.setPower(-gamepad1.right_stick_x);
            robot.RMotor2.setPower(gamepad1.right_stick_x);

            /* Collecting Part
              Press the A in gamepad1 to collect
            */

            State2 = gamepad1.a;

            if (State2 == true && State2 != State1)
            {
                robot.CMotor.setPower(1);
                telemetry.addData("Collecting mode", State2 ? "On" : "Off" );
                telemetry.update();
            }
            State1 = State2;

             /* Shotting Part
               Press the B in gamepad1 to shot a ball
               the Shotting motor rotate in 1 circulation
             */

            if (gamepad1.b)
            {
                robot.SMotor.setPower(1);
                robot.SMotor.getCurrentPosition();

                //ANDYMARKER (1120 revolution) TERIX (1440 revolution)
                robot. SMotor.setTargetPosition(1120);
                robot.SMotor.isBusy();
            }
        }
    }
}
