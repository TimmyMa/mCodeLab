package com.ziroom.commonlibrary.util;

import java.security.Provider;

/**
 * Created by xiaoming on 2017/12/13.
 * Don't contact before modify.
 */

class CryptoProvider extends Provider {

    CryptoProvider() {
        super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
        put("SecureRandom.SHA1PRNG",
                "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
        put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
    }
}
