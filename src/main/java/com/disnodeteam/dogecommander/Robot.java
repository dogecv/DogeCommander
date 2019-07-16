package com.disnodeteam.dogecommander;

import java.util.HashSet;
import java.util.Set;

/**
 * The Robot should be inherited by the user; it holds all Subsystems and updates them on their own thread
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

    /**
     * This should be called on all subsystems, and register them to be initialized and updated
     * @param subsystem the subsystem to add
     */
    public void addSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }

    /**
     * Sets the DogeCommander for this robot; called by the DogeCommander class
     * @param commander the DogeCommander set
     */
    void setCommander(DogeCommander commander) {
        this.commander = commander;
    }

    /**
     * Initializes the hardware of the Subsystems and starts the update thread; called by the DogeCommander class
     */
    public void init() {
        for (Subsystem subsystem : subsystems) {
            subsystem.initHardware();
        }

        new Thread(hardwareUpdater).start();
    }
}
