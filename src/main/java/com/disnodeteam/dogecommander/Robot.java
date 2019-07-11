package com.disnodeteam.dogecommander;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victo on 8/12/2018.
 */

public abstract class Robot {
    private Set<Subsystem> subsystems = new HashSet<>();
    private DogeCommander commander;

    private Runnable hardwareUpdater = new Runnable() {
        @Override
        public void run() {
            while (commander.isRunning()) {
                for (Subsystem subsystem : subsystems) {
                    subsystem.periodic();
                }
            }
        }
    };

    void setCommander(DogeCommander commander) {
        this.commander = commander;
    }

    protected void addSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }

    public void init() {
        for (Subsystem subsystem : subsystems) {
            subsystem.initHardware();
        }

        new Thread(hardwareUpdater).start();
    }
}
