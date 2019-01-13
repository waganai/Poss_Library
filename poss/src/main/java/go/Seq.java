package go;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.logging.Logger;

public class Seq {
    private static Logger log = Logger.getLogger("GoSeq");
    private static final int NULL_REFNUM = 41;
    public static final Seq.Ref nullRef = new Seq.Ref(41, (Object)null);
    private static final Seq.GoRefQueue goRefQueue = new Seq.GoRefQueue();
    static final Seq.RefTracker tracker;

    private static native void init();

    public static void touch() {
    }

    private Seq() {
    }

    static native void setContext(Object var0);

    public static void incRefnum(int var0) {
        tracker.incRefnum(var0);
    }

    public static int incRef(Object var0) {
        return tracker.inc(var0);
    }

    public static int incGoObjectRef(Seq.GoObject var0) {
        return var0.incRefnum();
    }

    public static void trackGoRef(int var0, Seq.GoObject var1) {
        if (var0 > 0) {
            throw new RuntimeException("trackGoRef called with Java refnum " + var0);
        } else {
            goRefQueue.track(var0, var1);
        }
    }

    public static Seq.Ref getRef(int var0) {
        return tracker.get(var0);
    }

    public static native void incGoRef(int var0, Seq.GoObject var1);

    static native void destroyRef(int var0);

    static void decRef(int var0) {
        tracker.dec(var0);
    }

    static {
        try {
            Class var0 = Class.forName("go.LoadJNI");
            setContext(var0.getDeclaredField("ctx").get((Object)null));
        } catch (ClassNotFoundException var1) {
            log.warning("LoadJNI class not found");
        } catch (NoSuchFieldException var2) {
            log.severe("LoadJNI class missing field: " + var2);
        } catch (IllegalAccessException var3) {
            log.severe("LoadJNI class bad field: " + var3);
        }

        init();
        Universe.touch();
        tracker = new Seq.RefTracker();
    }

    static final class RefMap {
        private int next = 0;
        private int live = 0;
        private int[] keys = new int[16];
        private Seq.Ref[] objs = new Seq.Ref[16];

        RefMap() {
        }

        Seq.Ref get(int var1) {
            int var2 = Arrays.binarySearch(this.keys, 0, this.next, var1);
            return var2 >= 0 ? this.objs[var2] : null;
        }

        void remove(int var1) {
            int var2 = Arrays.binarySearch(this.keys, 0, this.next, var1);
            if (var2 >= 0 && this.objs[var2] != null) {
                this.objs[var2] = null;
                --this.live;
            }

        }

        void put(int var1, Seq.Ref var2) {
            if (var2 == null) {
                throw new RuntimeException("put a null ref (with key " + var1 + ")");
            } else {
                int var3 = Arrays.binarySearch(this.keys, 0, this.next, var1);
                if (var3 >= 0) {
                    if (this.objs[var3] == null) {
                        this.objs[var3] = var2;
                        ++this.live;
                    }

                    if (this.objs[var3] != var2) {
                        throw new RuntimeException("replacing an existing ref (with key " + var1 + ")");
                    }
                } else {
                    if (this.next >= this.keys.length) {
                        this.grow();
                        var3 = Arrays.binarySearch(this.keys, 0, this.next, var1);
                    }

                    var3 = ~var3;
                    if (var3 < this.next) {
                        System.arraycopy(this.keys, var3, this.keys, var3 + 1, this.next - var3);
                        System.arraycopy(this.objs, var3, this.objs, var3 + 1, this.next - var3);
                    }

                    this.keys[var3] = var1;
                    this.objs[var3] = var2;
                    ++this.live;
                    ++this.next;
                }
            }
        }

        private void grow() {
            int var3 = 2 * roundPow2(this.live);
            int[] var1;
            Seq.Ref[] var2;
            if (var3 > this.keys.length) {
                var1 = new int[this.keys.length * 2];
                var2 = new Seq.Ref[this.objs.length * 2];
            } else {
                var1 = this.keys;
                var2 = this.objs;
            }

            int var4 = 0;

            int var5;
            for(var5 = 0; var5 < this.keys.length; ++var5) {
                if (this.objs[var5] != null) {
                    var1[var4] = this.keys[var5];
                    var2[var4] = this.objs[var5];
                    ++var4;
                }
            }

            for(var5 = var4; var5 < var1.length; ++var5) {
                var1[var5] = 0;
                var2[var5] = null;
            }

            this.keys = var1;
            this.objs = var2;
            this.next = var4;
            if (this.live != this.next) {
                throw new RuntimeException("bad state: live=" + this.live + ", next=" + this.next);
            }
        }

