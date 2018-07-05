package tech.mlaboratory.mcodelab;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by xiaoming on 2018/5/29.
 * Don't contact before modify.
 */
@RunWith(AndroidJUnit4.class)
public class MyInstrumentedTest {

    @Test
    public void test() {
        Assert.assertTrue(compareVersionCode("", "2.3.1") < 0);
        Assert.assertTrue(compareVersionCode("2.3.1", "") > 0);
        Assert.assertTrue(compareVersionCode("2.3.1", "2.3.1") == 0);
        Assert.assertTrue(compareVersionCode("2.3.1", "2.3.2") < 0);
        Assert.assertTrue(compareVersionCode("2.3.2", "2.3.1") > 0);
        Assert.assertTrue(compareVersionCode("2.3.10", "2.3.1") > 0);
        Assert.assertTrue(compareVersionCode("2.3.1", "2.3.10") < 0);

        Assert.assertTrue(compareVersionCode("2.3.1.1", "2.3.1") > 0);
        Assert.assertTrue(compareVersionCode("2.3.1.1", "2.3.10") < 0);

        Assert.assertTrue(compareVersionCode("2", "2.3.1") < 0);
        Assert.assertTrue(compareVersionCode("2.3.1", "2.3.11") < 0);
        Assert.assertTrue(compareVersionCode("2", "2") == 0);
        Assert.assertTrue(compareVersionCode("3.4.4", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.2.0.3", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.3.0", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.2.4.1", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.0", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.1.1", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
        Assert.assertTrue(compareVersionCode("3.5.2", "3.5.3.0") < 0);
    }

    public static int compareVersionCode(String version1, String version2) {
        // 先判空，空为小
        if (TextUtils.isEmpty(version1) && !TextUtils.isEmpty(version2)) {
            return -1;
        } else if (!TextUtils.isEmpty(version1) && TextUtils.isEmpty(version2)) {
            return 1;
        } else if (TextUtils.isEmpty(version1) && TextUtils.isEmpty(version2)) {
            return 0;
        }
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int minLength = Math.min(versionArray1.length, versionArray2.length);

        // 逐位比较版本号，位数较多的为大
        for (int i = 0; i < minLength; i++) {
            if (versionArray1[i].length() > versionArray2[i].length()) {
                return 1;
            } else if (versionArray1[i].length() < versionArray2[i].length()) {
                return -1;
            } else if (!versionArray1[i].equals(versionArray2[i])){
                return versionArray1[i].compareTo(versionArray2[i]);
            }
        }

        return versionArray1.length - versionArray2.length;
    }
}
