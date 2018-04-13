package com.ifeng.daemon.facade;

/**
 * Created by xiaoming on 2018/4/8.
 * Don't contact before modify.
 */

public class NativeSecureparam {
    static {
        try {
            System.loadLibrary("ifeng_secure");
        } catch (Throwable ignore) {}
    }

    public static native String readMD5Key();

    public static native String readPacketPublicKey();

    public static native String readPacketSalt();

    public static native String readSig2Key();

    public static native String readUserCreditPublicKey();

    public static native String readUserCreditSalt();
}