        private static int roundPow2(int var0) {
            int var1;
            for(var1 = 1; var1 < var0; var1 *= 2) {
                ;
            }

            return var1;
        }
    }

    static class GoRef extends PhantomReference<Seq.GoObject> {
        final int refnum;

        GoRef(int var1, Seq.GoObject var2, Seq.GoRefQueue var3) {
            super(var2, var3);
            if (var1 > 0) {
                throw new RuntimeException("GoRef instantiated with a Java refnum " + var1);
            } else {
                this.refnum = var1;
            }
        }
    }

    static class GoRefQueue extends ReferenceQueue<Seq.GoObject> {
        private final Collection<Seq.GoRef> refs = Collections.synchronizedCollection(new HashSet());

        void track(int var1, Seq.GoObject var2) {
            this.refs.add(new Seq.GoRef(var1, var2, this));
        }

        GoRefQueue() {
            Thread var1 = new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Seq.GoRef var1 = (Seq.GoRef)GoRefQueue.this.remove();
                            GoRefQueue.this.refs.remove(var1);
                            Seq.destroyRef(var1.refnum);
                            var1.clear();
                        } catch (InterruptedException var2) {
                            ;
                        }
                    }
                }
            });
            var1.setDaemon(true);
            var1.setName("GoRefQueue Finalizer Thread");
            var1.start();
        }
    }

    static final class RefTracker {
        private static final int REF_OFFSET = 42;
        private int next = 42;
        private final Seq.RefMap javaObjs = new Seq.RefMap();
        private final IdentityHashMap<Object, Integer> javaRefs = new IdentityHashMap();

        RefTracker() {
        }

        synchronized int inc(Object var1) {
            if (var1 == null) {
                return 41;
            } else if (var1 instanceof Seq.Proxy) {
                return ((Seq.Proxy)var1).incRefnum();
            } else {
                Integer var2 = (Integer)this.javaRefs.get(var1);
                if (var2 == null) {
                    if (this.next == 2147483647) {
                        throw new RuntimeException("createRef overflow for " + var1);
                    }

                    var2 = this.next++;
                    this.javaRefs.put(var1, var2);
                }

                int var3 = var2;
                Seq.Ref var4 = this.javaObjs.get(var3);
                if (var4 == null) {
                    var4 = new Seq.Ref(var3, var1);
                    this.javaObjs.put(var3, var4);
                }

                var4.inc();
                return var3;
            }
        }

        synchronized void incRefnum(int var1) {
            Seq.Ref var2 = this.javaObjs.get(var1);
            if (var2 == null) {
                throw new RuntimeException("referenced Java object is not found: refnum=" + var1);
            } else {
                var2.inc();
            }
        }

        synchronized void dec(int var1) {
            if (var1 <= 0) {
                Seq.log.severe("dec request for Go object " + var1);
            } else if (var1 != Seq.nullRef.refnum) {
                Seq.Ref var2 = this.javaObjs.get(var1);
                if (var2 == null) {
                    throw new RuntimeException("referenced Java object is not found: refnum=" + var1);
                } else {
                    var2.refcnt--;
                    if (var2.refcnt <= 0) {
                        this.javaObjs.remove(var1);
                        this.javaRefs.remove(var2.obj);
                    }

                }
            }
        }

        synchronized Seq.Ref get(int var1) {
            if (var1 < 0) {
                throw new RuntimeException("ref called with Go refnum " + var1);
            } else if (var1 == 41) {
                return Seq.nullRef;
            } else {
                Seq.Ref var2 = this.javaObjs.get(var1);
                if (var2 == null) {
                    throw new RuntimeException("unknown java Ref: " + var1);
                } else {
                    return var2;
                }
            }
        }
    }

    public static final class Ref {
        public final int refnum;
        private int refcnt;
        public final Object obj;

        Ref(int var1, Object var2) {
            if (var1 < 0) {
                throw new RuntimeException("Ref instantiated with a Go refnum " + var1);
            } else {
                this.refnum = var1;
                this.refcnt = 0;
                this.obj = var2;
            }
        }

        void inc() {
            if (this.refcnt == 2147483647) {
                throw new RuntimeException("refnum " + this.refnum + " overflow");
            } else {
                ++this.refcnt;
            }
        }
    }

    public interface Proxy extends Seq.GoObject {
    }

    public interface GoObject {
        int incRefnum();
    }
}
