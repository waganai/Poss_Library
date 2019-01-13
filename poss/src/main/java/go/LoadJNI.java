package go;

import android.app.Application;
import android.content.Context;
import java.util.logging.Logger;

public class LoadJNI {
    private static Logger log = Logger.getLogger("GoLoadJNI");
    public static final Object ctx;

    public LoadJNI() {
    }

    static {
        System.loadLibrary("gojni");
        Context var0 = null;

        try {
            Application var1 = (Application)Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke((Object)null);
            var0 = var1.getApplicationContext();
        } catch (Exception var5) {
            log.warning("Global context not found: " + var5);
        } finally {
            ctx = var0;
        }

    }
}
