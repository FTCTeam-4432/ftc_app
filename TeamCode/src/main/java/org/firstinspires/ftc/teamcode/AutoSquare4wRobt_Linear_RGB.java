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


@Autonomous(name = "Squarebot Linear by Sensor_RGB", group = "Squarebot")
public class AutoSquare4wRobt_Linear_RGB extends LinearOpMode {

  Sqarebot         robot   = new Sqarebot();

  ColorSensor SensorRGB;
  DeviceInterfaceModule cdim;

  int                     red =  SensorRGB.red();
  int                     blue = SensorRGB.blue();

  static final int LED_CHANNEL = 5;
  static final double     FORWARD_SPEED = 0.3;


  @Override
  public void runOpMode() throws InterruptedException {

    robot.init(hardwareMap);

    hardwareMap.logDevices();

    // get refference to the DeviceInterfaceModule
    cdim = hardwareMap.deviceInterfaceModule.get("dim");

    // set the digital channel to output mode.
    // remember, the AdaFruit Sensor is actually two devices.
    // It's an I2C Sensor and it's also an LED that can be turned on or off.
    cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);

    // get a reference to our ColorSensor object.
    SensorRGB = hardwareMap.colorSensor.get("Sensor_RGB");

    // bEnabled represents the state of the LED.
    boolean bEnabled = true;

    /* turn the LED on in the beginning, just so user will know that the Sensor is
    active.*/
    cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);


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

      Color.RGBToHSV((SensorRGB.red() * 255) / 800, (SensorRGB.green() * 255) / 800, (SensorRGB.blue() * 255) / 800, hsvValues);
      relativeLayout.post(new Runnable() {
        public void run() {
          relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
        }
      });
      if (red <= 1000 || blue <= 1200) {
        robot.LMotor1.setPower(FORWARD_SPEED);
        robot.LMotor2.setPower(FORWARD_SPEED);
        robot.RMotor1.setPower(FORWARD_SPEED);
        robot.RMotor2.setPower(FORWARD_SPEED);
      }
      else
      {
        robot.LMotor1.setPower(0);
        robot.LMotor2.setPower(0);
        robot.RMotor1.setPower(0);
        robot.RMotor2.setPower(0);

        robot.Finger.setPosition(1);

      }
    }

  }
}
