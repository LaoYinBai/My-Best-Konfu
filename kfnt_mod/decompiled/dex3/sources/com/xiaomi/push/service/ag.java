package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.push.cs;
import com.xiaomi.push.ea;
import com.xiaomi.push.hb;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ag {

    /* renamed from: a, reason: collision with other field name */
    private static final Pattern f878a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
    private static long a = 0;

    /* renamed from: a, reason: collision with other field name */
    private static ThreadPoolExecutor f877a = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private static String a(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        String string = sb.toString();
                        com.xiaomi.push.y.a(bufferedReader);
                        return string;
                    }
                    sb.append("\n");
                    sb.append(line);
                }
            } catch (Exception unused) {
                com.xiaomi.push.y.a(bufferedReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                com.xiaomi.push.y.a(bufferedReader);
                throw th;
            }
        } catch (Exception unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    public static void a() {
        ea.a aVarM623a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if ((f877a.getActiveCount() <= 0 || jCurrentTimeMillis - a >= 1800000) && hb.m374a().m379a() && (aVarM623a = bk.a().m623a()) != null && aVarM623a.e() > 0) {
            a = jCurrentTimeMillis;
            a(aVarM623a.m211a(), true);
        }
    }

    public static void a(List<String> list, boolean z) {
        f877a.execute(new ah(list, z));
    }

    public static void b() throws Throwable {
        String strA = a("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(strA)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("dump tcp for uid = " + Process.myUid());
            com.xiaomi.channel.commonutils.logger.b.m22a(strA);
        }
        String strA2 = a("/proc/self/net/tcp6");
        if (TextUtils.isEmpty(strA2)) {
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("dump tcp6 for uid = " + Process.myUid());
        com.xiaomi.channel.commonutils.logger.b.m22a(strA2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            com.xiaomi.channel.commonutils.logger.b.m22a("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(cs.m180a(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            com.xiaomi.channel.commonutils.logger.b.m22a("ConnectivityTest: connect to " + str + " in " + (System.currentTimeMillis() - jCurrentTimeMillis));
            socket.close();
            return true;
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.d("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }
}
