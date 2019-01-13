package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class ServerConfig implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    ServerConfig(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public ServerConfig() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public final native String getIP();

    public final native void setIP(String var1);

    public final native long getTCPPort();

    public final native void setTCPPort(long var1);

    public final native long getUDPPort();

    public final native void setUDPPort(long var1);

    public final native String getPeerID();

    public final native void setPeerID(String var1);

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof ServerConfig) {
            ServerConfig var2 = (ServerConfig)var1;
            String var3 = this.getIP();
            String var4 = var2.getIP();
            if (var3 == null) {
                if (var4 != null) {
                    return false;
                }
            } else if (!var3.equals(var4)) {
                return false;
            }

            long var5 = this.getTCPPort();
            long var7 = var2.getTCPPort();
            if (var5 != var7) {
                return false;
            } else {
                long var9 = this.getUDPPort();
                long var11 = var2.getUDPPort();
                if (var9 != var11) {
                    return false;
                } else {
                    String var13 = this.getPeerID();
                    String var14 = var2.getPeerID();
                    if (var13 == null) {
                        if (var14 != null) {
                            return false;
                        }
                    } else if (!var13.equals(var14)) {
                        return false;
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getIP(), this.getTCPPort(), this.getUDPPort(), this.getPeerID()});
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("ServerConfig").append("{");
        var1.append("IP:").append(this.getIP()).append(",");
        var1.append("TCPPort:").append(this.getTCPPort()).append(",");
        var1.append("UDPPort:").append(this.getUDPPort()).append(",");
        var1.append("PeerID:").append(this.getPeerID()).append(",");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}

