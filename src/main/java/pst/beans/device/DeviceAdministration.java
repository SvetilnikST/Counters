package pst.beans.device;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "deviceAdministration")
public class DeviceAdministration {
    private TblDeviceEntity tblDeviceEntity;

    @EJB
    private DeviceDAO deviceDAO;

    @Default
    private int id;
    private String name;
    private String serial;
    private int lastRequestDate;
    private int requestsCount;



}
