package com.disnodeteam.dogecommander;

/**
 * The Command interface is the center of the command-based design pattern.
 * Each command represents a certain action performed by the robot, more specifically one or more of the subsystems.
 */
public interface Command {
    /**
     * This function is called once whenever the command is run. This typically initializes things for the command.
     * @see DogeCommander#runCommand(Command)
     */
    void start();

    /**
     * This function is called when the command is run until isCompleted
     * returns true. This typically updates motor positions, servo positions,
     * did logic, etc.
     */
    void periodic();

    /**
     * This is called once after the command is run once isCompleted returns
     * true. This typically stops motors moving, resets servos, etc.
     */
    void stop();

    /**
     * This function tells @see DogeCommander if the command is completed.
     *
     * @return if the command is completed
     */
    boolean isCompleted();
}
