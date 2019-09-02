package com.disnodeteam.dogecommander;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The DogeCommander does a few things; it runs commands, and it sets when a robot should be updating or not.
 */
public class DogeCommander {
    private static String TAG = "DogeCommander";

    private boolean halt;
    private DogeOpMode opMode;
    private List<Command> currentStack = new ArrayList<>();
    private Set<Subsystem> subsystems = new HashSet<>();

    private Runnable hardwareUpdater = new Runnable() {
        @Override
        public void run() {
            while (isRunning()) {
                for (Subsystem subsystem : subsystems) {
                    subsystem.periodic();
                }
            }
        }
    };

    /**
     * This creates a new DogeCommander
     * @param opMode the DogeOpMode that the commander is associated with; for
     *               LinearOpMode you can just make it implement DogeOpMode
     */
    public DogeCommander(DogeOpMode opMode) {
        this.opMode = opMode;
    }

    /**
     * This method initializes anything the DogeCommander needs; namely initializing the subsystems.
     * This should be called at the init phase of the OpMode.
     *
     * It also starts the hardware update thread, which dies on either OpMode stop or DogeCommander
     * stop.
     *
     * @see Subsystem#initHardware()
     */
    public void init() {
        for (Subsystem subsystem : subsystems) {
            subsystem.initHardware();
        }

        new Thread(hardwareUpdater).start();
    }

    /**
     * This method adds a subsystem so that it's init method is called on DogeCommander.init(), and
     * so that it's periodic gets constantly called on a separate thread
     *
     * This method should be called before DogeCommander#init()
     * @param subsystem the subsystem to register
     */
    public void registerSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }

    /**
     * This starts the DogeCommander; it mainly starts the hardware update thread.
     * This should be called whenever the OpMode starts.
     */
    public void start() {
        this.halt = false;
    }

    /**
     * This stops the DogeCommander; it stops the hardware update thread and stops command execution.
     * This should be called whenever the OpMode ends.
     */
    public void stop() {
        this.halt = true;
    }

    /**
     * This returns whether or not the DogeCommander is active
     *
     * @return if the DogeCommander is currently active
     */
    public boolean isRunning() {
        return !halt;
    }

    /**
     * This runs a singular command to completion
     *
     * @param command the command to run
     */
    public void runCommand(Command command) {
        runCommandsParallel(command);
    }

    /**
     * This runs a series of commands to completion in parallel
     *
     * @param commands the commands to run
     */
    public void runCommandsParallel(Command... commands) {
        this.halt = false;
        for (Command command : commands) {
            currentStack.add(command);
            command.start();
        }

        while (isRunning() && isTaskRunningInStack() &&
                opMode.opModeIsActive()) {
            ArrayList<Command> commandsToRemove = new ArrayList<>();

            for (Command command : currentStack) {
                command.periodic();

                if (command.isCompleted()) {
                    command.stop();
                    commandsToRemove.add(command);
                }
            }

            currentStack.removeAll(commandsToRemove);
        }

        for (Command command : commands) {
            currentStack.remove(command);
        }

        if (!opMode.opModeIsActive()) {
            stop();
        }
    }

    private boolean isTaskRunningInStack() {
        boolean isTaskRunning = false;

        for (Command command : currentStack) {
            isTaskRunning = isTaskRunning || !command.isCompleted();

        }
        return isTaskRunning;
    }
}
