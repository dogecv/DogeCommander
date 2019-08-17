package com.disnodeteam.dogecommander.pid;

public class PIDSettings {
    private double P = 0.1;
    private double I = 0;
    private double D = 0;

    public void set(double P, double I, double D) {
        this.P = P;
        this.I = I;
        this.D = D;
    }

    public double getP() {
        return P;
    }

    public double getI() {
        return I;
    }

    public double getD() {
        return D;
    }
}
