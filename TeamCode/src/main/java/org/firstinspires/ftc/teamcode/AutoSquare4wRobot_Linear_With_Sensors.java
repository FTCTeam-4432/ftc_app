// By Jrodan
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


@Autonomous(name="SquareBot: Linear by Light Sensor", group="SquareBot")

public class AutoSquare4wRobot_Linear_With_Sensors extends LinearOpMode {

    Sqarebot         robot   = new Sqarebot();

    LightSensor lightSensor;
    ColorSensor SensorRGB;
    DeviceInterfaceModule cdim;
    int                               red =  SensorRGB.red();
    int                              blue = SensorRGB.blue();
    static final int          LED_CHANNEL = 5;
    static final double     FORWARD_SPEED = 0.3;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        cdim = hardwareMap.deviceInterfaceModule.get("Dim");
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        SensorRGB = hardwareMap.colorSensor.get("Sensor_RGB");
        cdim.setDigitalChannelState(LED_CHANNEL, false);
        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity)
                hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);


        lightSensor = hardwareMap.lightSensor.get("Sensor_Light");
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

            while(lightSensor.getLightDetected() < 0.32  ){

                telemetry.addData("Raw", lightSensor.getRawLightDetected());
                telemetry.addData("Normal", lightSensor.getLightDetected());
                telemetry.update();
            }

            robot.LMotor1.setPower(0);
            robot.LMotor2.setPower(0);
            robot.RMotor1.setPower(0);
            robot.RMotor2.setPower(0);

            if (red >= 1000 ||blue >= 1200) {

                robot.Finger.setPosition(0.5);

            }
            if (red >= 1000){

            /* Move to the Color sensor side.(RIGHT SIDE)
            IF the sensor is in left. It can be changed to the :
            robot.LMotor1.setPower(FORWARD_SPEED);
            robot.LMotor2.setPower(-FORWARD_SPEED);
            robot.RMotor1.setPower(-FORWARD_SPEED);
            robot.RMotor2.setPower(FORWARD_SPEED);
            */

            robot.LMotor1.setPower(FORWARD_SPEED);
            robot.LMotor2.setPower(-FORWARD_SPEED);
            robot.RMotor1.setPower(-FORWARD_SPEED);
            robot.RMotor2.setPower(FORWARD_SPEED);
            }


            sleep(1000);
            idle();
        }
    }
}
