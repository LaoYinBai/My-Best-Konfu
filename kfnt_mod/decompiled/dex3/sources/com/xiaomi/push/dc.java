package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class dc {
    private static String a = "/MiPushLog";

    /* renamed from: a, reason: collision with other field name */
    private int f218a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f221a;

    /* renamed from: b, reason: collision with other field name */
    private String f222b;
    private String c;

    /* renamed from: a, reason: collision with other field name */
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat f219a = new SimpleDateFormat(DateUtils.FORMAT_ONE);
    private int b = 2097152;

    /* renamed from: a, reason: collision with other field name */
    private ArrayList<File> f220a = new ArrayList<>();

    dc() {
    }

    private void a(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) throws IOException {
        char[] cArr = new char[4096];
        int i = bufferedReader.read(cArr);
        boolean z = false;
        while (i != -1 && !z) {
            String str = new String(cArr, 0, i);
            Matcher matcher = pattern.matcher(str);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 >= i || !matcher.find(i2)) {
                    break;
                }
                int iStart = matcher.start();
                String strSubstring = str.substring(iStart, this.f222b.length() + iStart);
                if (this.f221a) {
                    if (strSubstring.compareTo(this.c) > 0) {
                        i = iStart;
                        z = true;
                        break;
                    }
                } else if (strSubstring.compareTo(this.f222b) >= 0) {
                    this.f221a = true;
                    i3 = iStart;
                }
                int iIndexOf = str.indexOf(10, iStart);
                if (iIndexOf == -1) {
                    iIndexOf = this.f222b.length();
                }
                i2 = iStart + iIndexOf;
            }
            if (this.f221a) {
                int i4 = i - i3;
                this.f218a += i4;
                if (z) {
                    bufferedWriter.write(cArr, i3, i4);
                    return;
                } else {
                    bufferedWriter.write(cArr, i3, i4);
                    if (this.f218a > this.b) {
                        return;
                    }
                }
            }
            i = bufferedReader.read(cArr);
        }
    }

    private void a(File file) throws Throwable {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        String str;
        Pattern patternCompile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            } catch (Throwable th) {
                th = th;
                bufferedWriter = bufferedReader2;
            }
            try {
                bufferedWriter.write("model :" + Build.MODEL + "; os :" + Build.VERSION.INCREMENTAL + "; uid :" + com.xiaomi.push.service.bk.m619a() + "; lng :" + Locale.getDefault().toString() + "; sdk :43; andver :" + Build.VERSION.SDK_INT + "\n");
                this.f218a = 0;
                Iterator<File> it = this.f220a.iterator();
                while (it.hasNext()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(it.next())));
                    try {
                        a(bufferedReader, bufferedWriter, patternCompile);
                        bufferedReader.close();
                        bufferedReader2 = bufferedReader;
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedReader2 = bufferedWriter;
                        str = "LOG: filter error = " + e.getMessage();
                        com.xiaomi.channel.commonutils.logger.b.c(str);
                        y.a(bufferedReader2);
                        y.a(bufferedReader);
                        return;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader2 = bufferedWriter;
                        str = "LOG: filter error = " + e.getMessage();
                        com.xiaomi.channel.commonutils.logger.b.c(str);
                        y.a(bufferedReader2);
                        y.a(bufferedReader);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        y.a(bufferedWriter);
                        y.a(bufferedReader2);
                        throw th;
                    }
                }
                bufferedWriter.write(cu.a().c());
                y.a(bufferedWriter);
                y.a(bufferedReader2);
            } catch (FileNotFoundException e3) {
                e = e3;
                bufferedReader = bufferedReader2;
            } catch (IOException e4) {
                e = e4;
                bufferedReader = bufferedReader2;
            } catch (Throwable th3) {
                th = th3;
                y.a(bufferedWriter);
                y.a(bufferedReader2);
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            bufferedReader = null;
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = null;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    dc m193a(File file) {
        if (file.exists()) {
            this.f220a.add(file);
        }
        return this;
    }

    dc a(Date date, Date date2) {
        String str;
        if (date.after(date2)) {
            this.f222b = this.f219a.format(date2);
            str = this.f219a.format(date);
        } else {
            this.f222b = this.f219a.format(date);
            str = this.f219a.format(date2);
        }
        this.c = str;
        return this;
    }

    File a(Context context, Date date, Date date2, File file) throws Throwable {
        File file2;
        File file3;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            file2 = new File(context.getExternalFilesDir(null), "dump");
            if (!file2.exists()) {
                file2 = context.getFilesDir();
            }
            m193a(new File(file2, "xmsf.log.1"));
            file3 = new File(file2, "xmsf.log");
        } else {
            file2 = new File(context.getExternalFilesDir(null) + a);
            m193a(new File(file2, "log0.txt"));
            file3 = new File(file2, "log1.txt");
        }
        m193a(file3);
        if (!file2.isDirectory()) {
            return null;
        }
        File file4 = new File(file, date.getTime() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + date2.getTime() + ".zip");
        if (file4.exists()) {
            return null;
        }
        a(date, date2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        File file5 = new File(file, "log.txt");
        a(file5);
        com.xiaomi.channel.commonutils.logger.b.c("LOG: filter cost = " + (System.currentTimeMillis() - jCurrentTimeMillis));
        if (file5.exists()) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            y.a(file4, file5);
            com.xiaomi.channel.commonutils.logger.b.c("LOG: zip cost = " + (System.currentTimeMillis() - jCurrentTimeMillis2));
            file5.delete();
            if (file4.exists()) {
                return file4;
            }
        }
        return null;
    }

    void a(int i) {
        if (i != 0) {
            this.b = i;
        }
    }
}
