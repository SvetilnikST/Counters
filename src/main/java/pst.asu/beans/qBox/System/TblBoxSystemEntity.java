package pst.asu.beans.qBox.System;

import pst.asu.beans.qBox.Common.TblBoxCommonEntity;

import javax.persistence.*;

@Entity
@Table(name = "qBox_data_system")
public class TblBoxSystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "time_run_sys", nullable = false)
    private int timeRunSys;

    @Column(name = "SIGMA_Q", nullable = false)
    private double SIGMA_Q;

    @Column(name = "Q1", nullable = false)
    private double Q1;

    @Column(name = "Q2", nullable = false)
    private double Q2;

    @Column(name = "Q3", nullable = false)
    private double Q3;

    @Column(name = "V1", nullable = false)
    private double V1;

    @Column(name = "V2", nullable = false)
    private double V2;

    @Column(name = "M1", nullable = false)
    private double M1;

    @Column(name = "M2", nullable = false)
    private double M2;

    @Column(name = "GM1", nullable = false)
    private float GM1;

    @Column(name = "GM2", nullable = false)
    private float GM2;

    @Column(name = "GV1", nullable = false)
    private float GV1;

    @Column(name = "GV2", nullable = false)
    private float GV2;

    @Column(name = "T1", nullable = false)
    private float T1;

    @Column(name = "T2", nullable = false)
    private float T2;

    @Column(name = "T3", nullable = false)
    private float T3;

    @Column(name = "P1", nullable = false)
    private float P1;

    @Column(name = "P2", nullable = false)
    private float P2;

    @Column(name = "P3", nullable = false)
    private float P3;

    @ManyToOne
    @JoinColumn(name = "qBox_data_common_id")
    private TblBoxCommonEntity tblBoxCommonEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTimeRunSys() {
        return timeRunSys;
    }

    public void setTimeRunSys(int timeRunSys) {
        this.timeRunSys = timeRunSys;
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


    public TblBoxCommonEntity getTblBoxCommonEntity() {

        return tblBoxCommonEntity;
    }

    public void setTblBoxCommonEntity(TblBoxCommonEntity tblBoxCommonEntity) {
        this.tblBoxCommonEntity = tblBoxCommonEntity;
    }

    public double getSIGMA_Q() {
        return SIGMA_Q;
    }

    public void setSIGMA_Q(double SIGMA_Q) {
        this.SIGMA_Q = SIGMA_Q;
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
