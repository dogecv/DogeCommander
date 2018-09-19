package com.disnodeteam.dogecommander.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Victo on 8/12/2018.
 */

public abstract class DogeSubsystem {
    public static int id = 0;
    public String name = "DogeSubsystem-"+id;

    public DogeSubsystem(String name){
        this.name = name;
        id++;
    }

    public abstract void hardwareInit(HardwareMap map);
}
