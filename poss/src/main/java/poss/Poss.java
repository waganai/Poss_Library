
package poss;

import go.Seq;
import go.Seq.Proxy;

public abstract class Poss {
    private Poss() {
    }

    public static void touch() {
    }

    private static native void _init();

    public static native Config createDefaultConfig();

    public static native User createUser(Config var0) throws Exception;

    public static native String getVersionString();

    static {
        Seq.touch();
        _init();
    }

    private static final class proxyUserOpCallback implements Proxy, UserOpCallback {
        private final int refnum;

        public final int incRefnum() {
            Seq.incGoRef(this.refnum, this);
            return this.refnum;
        }

        proxyUserOpCallback(int var1) {
            this.refnum = var1;
            Seq.trackGoRef(var1, this);
        }

        public native boolean onProgress(long var1, long var3);
    }
}