package com.kwad.sdk.collector;

import android.os.Environment;
import androidx.annotation.NonNull;
import com.kwad.sdk.collector.AppStatusRules;
import com.kwad.sdk.collector.model.jni.AnalyseTaskNative;
import com.kwad.sdk.collector.model.jni.AppRunningInfoNative;
import com.kwad.sdk.collector.model.jni.RulesTargetNative;
import com.kwad.sdk.collector.model.jni.UploadEntryNative;
import com.kwad.sdk.utils.InstalledAppInfoManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {

    public static class a implements com.kwad.sdk.collector.a {
        private static String a(String str) {
            int iLastIndexOf = str.lastIndexOf("/");
            if (iLastIndexOf < 0 || iLastIndexOf >= str.length() - 1) {
                return null;
            }
            return str.substring(iLastIndexOf + 1);
        }

        private static void a(long j, String str, File file, ArrayList<com.kwad.sdk.collector.model.e> arrayList) {
            if ((file.exists() && file.isFile()) && file.length() <= j) {
                arrayList.add(new com.kwad.sdk.collector.model.kwai.d(str, file.getAbsolutePath()));
            }
        }

        private static void a(com.kwad.sdk.collector.model.kwai.b bVar, long j, @NonNull List<com.kwad.sdk.collector.model.b> list, long j2, AppStatusRules.Strategy strategy) {
            long startTimeWithMS = strategy.getStartTimeWithMS();
            if (j <= 0 || startTimeWithMS + j <= j2) {
                return;
            }
            com.kwad.sdk.collector.model.kwai.b bVarClone = bVar.clone();
            bVarClone.c(j);
            list.add(bVarClone);
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x010d  */
        @Override // com.kwad.sdk.collector.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.List<com.kwad.sdk.collector.model.b> a(com.kwad.sdk.collector.AppStatusRules.Strategy r23, java.util.Map<java.lang.String, com.kwad.sdk.utils.InstalledAppInfoManager.AppPackageInfo> r24) {
            /*
                Method dump skipped, instructions count: 432
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.collector.b.a.a(com.kwad.sdk.collector.AppStatusRules$Strategy, java.util.Map):java.util.List");
        }

        @Override // com.kwad.sdk.collector.a
        public List<com.kwad.sdk.collector.model.e> a(List<com.kwad.sdk.collector.model.d> list, long j, String str) {
            ArrayList arrayList = new ArrayList();
            for (com.kwad.sdk.collector.model.d dVar : list) {
                if (!(dVar instanceof com.kwad.sdk.collector.model.kwai.c)) {
                    return arrayList;
                }
                com.kwad.sdk.collector.model.kwai.c cVar = (com.kwad.sdk.collector.model.kwai.c) dVar;
                List<String> listB = cVar.b();
                if (listB != null) {
                    String strA = cVar.a();
                    if (new File(str, strA).exists()) {
                        for (String str2 : listB) {
                            String strA2 = a(str2);
                            int i = 0;
                            if (str2.endsWith(AppStatusRules.SUFFIX_ALL_CHILDREN)) {
                                File[] fileArrListFiles = new File(str, str2.replace(AppStatusRules.SUFFIX_ALL_CHILDREN, "")).listFiles();
                                if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                                    int length = fileArrListFiles.length;
                                    while (i < length) {
                                        File file = fileArrListFiles[i];
                                        if (file.isFile()) {
                                            a(j, strA, file, arrayList);
                                        }
                                        i++;
                                    }
                                }
                            } else if (str2.endsWith(AppStatusRules.SUFFIX_ALL_FILE)) {
                                File[] fileArrListFiles2 = new File(str, str2.substring(0, str2.lastIndexOf("/"))).listFiles();
                                String strReplace = str2.replace(AppStatusRules.SUFFIX_ALL_FILE, "");
                                int length2 = fileArrListFiles2.length;
                                while (i < length2) {
                                    File file2 = fileArrListFiles2[i];
                                    if (file2.isFile() && file2.getAbsolutePath().contains(strReplace)) {
                                        a(j, strA, file2, arrayList);
                                    }
                                    i++;
                                }
                            } else if (strA2 == null || !strA2.startsWith(AppStatusRules.SUFFIX_ALL_FILE)) {
                                a(j, strA, new File(str, str2), arrayList);
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    /* renamed from: com.kwad.sdk.collector.b$b, reason: collision with other inner class name */
    public static class C0028b implements com.kwad.sdk.collector.a {
        @Override // com.kwad.sdk.collector.a
        public List<com.kwad.sdk.collector.model.b> a(AppStatusRules.Strategy strategy, Map<String, InstalledAppInfoManager.AppPackageInfo> map) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            com.kwad.sdk.collector.model.c.a(strategy, map, arrayList2);
            File file = new File(Environment.getExternalStorageDirectory(), "/Android/data/");
            long[] jArr = new long[arrayList2.size()];
            for (int i = 0; i < jArr.length; i++) {
                com.kwad.sdk.collector.model.a aVar = (com.kwad.sdk.collector.model.a) arrayList2.get(i);
                if (!(aVar instanceof AnalyseTaskNative)) {
                    return arrayList;
                }
                jArr[i] = ((AnalyseTaskNative) aVar).getNativePtr();
            }
            long[] jArrAnalysis = AppStatusNative.analysis(jArr, file.getAbsolutePath() + "/");
            com.kwad.sdk.core.d.a.a("AppStatusAnalyserNative", "analysisByFile: runningInfoPtrs: " + jArrAnalysis);
            for (long j : jArrAnalysis) {
                arrayList.add(new AppRunningInfoNative(j));
            }
            com.kwad.sdk.core.d.a.a("AppStatusAnalyserNative", "analysisByFile: info size: " + arrayList.size());
            return arrayList;
        }

        @Override // com.kwad.sdk.collector.a
        public List<com.kwad.sdk.collector.model.e> a(List<com.kwad.sdk.collector.model.d> list, long j, String str) {
            int size = list.size();
            long[] jArr = new long[list.size()];
            for (int i = 0; i < size; i++) {
                com.kwad.sdk.collector.model.d dVar = list.get(i);
                if (dVar instanceof RulesTargetNative) {
                    jArr[i] = ((RulesTargetNative) dVar).getNativePtr();
                }
            }
            long[] jArrNativeGetUploadEntry = AppStatusNative.nativeGetUploadEntry(jArr, j, str);
            ArrayList arrayList = new ArrayList();
            for (long j2 : jArrNativeGetUploadEntry) {
                arrayList.add(new UploadEntryNative(j2));
            }
            return arrayList;
        }
    }

    public static com.kwad.sdk.collector.a a() {
        return com.kwad.sdk.core.config.b.ai() ? new C0028b() : new a();
    }
}
