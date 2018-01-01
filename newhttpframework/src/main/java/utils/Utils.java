package utils;

/**
 * Created by CTAS on 2018/1/1.
 */
public class Utils {




    public static boolean isExist(String classname,ClassLoader loader){

        try {
             Class.forName(classname);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  false;

    }
}
