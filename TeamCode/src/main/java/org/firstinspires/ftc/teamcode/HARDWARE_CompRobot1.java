package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorLEGOTouch;

/*taken from HardwarePushbot*/

public class HARDWARE_CompRobot1 {
    /* Public OpMode members. */
    public DcMotor  leftfDrive   = null;
    public DcMotor  rightfDrive  = null;
    public DcMotor  leftbDrive   = null;
    public DcMotor  rightbDrive  = null;
    public DcMotor  SMotor       = null;
    public DcMotor  CMotor       = null;

    public Servo    beaconer     = null;

    public OpticalDistanceSensor ods = null;
    public LightSensor LegoLight = null;
    public ColorSensor sensorRGB = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double START_SERVO     =  0;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HARDWARE_CompRobot1(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftfDrive   = hwMap.dcMotor.get("leftfDrive");
        rightfDrive  = hwMap.dcMotor.get("rightfDrive");
        leftbDrive   = hwMap.dcMotor.get("leftbDrive");
        rightbDrive  = hwMap.dcMotor.get("rightbDrive");
        SMotor       = hwMap.dcMotor.get("SMotor");
        CMotor       = hwMap.dcMotor.get("CMotor");

        beaconer = hwMap.servo.get("beaconer");

        ods = hwMap.opticalDistanceSensor.get("Sensor_ODS");
        LegoLight = hwMap.lightSensor.get("Sensor_LegoLight");
        sensorRGB = hwMap.colorSensor.get("sensor_color");

        /*leftfDrive.setDirection(DcMotor.Direction.REVERSE);
        rightfDrive.setDirection(DcMotor.Direction.FORWARD);
        leftbDrive.setDirection(DcMotor.Direction.REVERSE);
        rightbDrive.setDirection(DcMotor.Direction.FORWARD);*/
        CMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftfDrive.setPower(0);
        rightfDrive.setPower(0);
        leftbDrive.setPower(0);
        rightbDrive.setPower(0);
        SMotor.setPower(0);
        CMotor.setPower(0);

        beaconer.setPosition(START_SERVO);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftfDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftbDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightbDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        CMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        SMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

    public void moveForward() {
        leftfDrive.setPower(-1);
        leftbDrive.setPower(-1);
        rightfDrive.setPower(1);
        rightbDrive.setPower(1);
    }

    public void moveBack() {
        leftfDrive.setPower(1);
        leftbDrive.setPower(1);
        rightfDrive.setPower(-1);
        rightbDrive.setPower(-1);
    }

    public void moveLeft() {
        leftfDrive.setPower(-1);
        leftbDrive.setPower(1);
        rightfDrive.setPower(-1);
        rightbDrive.setPower(1);
    }

    public void moveRight() {
        leftfDrive.setPower(1);
        leftbDrive.setPower(-1);
        rightfDrive.setPower(1);
        rightbDrive.setPower(-1);
    }

    public void Shoot() {
        SMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SMotor.setTargetPosition(1120);
        SMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SMotor.setPower(1);
        while(SMotor.isBusy()){
            //doing nothing
        }
        SMotor.setPower(0);
        SMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

