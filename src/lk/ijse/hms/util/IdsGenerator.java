/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/16/2022 7:00 PM
 */
package lk.ijse.hms.util;

public class IdsGenerator {

    public static String generateId(String prefix,String lastId){
        if(lastId==null){
            return prefix+"000";
        }
        int nextValue = Integer.parseInt(lastId.replace(prefix,"").substring(1,4))+1;
        return String.format(prefix+"%03d",nextValue);
    }
}
