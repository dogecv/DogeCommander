package com.disnodeteam.dogecommander;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Victo on 8/12/2018.
 */

public interface Subsystem {
    void initHardware();

    void periodic();
}
