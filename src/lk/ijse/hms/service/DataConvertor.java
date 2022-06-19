/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/19/2022 1:15 AM
 */
package lk.ijse.hms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DataConvertor {

    private static DataConvertor dataConvertor;

    private DataConvertor(){}

    public <T> List<T> convert(List<?> list, Function function){
        List<T> tms = new ArrayList();
        for(int i = 0; i<list.size(); i++){
            tms.add((T) function.apply(list.get(i)));
        }
        return tms;
    }

    public static DataConvertor getInstance(){
        return dataConvertor==null? dataConvertor = new DataConvertor():dataConvertor;
    }
}
