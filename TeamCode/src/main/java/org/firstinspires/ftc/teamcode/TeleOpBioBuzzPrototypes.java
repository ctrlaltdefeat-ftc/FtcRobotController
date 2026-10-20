package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOpBioBuzzPrototypes", group = "StarterBot")
//@Disabled
public class TeleOpBioBuzzPrototypes extends OpMode {
    DcMotorEx intakeMotor;
    DcMotorEx transition;
    Intaker intaker;

    private boolean intaking;

    //    private boolean intake = false;
    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "Intake");
        intaker = new Intaker(intakeMotor);
        intaking = false;
        transition = hardwareMap.get(DcMotorEx.class, "Transfer");
        transition.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transition.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if (gamepad1.y && !intaking) {
            telemetry.addLine("starting intake");
            intaker.runIntake();
            transition.setVelocity(2000);
            intaking = true;
        }

        if (gamepad1.x && intaking) {
            telemetry.addLine("stopping intake");
            intaker.stopIntake();
            transition.setVelocity(0);
            intaking = false;
        }

        if(gamepad1.b && intaking) {
            telemetry.addLine("reversing intake");
            intaker.backoutBall();
        }

    }

}
