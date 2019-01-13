package go;

import go.Seq.Proxy;

public abstract class Universe {
    private Universe() {
    }

    public static void touch() {
    }

    private static native void _init();

    static {
        Seq.touch();
        _init();
    }

    private static final class proxyerror extends Exception implements Proxy, error {
        private final int refnum;

        public final int incRefnum() {
            Seq.incGoRef(this.refnum, this);
            return this.refnum;
        }

        proxyerror(int var1) {
            this.refnum = var1;
            Seq.trackGoRef(var1, this);
        }

        public String getMessage() {
            return this.error();
        }

        public native String error();
    }
}
