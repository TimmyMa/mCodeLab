package com.ziroom.commonlibrary.util;

/**
 * Created by xiaoming on 2017/12/12.
 * Don't contact before modify.
 */

public class SignKeyUtil {

    private static SignKeyUtil a = null;

    static
    {
        System.loadLibrary("ZiroomKey");
    }

    public static String getHouseKey()
    {
        return getInstance().getHouseKeyFromC();
    }

    private static SignKeyUtil getInstance()
    {
        if (a == null) {
            synchronized (SignKeyUtil.class) {
                if (a == null) {
                    a = new SignKeyUtil();
                }
            }
        }
        return a;
    }

    public static String getKey()
    {
        String str = getSignKey();
        try
        {
            str = AnonymousUtil.encrypt(str.substring(0, str.length() - 1), getInstance().getDataFromC());
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    public static String getSignKey()
    {
        return getInstance().getSignKeyFromC();
    }

    @SuppressWarnings("JniMissingFunction")
    public static native String getKeyFromC();

    @SuppressWarnings("JniMissingFunction")
    public native String getDataFromC();

    @SuppressWarnings("JniMissingFunction")
    public native String getHouseKeyFromC();

    @SuppressWarnings("JniMissingFunction")
    public native String getSignKeyFromC();
}
