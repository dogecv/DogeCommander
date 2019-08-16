package com.disnodeteam.dogecommander;

import java.util.ArrayList;
import java.util.List;

/**
 * The DogeCommander does a few things; it runs commands, and it sets when a robot should be updating or not.
 */
public class DogeCommander {
    private static String TAG = "DogeCommander";

    private DogeBot bot;
    private boolean halt;
    private String status;
    private List<Command> currentStack = new ArrayList<>();

    /**
     * This method sets the robot for the DogeCommander, and it also sets this commander as the robot's commander
     * @param bot the DogeBot to set
     */
    public void setBot(DogeBot bot) {
        this.bot = bot;
        bot.setCommander(this);
    }

    /**
     * This method initializes anything the DogeCommander needs; namely initializing the bot.
     * This should be called at the init phase of the OpMode.
     * @see DogeBot#init()
     */
    public void init() {
        bot.init();
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
     * @return if the DogeCommander is currently active
     */
    public boolean isRunning() {
        return !halt;
    }

    /**
     * This runs a singular command to completion
     * @param command the command to run
     */
    public void runCommand(Command command){
        runCommandsParallel(new Command[]{command});
    }

    /**
     * This runs an array of commands to completion in parallel
     * @param commands the commands to run
     */
    public void runCommandsParallel(Command[] commands){
        this.halt = false;
        for(Command command : commands){
            currentStack.add(command);
            command.start();
        }

        while(isRunning() && isTaskRunningInStack()){
            for(Command command : commands){
                command.periodic();
            }
        }

        for(Command command : commands){
            command.stop();
            currentStack.remove(command);
        }
    }

    private boolean isTaskRunningInStack(){
        boolean isTaskRunning = false;

        for(Command command : currentStack){
            isTaskRunning = isTaskRunning || !command.isCompleted();

        }
        return isTaskRunning;
    }
}
