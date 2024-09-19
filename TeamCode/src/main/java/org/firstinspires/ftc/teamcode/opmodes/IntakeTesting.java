package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Config
@TeleOp
public class IntakeTesting extends LinearOpMode {
    Intake intake;
    public static double claw_position = 0.3;
    public static double wrist_position = 0.3;
    public static double motor_power = 0;
    public static int target_position = 0;
    public static double roller_speed = 0.5;
    DcMotor frontLeft, frontRight, backRight, backLeft;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        intake = new Intake(hardwareMap, telemetry);
        intake.init();
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            if(gamepad1.a){
                intake.moveWrist(0.5 + roller_speed);
            }else if(gamepad1.b){
                intake.moveWrist(0.5 - roller_speed);
            }else{
                intake.moveWrist(wrist_position);

            }
            frontRight.setPower(-gamepad1.right_stick_y/3.0);
            backRight.setPower(-gamepad1.right_stick_y/3.0);

            frontLeft.setPower(-gamepad1.left_stick_y/3.0);
            backLeft.setPower(-gamepad1.left_stick_y/3.0);

            intake.telemetry();
            telemetry.update();
        }
    }
}
