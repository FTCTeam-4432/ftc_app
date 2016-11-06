package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


@Autonomous(name = "Sensor: Sensor RGB", group = "Sensor")
public class SensorMRColor_Reading extends LinearOpMode {

  ColorSensor SensorRGB;
  DeviceInterfaceModule cdim;
  static final int LED_CHANNEL = 5;

  @Override
  public void runOpMode() throws InterruptedException {

    hardwareMap.logDevices();

    // get refference to the DeviceInterfaceModule
    cdim = hardwareMap.deviceInterfaceModule.get("dim");

    // set the digital channel to output mode.
    // remember, the AdaFruit Sensor is actually two devices.
    // It's an I2C Sensor and it's also an LED that can be turned on or off.
    cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);

    // get a reference to our ColorSensor object.
    SensorRGB = hardwareMap.colorSensor.get("Sensor_RGB");


    /* turn the LED on in the beginning, just so user will know that the Sensor is
    active.*/
    cdim.setDigitalChannelState(LED_CHANNEL, true);

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
      Color.RGBToHSV((SensorRGB.red() * 255) / 800, (SensorRGB.green() * 255) / 800, (SensorRGB.blue() * 255) / 800, hsvValues);

      // send the info back to driver station using telemetry function.
      telemetry.addData("Clear", SensorRGB.alpha());
      telemetry.addData("Red ", SensorRGB.red());
      telemetry.addData("Green", SensorRGB.green());
      telemetry.addData("Blue ", SensorRGB.blue());
      telemetry.addData("Hue", hsvValues[0]);

    }
    relativeLayout.post(new Runnable() {
      public void run() {
        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
      }
    });

    telemetry.update();

  }
}
