package com.disnodeteam.dogecommander.pid;

/**
 *
 */
public class PIDController {
    private double kP;
    private double kI;
    private double kD;
    private double lastTime;
    private int integral = 0;
    private double previousError = 0;
    private double errorSum = 0;

    public PIDController(PIDSettings settings) {
        this.kP = settings.getP();
        this.kI = settings.getI();
        this.kD = settings.getD();
    }


    public double run(double targetValue, double position) {
        double dt = (System.currentTimeMillis() - lastTime);
        lastTime = System.currentTimeMillis();

        double error = (targetValue - position);

        errorSum += error;

        double result = (kP * error) + (kI * dt * errorSum) + (kD / dt * (error - previousError));

        previousError = error;

        return result;
    }
}