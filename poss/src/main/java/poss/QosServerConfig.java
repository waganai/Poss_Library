package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class QosServerConfig implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    QosServerConfig(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public QosServerConfig() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public final native boolean getEnable();

    public final native void setEnable(boolean var1);

    public final native String getNetwork();

    public final native void setNetwork(String var1);

    public final native String getAddr();

    public final native void setAddr(String var1);

    public final native String getTag();

    public final native void setTag(String var1);

    public final native String getDir();

    public final native void setDir(String var1);

    public final native long getMaxLinePerFile();

    public final native void setMaxLinePerFile(long var1);

    public final native long getHTTPTimeout();

    public final native void setHTTPTimeout(long var1);

    public final native boolean getDirectToFile();

    public final native void setDirectToFile(boolean var1);

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof QosServerConfig) {
            QosServerConfig var2 = (QosServerConfig)var1;
            boolean var3 = this.getEnable();
            boolean var4 = var2.getEnable();
            if (var3 != var4) {
                return false;
            } else {
                String var5 = this.getNetwork();
                String var6 = var2.getNetwork();
                if (var5 == null) {
                    if (var6 != null) {
                        return false;
                    }
                } else if (!var5.equals(var6)) {
                    return false;
                }

                String var7 = this.getAddr();
                String var8 = var2.getAddr();
                if (var7 == null) {
                    if (var8 != null) {
                        return false;
                    }
                } else if (!var7.equals(var8)) {
                    return false;
                }

                String var9 = this.getTag();
                String var10 = var2.getTag();
                if (var9 == null) {
                    if (var10 != null) {
                        return false;
                    }
                } else if (!var9.equals(var10)) {
                    return false;
                }

                String var11 = this.getDir();
                String var12 = var2.getDir();
                if (var11 == null) {
                    if (var12 != null) {
                        return false;
                    }
                } else if (!var11.equals(var12)) {
                    return false;
                }

                long var13 = this.getMaxLinePerFile();
                long var15 = var2.getMaxLinePerFile();
                if (var13 != var15) {
                    return false;
                } else {
                    long var17 = this.getHTTPTimeout();
                    long var19 = var2.getHTTPTimeout();
                    if (var17 != var19) {
                        return false;
                    } else {
                        boolean var21 = this.getDirectToFile();
                        boolean var22 = var2.getDirectToFile();
                        return var21 == var22;
                    }
                }
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getEnable(), this.getNetwork(), this.getAddr(), this.getTag(), this.getDir(), this.getMaxLinePerFile(), this.getHTTPTimeout(), this.getDirectToFile()});
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("QosServerConfig").append("{");
        var1.append("Enable:").append(this.getEnable()).append(",");
        var1.append("Network:").append(this.getNetwork()).append(",");
        var1.append("Addr:").append(this.getAddr()).append(",");
        var1.append("Tag:").append(this.getTag()).append(",");
        var1.append("Dir:").append(this.getDir()).append(",");
        var1.append("MaxLinePerFile:").append(this.getMaxLinePerFile()).append(",");
        var1.append("HTTPTimeout:").append(this.getHTTPTimeout()).append(",");
        var1.append("DirectToFile:").append(this.getDirectToFile()).append(",");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}
