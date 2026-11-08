package com.taobao.accs.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.w;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements Handler.Callback {
    public static final int ACT_START = 0;
    public static final int ACT_STOP = -1;
    public static final String AGOO_PID = "agoo.pid";
    public static final String EX_FILE_NAME = "DaemonServer";
    public static final String PROCESS_NAME = "runServer";
    public static String a = "startservice -n {packname}/com.taobao.accs.ChannelService";
    private static final String c = "com.taobao.accs.b.a";
    private static int g = 7200;
    private static int h = 2500;
    private static final ReentrantLock i = new ReentrantLock();
    private static a j = null;
    public boolean b;
    private Context d;
    private String e;
    private int f;
    private String k;
    private String m;
    private String n;
    private int o;
    private String p;
    private String q;
    private int r;
    private String l = "tb_accs_eudemon_1.1.3";
    private boolean s = true;
    private Handler t = null;
    private HandlerThread u = null;

    public a(Context context, int i2, boolean z) {
        this.d = null;
        this.f = 1800;
        this.b = false;
        this.k = "100001";
        this.m = "";
        this.n = "21646297";
        this.o = 0;
        this.p = "100.69.165.28";
        this.q = "http://100.69.165.28/agoo/report";
        this.r = 80;
        b();
        a = "startservice -n {packname}/com.taobao.accs.ChannelService";
        this.d = context;
        this.f = i2;
        this.b = z;
        this.e = a(new Build(), "CPU_ABI");
        this.m = "/data/data/" + context.getPackageName();
        this.o = Constants.SDK_VERSION_CODE;
        String[] strArrS = UtilityImpl.s(this.d);
        this.n = (strArrS == null || strArrS.length == 0) ? "" : strArrS[0];
        if (w.b(context) == 0) {
            this.p = "agoodm.m.taobao.com";
            this.r = 80;
            this.q = "http://agoodm.m.taobao.com/agoo/report";
            this.k = "1009527";
            return;
        }
        if (w.b(context) == 1) {
            this.p = "110.75.98.154";
            this.r = 80;
            this.q = "http://agoodm.wapa.taobao.com/agoo/report";
            this.k = "1009527";
            return;
        }
        this.p = "100.69.168.33";
        this.r = 80;
        this.q = "http://100.69.168.33/agoo/report";
        this.f = 60;
        this.k = "9527";
    }

    private void b() {
        Log.d(c, "start handler init");
        this.u = new HandlerThread("soManager-threads");
        this.u.setPriority(5);
        this.u.start();
        this.t = new Handler(this.u.getLooper(), this);
    }

    private String c() {
        if (this.e.startsWith("arm")) {
            return "armeabi/";
        }
        return this.e + "/";
    }

    private static String a(Build build, String str) {
        try {
            return Build.class.getField(str).get(build).toString();
        } catch (Throwable unused) {
            return "Unknown";
        }
    }

    public static a a(Context context, int i2, boolean z) {
        try {
            try {
                i.lock();
                if (j == null) {
                    j = new a(context, i2, z);
                }
            } catch (Exception e) {
                ALog.e(c, "getInstance", e, new Object[0]);
            }
            return j;
        } finally {
            i.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0091 -> B:40:0x00ba). Please report as a decompilation issue!!! */
    private String d() throws Throwable {
        File file;
        ?? file2 = new File(this.d.getFilesDir(), EX_FILE_NAME);
        if (file2.exists()) {
            file2.delete();
        }
        int i2 = 0;
        i2 = 0;
        i2 = 0;
        ALog.w(c, "open assets from = " + c() + EX_FILE_NAME, new Object[0]);
        FileOutputStream fileOutputStream = new FileOutputStream((File) file2);
        InputStream inputStream = null;
        try {
        } catch (IOException e) {
            Object[] objArr = new Object[i2];
            ALog.e(c, "error in close io", e, objArr);
            file = file2;
            i2 = objArr;
        }
        try {
            try {
                if (this.b) {
                    InputStream inputStreamOpen = this.d.getAssets().open(c() + EX_FILE_NAME);
                    try {
                        byte[] bArr = new byte[100];
                        while (true) {
                            int i3 = inputStreamOpen.read(bArr);
                            if (i3 <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i3);
                        }
                        inputStream = inputStreamOpen;
                    } catch (Exception e2) {
                        e = e2;
                        inputStream = inputStreamOpen;
                        ALog.e(c, "error in copy daemon files", e, new Object[0]);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                ALog.e(c, "error in close input file", e3, new Object[0]);
                            }
                        }
                        fileOutputStream.close();
                        file = file2;
                        file2 = file.getCanonicalPath();
                        return file2;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpen;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                ALog.e(c, "error in close input file", e4, new Object[0]);
                            }
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e5) {
                            ALog.e(c, "error in close io", e5, new Object[0]);
                            throw th;
                        }
                    }
                } else {
                    a(fileOutputStream, (File) file2);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        ALog.e(c, "error in close input file", e6, new Object[0]);
                    }
                }
                fileOutputStream.close();
                file = file2;
            } catch (Exception e7) {
                e = e7;
            }
            file2 = file.getCanonicalPath();
            return file2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.io.IOException] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00ad -> B:43:0x00b0). Please report as a decompilation issue!!! */
    private void a(FileOutputStream fileOutputStream, File file) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        String strA = b.a(this.e);
        ALog.d(c, ">>>soDataSize:datasize:" + strA.length(), new Object[0]);
        byte[] bArrDecode = Base64.decode(strA.getBytes(), 0);
        ALog.d(c, ">>>soDataSize:" + bArrDecode.length, new Object[0]);
        if (bArrDecode.length > 0 && fileOutputStream != null) {
            ?? r1 = 0;
            ByteArrayInputStream byteArrayInputStream2 = null;
            r1 = 0;
            StatFs statFs = new StatFs(file.getCanonicalPath());
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e.printStackTrace();
                r1 = r1;
                fileOutputStream = e;
            }
            if (statFs.getBlockSize() * statFs.getAvailableBlocks() < bArrDecode.length) {
                Log.e(c, "Disk is not enough for writing this file");
                return;
            }
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArrDecode);
            } catch (IOException e2) {
                e = e2;
            }
            try {
                byte[] bArr = new byte[100];
                while (true) {
                    int i2 = byteArrayInputStream.read(bArr, 0, 100);
                    if (i2 < 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i2);
                    }
                }
                FileDescriptor fd = fileOutputStream.getFD();
                fd.sync();
                byteArrayInputStream.close();
                r1 = bArr;
                fileOutputStream = fd;
            } catch (IOException e3) {
                e = e3;
                byteArrayInputStream2 = byteArrayInputStream;
                ALog.e(c, "error in write files", e, new Object[0]);
                FileDescriptor fd2 = fileOutputStream.getFD();
                fd2.sync();
                r1 = byteArrayInputStream2;
                fileOutputStream = fd2;
                if (byteArrayInputStream2 != null) {
                    byteArrayInputStream2.close();
                    r1 = byteArrayInputStream2;
                    fileOutputStream = fd2;
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = byteArrayInputStream;
                fileOutputStream.getFD().sync();
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    private void a(String str) throws InterruptedException, IOException {
        StringBuilder sb = new StringBuilder();
        a("", "chmod 500 " + str, sb);
        a("", str + " " + e(), sb);
        ALog.w(c, str + " " + e(), new Object[0]);
    }

    private String e() {
        StringBuilder sb = new StringBuilder();
        String str = "/data/data/" + this.d.getPackageName();
        sb.append("-s \"");
        sb.append(str);
        sb.append("/lib/");
        sb.append("\" ");
        sb.append("-n \"runServer\" ");
        sb.append("-p \"");
        sb.append(g());
        sb.append("\" ");
        sb.append("-f \"");
        sb.append(str);
        sb.append("\" ");
        sb.append("-t \"");
        sb.append(this.f);
        sb.append("\" ");
        sb.append("-c \"agoo.pid\" ");
        if (this.m != null) {
            sb.append("-P ");
            sb.append(this.m);
            sb.append(" ");
        }
        if (this.k != null) {
            sb.append("-K ");
            sb.append(this.k);
            sb.append(" ");
        }
        if (this.l != null) {
            sb.append("-U ");
            sb.append(this.l);
            sb.append(" ");
        }
        if (this.q != null) {
            sb.append("-L ");
            sb.append(this.q);
            sb.append(" ");
        }
        sb.append("-D ");
        sb.append(f());
        sb.append(" ");
        if (this.p != null) {
            sb.append("-I ");
            sb.append(this.p);
            sb.append(" ");
        }
        if (this.r > 0) {
            sb.append("-O ");
            sb.append(this.r);
            sb.append(" ");
        }
        String strA = UtilityImpl.a(this.d);
        int iB = UtilityImpl.b(this.d);
        if (strA != null && iB > 0) {
            sb.append("-X ");
            sb.append(strA);
            sb.append(" ");
            sb.append("-Y ");
            sb.append(iB);
            sb.append(" ");
        }
        if (this.s) {
            sb.append("-T ");
        }
        sb.append("-Z ");
        return sb.toString();
    }

    private String f() {
        String strL = UtilityImpl.l(this.d);
        if (TextUtils.isEmpty(strL)) {
            strL = "null";
        }
        String str = "{\"package\":\"" + this.d.getPackageName() + "\",\"appKey\":\"" + this.n + "\",\"utdid\":\"" + strL + "\",\"sdkVersion\":\"" + this.o + "\"}";
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable unused) {
            ALog.e(c, "getReportData failed for url encode, data:" + str, new Object[0]);
            return str;
        }
    }

    private String g() {
        StringBuilder sb = new StringBuilder();
        sb.append(a.replaceAll("\\{packname\\}", this.d.getApplicationContext().getPackageName()));
        if (Build.VERSION.SDK_INT > 15) {
            sb.append(" --user 0");
        }
        return sb.toString();
    }

    private void a(String str, int i2, int i3, String str2, String str3, int i4) {
        String str4 = "AndroidVer=" + Build.VERSION.RELEASE + "&Model=" + Build.MODEL + "&AndroidSdk=" + Build.VERSION.SDK_INT + "&AccsVer=" + Constants.SDK_VERSION_CODE + "&Appkey=" + this.n + "&PullCount=" + str2 + "&Pid=" + str + "&StartTime=" + i2 + "&EndTime=" + i3 + "&ExitCode=" + str3 + "&AliveTime=" + i4;
        Log.d(c, "EUDEMON_ENDSTAT doReportDaemonStat:" + str4);
        UTMini.getInstance().commitEvent(66001, "EUDEMON_ENDSTAT", str4);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x01d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0178 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0187 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0169 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0196 A[Catch: IOException -> 0x019a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x019a, blocks: (B:87:0x0196, B:39:0x0116), top: B:116:0x002c }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x019b -> B:115:0x01a3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void h() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 484
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.b.a.h():void");
    }

    public void a() {
        Log.d(c, "start SoManager");
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        this.t.sendMessage(messageObtain);
    }

    private void i() throws Throwable {
        ALog.d(c, "api level is:" + Build.VERSION.SDK_INT, new Object[0]);
        b(this.d);
        if (Build.VERSION.SDK_INT < 20) {
            try {
                String strD = d();
                h();
                a(strD);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UTMini.getInstance().commitEvent(66001, "EUDEMON_START", "");
    }

    private void j() {
        File file = new File("/data/data/" + this.d.getPackageName(), "daemonserver.pid");
        if (file.exists()) {
            file.delete();
        }
    }

    public static final PendingIntent a(Context context) {
        Intent intent = new Intent();
        intent.setAction(context.getApplicationContext().getPackageName() + ".intent.action.COCKROACH");
        intent.putExtra("cockroach", "cockroach-PPreotect");
        intent.putExtra("pack", context.getApplicationContext().getPackageName());
        return PendingIntent.getService(context, 0, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
    }

    public static void b(Context context) {
        int i2 = Calendar.getInstance().get(11);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager != null) {
            PendingIntent pendingIntentA = a(context);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (i2 > 23 || i2 < 6) {
                ALog.w(c, "time is night, do not wakeup cpu", new Object[0]);
                b(alarmManager, pendingIntentA, jElapsedRealtime);
            } else {
                ALog.w(c, "time is daytime, wakeup cpu for keeping connecntion", new Object[0]);
                a(alarmManager, pendingIntentA, jElapsedRealtime);
            }
        }
    }

    private static void a(AlarmManager alarmManager, PendingIntent pendingIntent, long j2) {
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
            alarmManager.setRepeating(2, (h * 1000) + j2, h * 1000, pendingIntent);
        }
    }

    private static void b(AlarmManager alarmManager, PendingIntent pendingIntent, long j2) {
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(3, j2 + (g * 1000), g * 1000, pendingIntent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0066 -> B:35:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.content.Context r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L82
            java.io.File r5 = r5.getFilesDir()     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = "agoo.pid"
            r1.<init>(r5, r2)     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = com.taobao.accs.b.a.c     // Catch: java.lang.Throwable -> L82
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82
            r2.<init>()     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = "pid path:"
            r2.append(r3)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> L82
            r2.append(r3)     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L82
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L82
            com.taobao.accs.utl.ALog.d(r5, r2, r3)     // Catch: java.lang.Throwable -> L82
            boolean r5 = r1.exists()     // Catch: java.lang.Throwable -> L82
            if (r5 == 0) goto L31
            r1.delete()     // Catch: java.lang.Throwable -> L82
        L31:
            r1.createNewFile()     // Catch: java.lang.Throwable -> L82
            r5 = 0
            int r2 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L53
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L53
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L53
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L4d java.lang.Throwable -> L70
            char[] r5 = r5.toCharArray()     // Catch: java.lang.Throwable -> L4d java.lang.Throwable -> L70
            r3.write(r5)     // Catch: java.lang.Throwable -> L4d java.lang.Throwable -> L70
            r3.close()     // Catch: java.lang.Throwable -> L65
            goto L6f
        L4d:
            r5 = move-exception
            goto L56
        L4f:
            r1 = move-exception
            r3 = r5
            r5 = r1
            goto L71
        L53:
            r1 = move-exception
            r3 = r5
            r5 = r1
        L56:
            java.lang.String r1 = com.taobao.accs.b.a.c     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "save pid error"
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L70
            com.taobao.accs.utl.ALog.e(r1, r2, r5, r4)     // Catch: java.lang.Throwable -> L70
            if (r3 == 0) goto L6f
            r3.close()     // Catch: java.lang.Throwable -> L65
            goto L6f
        L65:
            r5 = move-exception
            java.lang.String r1 = com.taobao.accs.b.a.c
            java.lang.String r2 = "error"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.taobao.accs.utl.ALog.e(r1, r2, r5, r0)
        L6f:
            return
        L70:
            r5 = move-exception
        L71:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.lang.Throwable -> L77
            goto L81
        L77:
            r1 = move-exception
            java.lang.String r2 = com.taobao.accs.b.a.c
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r3 = "error"
            com.taobao.accs.utl.ALog.e(r2, r3, r1, r0)
        L81:
            throw r5
        L82:
            r5 = move-exception
            java.lang.String r1 = com.taobao.accs.b.a.c
            java.lang.String r2 = "error in create file"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.taobao.accs.utl.ALog.e(r1, r2, r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.b.a.c(android.content.Context):void");
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            if (message.what == 0) {
                i();
            } else if (message.what == -1) {
                j();
            }
            return true;
        } catch (Throwable th) {
            ALog.e(c, "handleMessage error", th, new Object[0]);
            return true;
        }
    }

    public static boolean a(String str, String str2, StringBuilder sb) throws InterruptedException, IOException {
        Log.w("TAG.", str2);
        try {
            Process processExec = Runtime.getRuntime().exec("sh");
            DataInputStream dataInputStream = new DataInputStream(processExec.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            if (str != null && !"".equals(str.trim())) {
                dataOutputStream.writeBytes("cd " + str + "\n");
            }
            dataOutputStream.writeBytes(str2 + " &\n");
            dataOutputStream.writeBytes("exit \n");
            dataOutputStream.flush();
            processExec.waitFor();
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String str3 = new String(bArr);
            if (str3.length() == 0) {
                return true;
            }
            sb.append(str3);
            return true;
        } catch (Exception e) {
            sb.append("Exception:");
            sb.append(e.getMessage());
            return false;
        }
    }
}
