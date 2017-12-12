package com.ziroom.commonlibrary.util;

import android.annotation.SuppressLint;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xiaoming on 2017/12/12.
 * Don't contact before modify.
 */

@SuppressWarnings("WeakerAccess")
public class AnonymousUtil {

    private static void a(StringBuffer paramStringBuffer, byte paramByte)
    {
        paramStringBuffer.append("0123456789ABCDEFFFFAD".charAt(paramByte >> 4 & 0xF)).append("0123456789ABCDEFFFFAD".charAt(paramByte & 0xF));
    }

    private static byte[] a(byte[] paramArrayOfByte)
            throws Exception
    {
        KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
        localSecureRandom.setSeed(paramArrayOfByte);
        localKeyGenerator.init(128, localSecureRandom);
        return localKeyGenerator.generateKey().getEncoded();
    }

    private static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
            throws Exception
    {
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
        @SuppressLint("GetInstance") Cipher localCipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
        localCipher.init(1, secretKeySpec);
        return localCipher.doFinal(paramArrayOfByte2);
    }

    private static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
            throws Exception
    {
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
        @SuppressLint("GetInstance") Cipher localCipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
        localCipher.init(2, secretKeySpec);
        return localCipher.doFinal(paramArrayOfByte2);
    }

    public static String decrypt(String paramString1, String paramString2)
            throws Exception
    {
        return toHex(a(a(paramString1.getBytes()), paramString2.getBytes()));
    }

    public static String encrypt(String paramString1, String paramString2)
            throws Exception
    {
        return new String(b(a(paramString1.getBytes()), toByte(paramString2)));
    }

    public static String fromHex(String paramString)
    {
        return new String(toByte(paramString));
    }

    public static byte[] toByte(String paramString)
    {
        int j = paramString.length() / 2;
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        while (i < j)
        {
            arrayOfByte[i] = Integer.valueOf(paramString.substring(i * 2, i * 2 + 2), 16).byteValue();
            i += 1;
        }
        return arrayOfByte;
    }

    public static String toHex(String paramString)
    {
        return toHex(paramString.getBytes());
    }

    public static String toHex(byte[] paramArrayOfByte)
    {
        if (paramArrayOfByte == null) {
            return "";
        }
        StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 3);
        int i = 0;
        while (i < paramArrayOfByte.length)
        {
            a(localStringBuffer, paramArrayOfByte[i]);
            i += 1;
        }
        return localStringBuffer.toString();
    }
}
