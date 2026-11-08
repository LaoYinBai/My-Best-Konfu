package anet.channel.bytes;

import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static final int MAX_POOL_SIZE = 524288;
    public static final String TAG = "awcn.ByteArrayPool";

    /* renamed from: a, reason: collision with root package name */
    private final TreeSet<ByteArray> f405a = new TreeSet<>();

    /* renamed from: b, reason: collision with root package name */
    private final ByteArray f406b = ByteArray.create(0);

    /* renamed from: c, reason: collision with root package name */
    private final Random f407c = new Random();
    private long d = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: anet.channel.bytes.a$a, reason: collision with other inner class name */
    public static class C0007a {

        /* renamed from: a, reason: collision with root package name */
        public static a f408a = new a();

        C0007a() {
        }
    }

    public synchronized void a(ByteArray byteArray) {
        ByteArray byteArrayPollLast;
        if (byteArray != null) {
            if (byteArray.bufferLength < 524288) {
                this.d += byteArray.bufferLength;
                this.f405a.add(byteArray);
                while (this.d > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    if (this.f407c.nextBoolean()) {
                        byteArrayPollLast = this.f405a.pollFirst();
                    } else {
                        byteArrayPollLast = this.f405a.pollLast();
                    }
                    this.d -= byteArrayPollLast.bufferLength;
                }
            }
        }
    }

    public synchronized ByteArray a(int i) {
        if (i >= 524288) {
            return ByteArray.create(i);
        }
        this.f406b.bufferLength = i;
        ByteArray byteArrayCeiling = this.f405a.ceiling(this.f406b);
        if (byteArrayCeiling == null) {
            byteArrayCeiling = ByteArray.create(i);
        } else {
            Arrays.fill(byteArrayCeiling.buffer, (byte) 0);
            byteArrayCeiling.dataLength = 0;
            this.f405a.remove(byteArrayCeiling);
            this.d -= byteArrayCeiling.bufferLength;
        }
        return byteArrayCeiling;
    }

    public ByteArray a(byte[] bArr, int i) {
        ByteArray byteArrayA = a(i);
        System.arraycopy(bArr, 0, byteArrayA.buffer, 0, i);
        byteArrayA.dataLength = i;
        return byteArrayA;
    }
}
