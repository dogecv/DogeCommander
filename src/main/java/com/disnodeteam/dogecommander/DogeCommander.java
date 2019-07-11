package com.disnodeteam.dogecommander;

import java.util.ArrayList;
import java.util.List;

public class DogeCommander {
    private static String TAG = "DogeCommander";

    private Robot bot;
    private boolean halt;
    private String status;
    private List<Command> currentStack = new ArrayList<>();


    public void setBot(Robot bot) {
        this.bot = bot;
        bot.setCommander(this);
    }

    public void init() {
        bot.init();
    }

    public void runCommand(Command command){
        runCommandsParallel(new Command[]{command});
    }

    public void runCommandsParallel(Command[] commands){
        this.halt = false;
        for(Command command : commands){
            currentStack.add(command);
            command.start();
        }

        while(!halt && isTaskRunningInStack()){
            for(Command command : commands){
                command.periodic();
            }
        }

        for(Command command : commands){
            command.stop();
            currentStack.remove(command);
        }
    }

    public boolean isRunning() {
        return !halt;
    }


    public void start() {
        this.halt = false;
    }

    public void stop() {
        this.halt = true;
    }

    private boolean isTaskRunningInStack(){
        boolean isTaskRunning = false;

        for(Command command : currentStack){
            isTaskRunning = isTaskRunning || !command.isCompleted();

        }
        return isTaskRunning;
    }
}
