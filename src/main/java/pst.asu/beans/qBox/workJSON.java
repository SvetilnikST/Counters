package pst.asu.beans.qBox;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class workJSON {
    private final String FILENAME = "D:/1_SVETILNIK_NEED/Java/Counters/sku.json";

    public void fromJSON(){
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            TblBoxCommonEntity qbox_data_common = gson.fromJson(reader, TblBoxCommonEntity.class);
            //записываем в базу полученные данные


        } catch (FileNotFoundException ex) {
            int a=0;
        }
    }
}
