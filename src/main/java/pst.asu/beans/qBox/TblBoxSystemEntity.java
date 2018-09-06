package pst.asu.beans.qBox;

import javax.persistence.*;

@Entity
@Table(name = "qBox_data_system")
public class TblBoxSystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

//    @Column(name = "qBox_data_common_id", nullable = false)
//    private int comId;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "timeRunSys", nullable = false)
    private int timeRunSys;

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
    private double GM1;

    @Column(name = "GM2", nullable = false)
    private double GM2;

    @Column(name = "GV1", nullable = false)
    private double GV1;

    @Column(name = "GV2", nullable = false)
    private double GV2;

    @Column(name = "T1", nullable = false)
    private double T1;

    @Column(name = "T2", nullable = false)
    private double T2;

    @Column(name = "T3", nullable = false)
    private double T3;

    @Column(name = "P1", nullable = false)
    private double P1;

    @Column(name = "P2", nullable = false)
    private double P2;

    @Column(name = "P3", nullable = false)
    private double P3;

    @ManyToOne
    @JoinColumn(name = "commonId")
    private TblBoxCommonEntity tblBoxCommonEntity;

}
