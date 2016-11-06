// By Jordan
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;


@Autonomous(name="SquareBot: Linear by Light Sensor", group="SquareBot")

public class AutoSquare4wRobot_Linear_LEGO extends LinearOpMode {

    Sqarebot         robot   = new Sqarebot();
    LightSensor lightSensor;

    static final double     FORWARD_SPEED = 0.3;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        lightSensor = hardwareMap.lightSensor.get("sensor_light");
        lightSensor.enableLed(true);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start
        waitForStart();

        while (opModeIsActive()) {

            robot.LMotor1.setPower(FORWARD_SPEED);
            robot.LMotor2.setPower(FORWARD_SPEED);
            robot.RMotor1.setPower(FORWARD_SPEED);
            robot.RMotor2.setPower(FORWARD_SPEED);

            while(lightSensor.getLightDetected() < 0.32 ){

                telemetry.addData("Raw", lightSensor.getRawLightDetected());
                telemetry.addData("Normal", lightSensor.getLightDetected());
                telemetry.update();
            }

            robot.LMotor1.setPower(0);
            robot.LMotor2.setPower(0);
            robot.RMotor1.setPower(0);
            robot.RMotor2.setPower(0);

            telemetry.addData("Action", "Complete");
            telemetry.update();
            sleep(1000);
            idle();
        }
    }
}
