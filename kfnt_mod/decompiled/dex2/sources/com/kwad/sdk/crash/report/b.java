package com.kwad.sdk.crash.report;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.crash.model.message.ExceptionMessage;
import com.kwad.sdk.crash.model.message.MemoryInfo;
import com.kwad.sdk.crash.model.message.ThreadInfo;
import com.kwad.sdk.crash.utils.f;
import com.kwad.sdk.crash.utils.h;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    protected c a;
    protected String b = "";

    private String a(String str) {
        return (str == null || !str.contains("-")) ? str : str.substring(0, str.lastIndexOf(45));
    }

    protected abstract ExceptionMessage a(@NonNull File file, File file2, File file3, String str);

    public void a(c cVar) {
        this.a = cVar;
    }

    @SuppressLint({"CheckResult"})
    public void a(File file) {
        com.kwad.sdk.core.d.a.a("ExceptionCollector", "reportException dir =" + file);
        for (File file2 : file.listFiles(new FileFilter() { // from class: com.kwad.sdk.crash.report.b.1
            @Override // java.io.FileFilter
            public boolean accept(File file3) {
                return file3.getName().endsWith(".dump");
            }
        })) {
            b(file2);
        }
    }

    protected void a(File file, ExceptionMessage exceptionMessage) throws Throwable {
        BufferedReader bufferedReader;
        String line;
        String str;
        try {
            MemoryInfo memoryInfo = new MemoryInfo(exceptionMessage.mMemoryInfo);
            ArrayList arrayList = new ArrayList();
            BufferedReader bufferedReader2 = null;
            BufferedReader bufferedReader3 = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                ThreadInfo threadInfo = new ThreadInfo();
                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.isEmpty()) {
                        arrayList.add(threadInfo);
                        threadInfo = new ThreadInfo();
                    } else if (line.startsWith("at ") || line.startsWith("(no ")) {
                        if (threadInfo.mTrace == null) {
                            str = line;
                        } else {
                            str = threadInfo.mTrace + line;
                        }
                        threadInfo.mTrace = str;
                        threadInfo.mTrace += "#";
                    } else {
                        threadInfo.mName = line;
                    }
                }
                memoryInfo.mJavaThreads = arrayList;
                exceptionMessage.mMemoryInfo = memoryInfo.toJson().toString();
                com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader);
                bufferedReader2 = line;
            } catch (IOException e2) {
                e = e2;
                bufferedReader3 = bufferedReader;
                com.kwad.sdk.core.d.a.b(e);
                com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader3);
                bufferedReader2 = bufferedReader3;
            } catch (Throwable th2) {
                th = th2;
                com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader);
                throw th;
            }
        } catch (Exception e3) {
            com.kwad.sdk.core.d.a.b(e3);
        }
    }

    public void b(File file) {
        String strA = f.a(file.getPath());
        File file2 = new File(strA + ".msg");
        File file3 = new File(strA + ".log");
        File file4 = new File(strA + ".blog");
        File file5 = new File(strA + ".jtrace");
        ArrayList arrayList = new ArrayList();
        try {
            try {
                try {
                    ExceptionMessage exceptionMessageA = a(file, file2, file3, strA);
                    this.a.a(exceptionMessageA);
                    f.a(file4);
                    ArrayList arrayList2 = new ArrayList();
                    Collections.addAll(arrayList2, file3, file4);
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        if (!((File) it.next()).exists()) {
                            it.remove();
                        }
                    }
                    File file6 = new File(file.getParentFile().getParent(), "custom");
                    if (file6.exists()) {
                        for (File file7 : file6.listFiles()) {
                            if (!file7.isDirectory() && (file7.getName().startsWith(exceptionMessageA.mLogUUID) || file7.getName().startsWith(a(exceptionMessageA.mLogUUID)))) {
                                arrayList.add(file7);
                            }
                        }
                        arrayList2.addAll(arrayList);
                    }
                    h.b(file.getPath());
                    h.b(file3.getPath());
                    h.b(file4.getPath());
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        h.b(((File) it2.next()).getPath());
                    }
                    h.b(file5.getPath());
                    f.b(com.kwad.sdk.crash.b.b.b);
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                    this.a.b("report_ex_error", f.a(th));
                    h.b(file.getPath());
                    h.b(file3.getPath());
                    h.b(file4.getPath());
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        h.b(((File) it3.next()).getPath());
                    }
                    h.b(file5.getPath());
                    f.b(com.kwad.sdk.crash.b.b.b);
                }
            } catch (Throwable th2) {
                com.kwad.sdk.core.d.a.b(th2);
            }
        } catch (Throwable th3) {
            try {
                h.b(file.getPath());
                h.b(file3.getPath());
                h.b(file4.getPath());
                Iterator it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    h.b(((File) it4.next()).getPath());
                }
                h.b(file5.getPath());
                f.b(com.kwad.sdk.crash.b.b.b);
            } catch (Throwable th4) {
                com.kwad.sdk.core.d.a.b(th4);
            }
            throw th3;
        }
    }

    protected void b(File file, ExceptionMessage exceptionMessage) throws Throwable {
        BufferedReader bufferedReader;
        String str;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                boolean z = false;
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader);
                            return;
                        }
                        if (!z && line.contains("JNI DETECTED ERROR IN APPLICATION")) {
                            exceptionMessage.mJNIError = line.substring(line.indexOf("JNI DETECTED ERROR IN APPLICATION"));
                            z = true;
                        } else if (line.contains("Waiting for a blocking GC ") || line.contains("WaitForGcToComplete")) {
                            if (TextUtils.isEmpty(exceptionMessage.mGCInfo)) {
                                sb = new StringBuilder();
                                sb.append(line);
                                sb.append("\n");
                            } else {
                                sb = new StringBuilder();
                                sb.append(exceptionMessage.mGCInfo);
                                sb.append(line);
                                sb.append("\n");
                            }
                            exceptionMessage.mGCInfo = sb.toString();
                        } else if (line.contains("dvm_lock_sample")) {
                            if (TextUtils.isEmpty(exceptionMessage.mLockInfo)) {
                                sb2 = new StringBuilder();
                                sb2.append(line);
                                sb2.append("\n");
                            } else {
                                sb2 = new StringBuilder();
                                sb2.append(exceptionMessage.mLockInfo);
                                sb2.append(line);
                                sb2.append("\n");
                            }
                            exceptionMessage.mLockInfo = sb2.toString();
                        } else if (line.contains("Long monitor")) {
                            if (TextUtils.isEmpty(exceptionMessage.mMonitorInfo)) {
                                sb3 = new StringBuilder();
                                sb3.append(line);
                                sb3.append("\n");
                            } else {
                                sb3 = new StringBuilder();
                                sb3.append(exceptionMessage.mMonitorInfo);
                                sb3.append(line);
                                sb3.append("\n");
                            }
                            exceptionMessage.mMonitorInfo = sb3.toString();
                        } else if (line.contains("Slow Looper")) {
                            if (TextUtils.isEmpty(exceptionMessage.mSlowLooper)) {
                                sb4 = new StringBuilder();
                                sb4.append(line);
                                sb4.append("\n");
                            } else {
                                sb4 = new StringBuilder();
                                sb4.append(exceptionMessage.mSlowLooper);
                                sb4.append(line);
                                sb4.append("\n");
                            }
                            exceptionMessage.mSlowLooper = sb4.toString();
                        } else if (line.contains("Slow Operation")) {
                            if (TextUtils.isEmpty(exceptionMessage.mSlowOperation)) {
                                sb5 = new StringBuilder();
                                sb5.append(line);
                                sb5.append("\n");
                            } else {
                                sb5 = new StringBuilder();
                                sb5.append(exceptionMessage.mSlowOperation);
                                sb5.append(line);
                                sb5.append("\n");
                            }
                            exceptionMessage.mSlowOperation = sb5.toString();
                        }
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedReader2 = bufferedReader;
                        str = this.b + e + "\n";
                        this.b = str;
                        com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader2);
                        return;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        str = this.b + e + "\n";
                        this.b = str;
                        com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader2);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }
}
