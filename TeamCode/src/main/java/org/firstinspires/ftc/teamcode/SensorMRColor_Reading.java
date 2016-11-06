//By Jordan
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


@Autonomous(name = "Sensor: Sensor RGB", group = "Sensor")
public class SensorMRColor_Reading extends LinearOpMode {

  HARDWARE_CompRobot1 robot = new HARDWARE_CompRobot1(); //changed to new hardware file
  static final int LED_CHANNEL = 5;

  @Override
  public void runOpMode() throws InterruptedException {
    robot.init(hardwareMap);

    // wait for the start button to be pressed.
    waitForStart();

    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F,0F,0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // get a reference to the RelativeLayout so we can change the background
    // color of the Robot Controller app to match the hue detected by the RGB Sensor.
    final View relativeLayout = ((Activity)
            hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);

    // while the op mode is active, loop and read the RGB data.
    /* Note we use opModeIsActive() as our loop condition because it is an interruptible
    method*/
    while (opModeIsActive()) {

      // convert the RGB values to HSV values.
      Color.RGBToHSV((robot.sensorRGB.red() * 255) / 800, (robot.sensorRGB.green() * 255) / 800, (robot.sensorRGB.blue() * 255) / 800, hsvValues);

      // send the info back to driver station using telemetry function.
      telemetry.addData("Clear", robot.sensorRGB.alpha());
      telemetry.addData("Red ", robot.sensorRGB.red());
      telemetry.addData("Green", robot.sensorRGB.green());
      telemetry.addData("Blue ", robot.sensorRGB.blue());
      telemetry.addData("Hue", hsvValues[0]);
      telemetry.update();
      relativeLayout.post(new Runnable() {
        public void run() {
          relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
        }
      });

    }

  }
}
