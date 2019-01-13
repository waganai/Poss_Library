package poss;

import go.Seq;
import go.Seq.Proxy;
import java.util.Arrays;

public final class User implements Proxy {
    private final int refnum;

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    User(int var1) {
        this.refnum = var1;
        Seq.trackGoRef(var1, this);
    }

    public User() {
        this.refnum = __New();
        Seq.trackGoRef(this.refnum, this);
    }

    private static native int __New();

    public native String copyObject(String var1, String var2, String var3, String var4, String var5, long var6, String var8, boolean var9) throws Exception;

    public native void createBucket(String var1) throws Exception;

    public native void deleteBucket(String var1) throws Exception;

    public native String deleteObject(String var1, String var2) throws Exception;

    public native void deleteTask(String var1) throws Exception;

    public native String dumpIndexdata() throws Exception;

    public native String dumpTasks() throws Exception;

    public native String exportRootHash() throws Exception;

    public native String exportWalletAccount() throws Exception;

    public native String exportWalletKey() throws Exception;

    public native Progress getJobProgress(String var1) throws Exception;

    public native String getObject(String var1, String var2, String var3, String var4, String var5) throws Exception;

    public native String getTask(String var1) throws Exception;

    public native String headObject(String var1, String var2) throws Exception;

    public native void importRootHash(String var1) throws Exception;

    public native void init(String var1) throws Exception;

    public native String listBuckets() throws Exception;

    public native String listObjects(String var1) throws Exception;

    public native String listTasks() throws Exception;

    public native void moveObject(String var1, String var2, String var3) throws Exception;

    public native String objectStatus(String var1, String var2) throws Exception;

    public native void pauseTask(String var1) throws Exception;

    public native String putObject(String var1, String var2, String var3, String var4, String var5, long var6, String var8, boolean var9) throws Exception;

    public native String putObjectSync(String var1, String var2, String var3, String var4, String var5, long var6, String var8, boolean var9) throws Exception;

    public native String renewObject(String var1, String var2, String var3, long var4, String var6) throws Exception;

    public native void resumeTask(String var1) throws Exception;

    public native String shareObject(String var1, String var2) throws Exception;

    public native void startDaemon() throws Exception;

    public native void stopDaemon();

    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof User) {
            User var2 = (User)var1;
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[0]);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("User").append("{");
        return var1.append("}").toString();
    }

    static {
        Poss.touch();
    }
}
