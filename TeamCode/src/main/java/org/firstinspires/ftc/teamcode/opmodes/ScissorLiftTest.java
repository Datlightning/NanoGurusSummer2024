package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp
public class ScissorLiftTest extends LinearOpMode {
    protected DcMotor scissorLift;
    public static double MOTOR_SPEED = 0;
    public void runOpMode(){
        scissorLift = hardwareMap.get(DcMotor.class, "scissor_motor");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            if(MOTOR_SPEED == 0){
                scissorLift.setPower(gamepad1.left_stick_y);
            }else{
                scissorLift.setPower(MOTOR_SPEED);
            }
            telemetry.addData("Gamepad 1 Y", gamepad1.left_stick_y);
            telemetry.addData("motor speed", MOTOR_SPEED);
            telemetry.update();
        }
    }
}
