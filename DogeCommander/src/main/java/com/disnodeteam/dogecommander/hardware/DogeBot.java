package com.disnodeteam.dogecommander.hardware;

import com.disnodeteam.dogecommander.UniLogger;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

/**
 * Created by Victo on 8/12/2018.
 */

public abstract class DogeBot {
    public HardwareMap hardwareMap;
    public HashMap<String, DogeSubsystem> subsystems = new HashMap<>();

    public DogeBot(HardwareMap hwd){
        hardwareMap = hwd;
    }

    public void init() {
        hardwareInit();
    }

    public void addSubsystem(DogeSubsystem subsystem) {
        UniLogger.Log("DogeCommander", "Adding system subsystems: " + this.getClass().getSimpleName() + "/" + subsystem.name);
        subsystems.put(subsystem.name, subsystem);
    }

    public DogeSubsystem getSubSystem(String name){
        DogeSubsystem found = subsystems.get(name);
        return found;
    }

    private void hardwareInit(){
        UniLogger.Log("DogeCommander", "Running hardwareInit for subsystems: " + this.getClass().getSimpleName());


        for(DogeSubsystem subsystem : subsystems.values()){
            subsystem.hardwareInit(hardwareMap);
            UniLogger.Log("DogeCommander", "Ran hardwareInit for subsystems: " + this.getClass().getSimpleName() + "/" + subsystem.name);
        }
    }
}
