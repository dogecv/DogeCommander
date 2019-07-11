package com.disnodeteam.dogecommander;

/**
 * The Subsystem interface represents a physical component of a robot. These
 * can be a drivetrain, a claw, etc.
 */

public interface Subsystem {
    /**
     * This method should initialize the hardware of the component. This is
     * called by Robot.
     */
    void initHardware();

    /**
     * This method is called by Robot while the DogeCommander is active.
     */
    void periodic();
}
