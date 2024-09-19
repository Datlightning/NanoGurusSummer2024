package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotConstant;

@Config
public class Intake extends Subsystem{
    protected PIDMotor armMotor;
    protected RobotConstant robot;
    protected Servo claw;
    protected Servo wrist;
    public static double P = 0.0034;
    public static double I = 0.01;
    public static double D = 0;
    public static double F = 0.0005;
    protected Telemetry telemetry;
    public Intake(HardwareMap hardwareMap, Telemetry telemetry){
        robot = new RobotConstant();
        armMotor = new PIDMotor(hardwareMap, telemetry, robot.arm_motor);
        armMotor.setPIDF(P, I, D, F);
        claw = hardwareMap.get(Servo.class, robot.claw_servo);
        wrist = hardwareMap.get(Servo.class, robot.wrist_servo);
        armMotor.setMin(-500);
        armMotor.setMax(50);
        this.telemetry = telemetry;
    }
    public void moveArm(int targetPosition){
        armMotor.move_async(targetPosition);
    }
    public void setPower(double power){
        armMotor.setPower(power);
    }
    public void moveClaw(double position){
        claw.setPosition(position);
    }
    public void moveWrist(double position){
        wrist.setPosition(position);
    }
    @Override
    public void update() {
        armMotor.update();
    }

    @Override
    public void init() {
        armMotor.init();
        moveClaw(robot.claw_closed);
        moveWrist(robot.wrist_up);
        moveArm(0);


    }

    @Override
    public void telemetry() {
        telemetry.addData("Claw Position", claw.getPosition());
        telemetry.addData("Wrist Position", wrist.getPosition());
        armMotor.telemetry();

    }
}
