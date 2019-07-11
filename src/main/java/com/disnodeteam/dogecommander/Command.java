package com.disnodeteam.dogecommander;

/**
 * Created by Victo on 8/12/2018.
 */

public interface Command {
    void start();
    void periodic();
    void stop();
    boolean isCompleted();
}
