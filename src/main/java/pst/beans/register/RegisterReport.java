package pst.beans.register;

public class RegisterReport {
    private String date;
    private Boolean hotWater;
    private Boolean heating;
    private Boolean ventilation;
    private Double Tnv;
    private double Q1;
    private Double Q1delta;
    private double V1;
    private float GM1;
    private float T1;
    private float P1;
    private double Q2;
    private Double Q2delta;
    private double V2;
    private float GM2;
    private float T2;
    private float P2;
    private Integer timeOn;
//    private String timeError;
    private Integer timeOndelta;
    private float Txv;
    private Double Qsumm;
    private Double Qdelta;
    private Double Vdelta;
    private int idShedule;
    private boolean inStore1;
    private boolean toShow =false;

    public boolean isToShow() {
        return toShow;
    }

    public void setToShow(boolean toShow) {
        this.toShow = toShow;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getHotWater() {
        return hotWater;
    }

    public void setHotWater(Boolean hotWater) {
        this.hotWater = hotWater;
    }

    public Boolean getHeating() {
        return heating;
    }

    public void setHeating(Boolean heating) {
        this.heating = heating;
    }

    public Boolean getVentilation() {
        return ventilation;
    }

    public void setVentilation(Boolean ventilation) {
        this.ventilation = ventilation;
    }

    public Double getTnv() {
        return Tnv;
    }

    public void setTnv(Double tnv) {
        Tnv = tnv;
    }

    public double getQ1() {
        return Q1;
    }

    public void setQ1(double q1) {
        Q1 = q1;
    }

    public Double getQ1delta() {
        return Q1delta;
    }

    public void setQ1delta(Double q1delta) {
        Q1delta = q1delta;
    }

    public double getV1() {
        return V1;
    }

    public void setV1(double v1) {
        V1 = v1;
    }

    public float getGM1() {
        return GM1;
    }

    public void setGM1(float GM1) {
        this.GM1 = GM1;
    }

    public float getT1() {
        return T1;
    }

    public void setT1(float t1) {
        T1 = t1;
    }

    public float getP1() {
        return P1;
    }

    public void setP1(float p1) {
        P1 = p1;
    }

    public double getQ2() {
        return Q2;
    }

    public void setQ2(double q2) {
        Q2 = q2;
    }

    public Double getQ2delta() {
        return Q2delta;
    }

    public void setQ2delta(Double q2delta) {
        Q2delta = q2delta;
    }

    public double getV2() {
        return V2;
    }

    public void setV2(double v2) {
        V2 = v2;
    }

    public float getGM2() {
        return GM2;
    }

    public void setGM2(float GM2) {
        this.GM2 = GM2;
    }

    public float getT2() {
        return T2;
    }

    public void setT2(float t2) {
        T2 = t2;
    }

    public float getP2() {
        return P2;
    }

    public void setP2(float p2) {
        P2 = p2;
    }

    public Integer getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(Integer timeOn) {
        this.timeOn = timeOn;
    }

//    public String getTimeError() {
//        return timeError;
//    }
//
//    public void setTimeError(String timeError) {
//        this.timeError = timeError;
//    }

    public Integer getTimeOndelta() {
        return timeOndelta;
    }

    public void setTimeOndelta(Integer timeOndelta) {
        this.timeOndelta = timeOndelta;
    }

    public float getTxv() {
        return Txv;
    }

    public void setTxv(float txv) {
        Txv = txv;
    }

    public double getQsumm() {
        return Qsumm;
    }

    public void setQsumm(double qsumm) {
        Qsumm = qsumm;
    }

    public Double getQdelta() {
        return Qdelta;
    }

    public void setQdelta(Double qdelta) {
        Qdelta = qdelta;
    }

    public Double getVdelta() {
        return Vdelta;
    }

    public void setVdelta(Double vdelta) {
        Vdelta = vdelta;
    }

    public int getIdShedule() {
        return idShedule;
    }

    public void setIdShedule(int idShedule) {
        this.idShedule = idShedule;
    }

    public boolean isInStore1() {
        return inStore1;
    }

    public void setInStore1(boolean inStore1) {
        this.inStore1 = inStore1;
    }
}
