
package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class Progress implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    Progress(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public Progress() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public final native String getAction();

    public final native void setAction(String var1);

    public final native long getTotalSubJobs();

    public final native void setTotalSubJobs(long var1);

    public final native long getFinishedSubJobs();

    public final native void setFinishedSubJobs(long var1);

    public final native long getTotalBytes();

    public final native void setTotalBytes(long var1);

    public final native long getFinishedBytes();

    public final native void setFinishedBytes(long var1);

    public final native String getJobState();

    public final native void setJobState(String var1);

    public final native String getErr();

    public final native void setErr(String var1);

    public final native String getExResult();

    public final native void setExResult(String var1);

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof Progress) {
            Progress var2 = (Progress)var1;
            String var3 = this.getAction();
            String var4 = var2.getAction();
            if (var3 == null) {
                if (var4 != null) {
                    return false;
                }
            } else if (!var3.equals(var4)) {
                return false;
            }

            long var5 = this.getTotalSubJobs();
            long var7 = var2.getTotalSubJobs();
            if (var5 != var7) {
                return false;
            } else {
                long var9 = this.getFinishedSubJobs();
                long var11 = var2.getFinishedSubJobs();
                if (var9 != var11) {
                    return false;
                } else {
                    long var13 = this.getTotalBytes();
                    long var15 = var2.getTotalBytes();
                    if (var13 != var15) {
                        return false;
                    } else {
                        long var17 = this.getFinishedBytes();
                        long var19 = var2.getFinishedBytes();
                        if (var17 != var19) {
                            return false;
                        } else {
                            String var21 = this.getJobState();
                            String var22 = var2.getJobState();
                            if (var21 == null) {
                                if (var22 != null) {
                                    return false;
                                }
                            } else if (!var21.equals(var22)) {
                                return false;
                            }

                            String var23 = this.getErr();
                            String var24 = var2.getErr();
                            if (var23 == null) {
                                if (var24 != null) {
                                    return false;
                                }
                            } else if (!var23.equals(var24)) {
                                return false;
                            }

                            String var25 = this.getExResult();
                            String var26 = var2.getExResult();
                            if (var25 == null) {
                                if (var26 != null) {
                                    return false;
                                }
                            } else if (!var25.equals(var26)) {
                                return false;
                            }

                            return true;
                        }
                    }
                }
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getAction(), this.getTotalSubJobs(), this.getFinishedSubJobs(), this.getTotalBytes(), this.getFinishedBytes(), this.getJobState(), this.getErr(), this.getExResult()});
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("Progress").append("{");
        var1.append("Action:").append(this.getAction()).append(",");
        var1.append("TotalSubJobs:").append(this.getTotalSubJobs()).append(",");
        var1.append("FinishedSubJobs:").append(this.getFinishedSubJobs()).append(",");
        var1.append("TotalBytes:").append(this.getTotalBytes()).append(",");
        var1.append("FinishedBytes:").append(this.getFinishedBytes()).append(",");
        var1.append("JobState:").append(this.getJobState()).append(",");
        var1.append("Err:").append(this.getErr()).append(",");
        var1.append("ExResult:").append(this.getExResult()).append(",");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}

