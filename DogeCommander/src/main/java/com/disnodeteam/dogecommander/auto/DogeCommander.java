package com.disnodeteam.dogecommander.auto;

import com.disnodeteam.dogecommander.hardware.DogeBot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DogeCommander {

    public Telemetry telemetry;
    public DogeBot bot;
    public LinearOpMode linearOpMode;
    public DogeCommander(){
    }

    public void setBot(DogeBot bot){
        this.bot = bot;
        bot.init();
    }

    public void usingLinearOpMode(LinearOpMode opMode){
        linearOpMode = opMode;
    }

    public boolean getOpModeStop(){
        return !linearOpMode.opModeIsActive() || linearOpMode.isStopRequested();
    }

    public void runCommand(DogeCommand command){
        command.Init(this);
        command.Run();
    }

    public void getStatus(){

    }



}
