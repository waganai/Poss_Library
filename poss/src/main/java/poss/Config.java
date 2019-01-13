package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class Config implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    Config(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public Config() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public final native String getPrivateKey();

    public final native void setPrivateKey(String var1);

    public final native long getTCPPort();

    public final native void setTCPPort(long var1);

    public final native long getUDPPort();

    public final native void setUDPPort(long var1);

    public final native long getRPCPort();

    public final native void setRPCPort(long var1);

    public final native boolean getDisableUDP();

    public final native void setDisableUDP(boolean var1);

    public final native String getBootstrap();

    public final native void setBootstrap(String var1);

    public final native long getZone();

    public final native void setZone(long var1);

    public final native long getCacheLimit();

    public final native void setCacheLimit(long var1);

    public final native QosServerConfig getQosServerConfig();

    public final native void setQosServerConfig(QosServerConfig var1);

    public final native String getDir();

    public final native void setDir(String var1);

    public final native long getStorageMax();

    public final native void setStorageMax(long var1);

    public final native boolean getDebug();

    public final native void setDebug(boolean var1);

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof Config) {
            Config var2 = (Config)var1;
            String var3 = this.getPrivateKey();
            String var4 = var2.getPrivateKey();
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
                    long var13 = this.getRPCPort();
                    long var15 = var2.getRPCPort();
                    if (var13 != var15) {
                        return false;
                    } else {
                        boolean var17 = this.getDisableUDP();
                        boolean var18 = var2.getDisableUDP();
                        if (var17 != var18) {
                            return false;
                        } else {
                            String var19 = this.getBootstrap();
                            String var20 = var2.getBootstrap();
                            if (var19 == null) {
                                if (var20 != null) {
                                    return false;
                                }
                            } else if (!var19.equals(var20)) {
                                return false;
                            }

                            long var21 = this.getZone();
                            long var23 = var2.getZone();
                            if (var21 != var23) {
                                return false;
                            } else {
                                long var25 = this.getCacheLimit();
                                long var27 = var2.getCacheLimit();
                                if (var25 != var27) {
                                    return false;
                                } else {
                                    QosServerConfig var29 = this.getQosServerConfig();
                                    QosServerConfig var30 = var2.getQosServerConfig();
                                    if (var29 == null) {
                                        if (var30 != null) {
                                            return false;
                                        }
                                    } else if (!var29.equals(var30)) {
                                        return false;
                                    }

                                    String var31 = this.getDir();
                                    String var32 = var2.getDir();
                                    if (var31 == null) {
                                        if (var32 != null) {
                                            return false;
                                        }
                                    } else if (!var31.equals(var32)) {
                                        return false;
                                    }

                                    long var33 = this.getStorageMax();
                                    long var35 = var2.getStorageMax();
                                    if (var33 != var35) {
                                        return false;
                                    } else {
                                        boolean var37 = this.getDebug();
                                        boolean var38 = var2.getDebug();
                                        return var37 == var38;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getPrivateKey(), this.getTCPPort(), this.getUDPPort(), this.getRPCPort(), this.getDisableUDP(), this.getBootstrap(), this.getZone(), this.getCacheLimit(), this.getQosServerConfig(), this.getDir(), this.getStorageMax(), this.getDebug()});
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("Config").append("{");
        var1.append("PrivateKey:").append(this.getPrivateKey()).append(",");
        var1.append("TCPPort:").append(this.getTCPPort()).append(",");
        var1.append("UDPPort:").append(this.getUDPPort()).append(",");
        var1.append("RPCPort:").append(this.getRPCPort()).append(",");
        var1.append("DisableUDP:").append(this.getDisableUDP()).append(",");
        var1.append("Bootstrap:").append(this.getBootstrap()).append(",");
        var1.append("Zone:").append(this.getZone()).append(",");
        var1.append("CacheLimit:").append(this.getCacheLimit()).append(",");
        var1.append("QosServerConfig:").append(this.getQosServerConfig()).append(",");
        var1.append("Dir:").append(this.getDir()).append(",");
        var1.append("StorageMax:").append(this.getStorageMax()).append(",");
        var1.append("Debug:").append(this.getDebug()).append(",");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}

