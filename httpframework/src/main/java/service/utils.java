package service;

/**
 * Created by CTAS on 2017/12/29.
 */
public class utils {

    public static boolean isExist(String className, ClassLoader loader) {
        try { Class.forName(className);
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
