package pst.beans.deviceDataAdministration;

import java.sql.Timestamp;

public class SheduleReport {
    private String dateRequest;
    private String timeRequest;
    private String timeDevice;
    private String timeOn;
    private String timeError;
    private int idShedule;
//    private int number;
    private double SIGMA_Q;
    private double Q1;
    private double Q2;
    private double Q3;
    private double V1;
    private double V2;
    private double M1;
    private double M2;
    private float GM1;
    private float GM2;
    private float GV1;
    private float GV2;
    private float T1;
    private float T2;
    private float T3;
    private float P1;
    private float P2;
    private float P3;


    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(String timeRequest) {
        this.timeRequest = timeRequest;
    }

    public String getTimeDevice() {
        return timeDevice;
    }

    public void setTimeDevice(String timeDevice) {
        this.timeDevice = timeDevice;
    }

    public String getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(String timeOn) {
        this.timeOn = timeOn;
    }

    public String getTimeError() {
        return timeError;
    }

    public void setTimeError(String timeError) {
        this.timeError = timeError;
    }

    public int getIdShedule() {
        return idShedule;
    }

    public void setIdShedule(int idShedule) {
        this.idShedule = idShedule;
    }

    public double getSIGMA_Q() {
        return SIGMA_Q;
    }

    public void setSIGMA_Q(double SIGMA_Q) {
        this.SIGMA_Q = SIGMA_Q;
    }

    public double getQ1() {
        return Q1;
    }

    public void setQ1(double q1) {
        Q1 = q1;
    }

    public double getQ2() {
        return Q2;
    }

    public void setQ2(double q2) {
        Q2 = q2;
    }

    public double getQ3() {
        return Q3;
    }

    public void setQ3(double q3) {
        Q3 = q3;
    }

    public double getV1() {
        return V1;
    }

    public void setV1(double v1) {
        V1 = v1;
    }

    public double getV2() {
        return V2;
    }

    public void setV2(double v2) {
        V2 = v2;
    }

    public double getM1() {
        return M1;
    }

    public void setM1(double m1) {
        M1 = m1;
    }

    public double getM2() {
        return M2;
    }

    public void setM2(double m2) {
        M2 = m2;
    }

    public float getGM1() {
        return GM1;
    }

    public void setGM1(float GM1) {
        this.GM1 = GM1;
    }

    public float getGM2() {
        return GM2;
    }

    public void setGM2(float GM2) {
        this.GM2 = GM2;
    }

    public float getGV1() {
        return GV1;
    }

    public void setGV1(float GV1) {
        this.GV1 = GV1;
    }

    public float getGV2() {
        return GV2;
    }

    public void setGV2(float GV2) {
        this.GV2 = GV2;
    }

    public float getT1() {
        return T1;
    }

    public void setT1(float t1) {
        T1 = t1;
    }

    public float getT2() {
        return T2;
    }

    public void setT2(float t2) {
        T2 = t2;
    }

    public float getT3() {
        return T3;
    }

    public void setT3(float t3) {
        T3 = t3;
    }

    public float getP1() {
        return P1;
    }

    public void setP1(float p1) {
        P1 = p1;
    }

    public float getP2() {
        return P2;
    }

    public void setP2(float p2) {
        P2 = p2;
    }

    public float getP3() {
        return P3;
    }

    public void setP3(float p3) {
        P3 = p3;
    }
}
