package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorLEGOTouch;

/*taken from HardwarePushbot*/

public class HARDWARE_xHolonomic_demo {
    /* Public OpMode members. */
    public DcMotor  leftfMotor   = null;
    public DcMotor  rightfMotor  = null;
    public DcMotor  leftbMotor   = null;
    public DcMotor  rightbMotor  = null;

    public TouchSensor touchSensorf = null;
    public TouchSensor touchSensorb = null;
    public TouchSensor touchSensorr = null;
    public TouchSensor touchSensorl = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HARDWARE_xHolonomic_demo(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftfMotor   = hwMap.dcMotor.get("leftf_drive");
        rightfMotor  = hwMap.dcMotor.get("rightf_drive");
        leftbMotor   = hwMap.dcMotor.get("leftb_drive");
        rightbMotor  = hwMap.dcMotor.get("rightb_drive");
        /*leftfMotor.setDirection(DcMotor.Direction.REVERSE);
        rightfMotor.setDirection(DcMotor.Direction.FORWARD);
        leftbMotor.setDirection(DcMotor.Direction.REVERSE);
        rightbMotor.setDirection(DcMotor.Direction.FORWARD);*/

        // Set all motors to zero power
        leftfMotor.setPower(0);
        rightfMotor.setPower(0);
        leftbMotor.setPower(0);
        rightbMotor.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        touchSensorf = hwMap.touchSensor.get("touch_sensorf");
        touchSensorb = hwMap.touchSensor.get("touch_sensorb");
        touchSensorr = hwMap.touchSensor.get("touch_sensorr");
        touchSensorl = hwMap.touchSensor.get("touch_sensorl");

    }

    public void wiggle1() {
        leftfMotor.setPower(-1);
        leftbMotor.setPower(-1);
        rightfMotor.setPower(-1);
        rightbMotor.setPower(-1);
    }

    public void wiggle2() {
        leftfMotor.setPower(1);
        leftbMotor.setPower(1);
        rightfMotor.setPower(1);
        rightbMotor.setPower(1);
    }

    public void moveForward() {
        leftfMotor.setPower(-1);
        leftbMotor.setPower(-1);
        rightfMotor.setPower(1);
        rightbMotor.setPower(1);
    }

    public void moveBack() {
        leftfMotor.setPower(1);
        leftbMotor.setPower(1);
        rightfMotor.setPower(-1);
        rightbMotor.setPower(-1);
    }

    public void moveLeft() {
        leftfMotor.setPower(-1);
        leftbMotor.setPower(1);
        rightfMotor.setPower(-1);
        rightbMotor.setPower(1);
    }

    public void moveRight() {
        leftfMotor.setPower(1);
        leftbMotor.setPower(-1);
        rightfMotor.setPower(1);
        rightbMotor.setPower(-1);
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

