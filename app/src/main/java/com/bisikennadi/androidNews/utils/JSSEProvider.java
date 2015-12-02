package com.bisikennadi.androidNews.utils;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

/**
 * Created by BNnadi on 12/2/2015.
 * com.bisikennadi.androidNews.utils
 */
public class JSSEProvider extends Provider {
    public JSSEProvider() {
        super("AAA JSSE",1.0,"AAA JSSE Provider");
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                put("SSLContext.TLS",
                        "org.apache.harmony.xnet.provider.jsse.SSLContextImpl");
                put("Alg.Alias.SSLContext.TLSv1", "TLS");
                put("KeyManagerFactory.X509",
                        "org.apache.harmony.xnet.provider.jsse.KeyManagerFactoryImpl");
                put("TrustManagerFactory.X509",
                        "org.apache.harmony.xnet.provider.jsse.TrustManagerFactoryImpl");
                return null;
            }
        });
    }
}
