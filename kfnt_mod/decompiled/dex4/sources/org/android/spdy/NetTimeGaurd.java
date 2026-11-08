package org.android.spdy;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class NetTimeGaurd {
    public static final int CREATE = 0;
    public static final int ERROR = 2;
    public static final int PING = 1;
    public static final int STREAM = 3;
    private static final long calltime = 10;
    private static final long total = 50;
    private static long[] totaltime = new long[4];

    static void start(int i) {
        if (SpdyAgent.enableTimeGaurd) {
            totaltime[i] = 0;
        }
    }

    static long begin() {
        if (SpdyAgent.enableTimeGaurd) {
            return System.currentTimeMillis();
        }
        return 0L;
    }

    static void end(String str, int i, long j) {
        if (SpdyAgent.enableTimeGaurd) {
            long jCurrentTimeMillis = System.currentTimeMillis() - j;
            totaltime[i] = totaltime[i] + jCurrentTimeMillis;
            Log.i("NetTimeGaurd", "NetTimeGaurd[end]" + str + " time=" + jCurrentTimeMillis + " total=" + totaltime[i]);
            if (jCurrentTimeMillis <= calltime) {
                return;
            }
            throw new SpdyErrorException("CallBack:" + str + " timeconsuming:" + jCurrentTimeMillis + "  mustlessthan:" + calltime, -1);
        }
    }

    static void finish(int i) {
        if (SpdyAgent.enableTimeGaurd) {
            Log.i("NetTimeGaurd", "NetTimeGaurd[finish]:time=" + totaltime[i]);
            if (totaltime[i] <= total) {
                return;
            }
            throw new SpdyErrorException("CallBack totaltimeconsuming:" + totaltime[i] + "  mustlessthan:" + total, -1);
        }
    }
}
