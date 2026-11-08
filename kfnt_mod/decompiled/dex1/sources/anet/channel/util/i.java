package anet.channel.util;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicInteger f617a = new AtomicInteger();

    public static String a(String str) {
        if (f617a.get() == Integer.MAX_VALUE) {
            f617a.set(0);
        }
        if (!TextUtils.isEmpty(str)) {
            return StringUtils.concatString(str, ".AWCN", String.valueOf(f617a.incrementAndGet()));
        }
        return StringUtils.concatString("AWCN", String.valueOf(f617a.incrementAndGet()));
    }
}
