//By Jordan
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Sqarebot
{
    /* Public OpMode members. */
    //Drive Motors
    public DcMotor LMotor1     = null; // Front Left Motor
    public DcMotor LMotor2     = null; // Back Left Motor
    public DcMotor RMotor1     = null; // Front Right Motor
    public DcMotor RMotor2     = null; // Back Right Motor
    public Servo   Finger      = null; //Servo
    public DcMotor  SMotor     = null; //Shot Motor
    public DcMotor  CMotor     = null; //Collect Motor

    public static final double MID_SERVO          =  0 ;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Sqarebot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        LMotor1     = hwMap.dcMotor.get("LMotor_1");
        LMotor2     = hwMap.dcMotor.get("LMotor_2");
        RMotor1     = hwMap.dcMotor.get("RMotor_1");
        RMotor2     = hwMap.dcMotor.get("RMotor_2");
        SMotor      = hwMap.dcMotor.get("SMotor");
        CMotor      = hwMap.dcMotor.get("CMotor");

        LMotor1.setPower(0);
        LMotor2.setPower(0);
        RMotor1.setPower(0);
        RMotor2.setPower(0);



        LMotor1.setDirection(DcMotor.Direction.REVERSE);
        LMotor1.setDirection(DcMotor.Direction.REVERSE);
        RMotor1.setDirection(DcMotor.Direction.FORWARD);
        RMotor2.setDirection(DcMotor.Direction.FORWARD);
        SMotor.setDirection(DcMotor.Direction.FORWARD);
        CMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        LMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        CMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        SMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        Finger      = hwMap.servo.get("Finger");
        Finger.setPosition(MID_SERVO);

    }

    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

