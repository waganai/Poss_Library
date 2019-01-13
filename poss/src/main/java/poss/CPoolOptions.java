package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class CPoolOptions implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    CPoolOptions(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public CPoolOptions() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public final native String getCPoolSignatureHex();

    public final native void setCPoolSignatureHex(String var1);

    public final native long getNonce();

    public final native void setNonce(long var1);

    public final native String getCPoolIdHex();

    public final native void setCPoolIdHex(String var1);

    public final native long getExpiredTime();

    public final native void setExpiredTime(long var1);

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof CPoolOptions) {
            CPoolOptions var2 = (CPoolOptions)var1;
            String var3 = this.getCPoolSignatureHex();
            String var4 = var2.getCPoolSignatureHex();
            if (var3 == null) {
                if (var4 != null) {
                    return false;
                }
            } else if (!var3.equals(var4)) {
                return false;
            }

            long var5 = this.getNonce();
            long var7 = var2.getNonce();
            if (var5 != var7) {
                return false;
            } else {
                String var9 = this.getCPoolIdHex();
                String var10 = var2.getCPoolIdHex();
                if (var9 == null) {
                    if (var10 != null) {
                        return false;
                    }
                } else if (!var9.equals(var10)) {
                    return false;
                }

                long var11 = this.getExpiredTime();
                long var13 = var2.getExpiredTime();
                return var11 == var13;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getCPoolSignatureHex(), this.getNonce(), this.getCPoolIdHex(), this.getExpiredTime()});
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("CPoolOptions").append("{");
        var1.append("CPoolSignatureHex:").append(this.getCPoolSignatureHex()).append(",");
        var1.append("Nonce:").append(this.getNonce()).append(",");
        var1.append("CPoolIdHex:").append(this.getCPoolIdHex()).append(",");
        var1.append("ExpiredTime:").append(this.getExpiredTime()).append(",");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}
