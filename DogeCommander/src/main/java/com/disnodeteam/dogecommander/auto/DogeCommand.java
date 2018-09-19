package com.disnodeteam.dogecommander.auto;

import com.disnodeteam.dogecommander.UniLogger;
import com.disnodeteam.dogecommander.hardware.DogeBot;

/**
 * Created by Victo on 8/12/2018.
 */

public abstract class DogeCommand {
    public DogeBot bot;
    public DogeCommander commander;
    public boolean overrideLoop = false;

    public DogeCommand(){

    }
    public void Init(DogeCommander commander){
        this.commander = commander;
        this.bot = commander.bot;

        UniLogger.Log("DogeCommander", "Init DogeCommand: " + this.getClass().getSimpleName() + " using bot " + this.bot.getClass().getSimpleName());

    }
    public void setOverrideLoop(boolean va){
        overrideLoop = overrideLoop;
    }

    public void Run(){
        start();
        UniLogger.Log("DogeCommander", "Running Command: " + this.getClass().getSimpleName());
        if(!overrideLoop){

            while(canRunLoop()){
                loop();
            }
            stop();
            UniLogger.Log("DogeCommander", "Stopped Command: " + this.getClass().getSimpleName());
        }

    }
    public abstract void start();
    public abstract void loop();
    public abstract void stop();

    public abstract boolean isTaskRunning();
    public boolean canRunLoop(){
        return commander.getOpModeStop() && isTaskRunning();
    }
}
