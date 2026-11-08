package anet.channel.util;

import anet.channel.request.Request;
import anet.channel.thread.ThreadPoolExecutorFactory;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Integer> f616a = new HashMap();

    static {
        f616a.put("tpatch", 3);
        f616a.put("so", 3);
        f616a.put("json", 3);
        f616a.put(com.baidu.mobads.sdk.internal.a.f, 4);
        f616a.put("htm", 4);
        f616a.put("css", 5);
        f616a.put("js", 5);
        f616a.put("webp", 6);
        f616a.put("png", 6);
        f616a.put("jpg", 6);
        f616a.put("do", 6);
        f616a.put("zip", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
        f616a.put("bin", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
        f616a.put("apk", Integer.valueOf(ThreadPoolExecutorFactory.Priority.LOW));
    }

    public static int a(Request request) {
        Integer num;
        if (request == null) {
            throw new NullPointerException("url is null!");
        }
        if (request.getHeaders().containsKey(HttpConstant.X_PV)) {
            return 1;
        }
        String strTrySolveFileExtFromUrlPath = HttpHelper.trySolveFileExtFromUrlPath(request.getHttpUrl().path());
        if (strTrySolveFileExtFromUrlPath == null || (num = f616a.get(strTrySolveFileExtFromUrlPath)) == null) {
            return 6;
        }
        return num.intValue();
    }
}
