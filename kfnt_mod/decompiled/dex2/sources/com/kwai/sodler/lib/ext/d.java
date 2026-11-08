package com.kwai.sodler.lib.ext;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"LongLogTag"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {

    private static final class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Object obj = com.kwai.sodler.lib.ext.e.a(classLoader, "pathList").get(classLoader);
            Field fieldA = com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryDirectories");
            File[] fileArr = (File[]) fieldA.get(obj);
            ArrayList arrayList = new ArrayList();
            for (File file2 : fileArr) {
                if (!file.equals(file2)) {
                    arrayList.add(file2);
                }
            }
            arrayList.add(file);
            fieldA.set(obj, arrayList.toArray(new File[0]));
        }
    }

    private static final class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Object obj = com.kwai.sodler.lib.ext.e.a(classLoader, "pathList").get(classLoader);
            Field fieldA = com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryDirectories");
            Collection arrayList = (List) fieldA.get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            ArrayList arrayList2 = new ArrayList(arrayList);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                if (file.equals((File) it.next())) {
                    return;
                }
            }
            arrayList2.add(file);
            fieldA.set(obj, arrayList2);
            if (fieldA.get(obj) != arrayList2) {
                com.kwai.sodler.lib.ext.e.b(obj, "nativeLibraryDirectories").set(obj, arrayList2);
            }
            List arrayList3 = (List) com.kwai.sodler.lib.ext.e.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList(2);
            }
            ArrayList arrayList4 = new ArrayList(arrayList2.size() + arrayList3.size() + 1);
            arrayList4.addAll(arrayList2);
            arrayList4.addAll(arrayList3);
            Object obj2 = (Object[]) com.kwai.sodler.lib.ext.e.a(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList4, null, new ArrayList());
            Field fieldA2 = com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryPathElements");
            fieldA2.set(obj, obj2);
            if (fieldA2.get(obj) != obj2) {
                com.kwai.sodler.lib.ext.e.b(obj, "nativeLibraryPathElements").set(obj, obj2);
            }
        }
    }

    private static final class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, IllegalArgumentException {
            Object obj = com.kwai.sodler.lib.ext.e.a(classLoader, "pathList").get(classLoader);
            List arrayList = (List) com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (file.equals((File) it.next())) {
                    return;
                }
            }
            arrayList.add(file);
            List arrayList2 = (List) com.kwai.sodler.lib.ext.e.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) com.kwai.sodler.lib.ext.e.a(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList3, null, new ArrayList()));
        }
    }

    /* renamed from: com.kwai.sodler.lib.ext.d$d, reason: collision with other inner class name */
    private static final class C0099d {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, IllegalArgumentException {
            Object obj = com.kwai.sodler.lib.ext.e.a(classLoader, "pathList").get(classLoader);
            List arrayList = (List) com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (file.equals((File) it.next())) {
                    return;
                }
            }
            arrayList.add(file);
            List arrayList2 = (List) com.kwai.sodler.lib.ext.e.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            com.kwai.sodler.lib.ext.e.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) com.kwai.sodler.lib.ext.e.a(obj, "makePathElements", List.class).invoke(obj, arrayList3));
        }
    }

    private static final class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            String str;
            String path = file.getPath();
            Field fieldA = com.kwai.sodler.lib.ext.e.a(classLoader, "libPath");
            String strValueOf = String.valueOf(fieldA.get(classLoader));
            if (TextUtils.isEmpty(strValueOf)) {
                str = path;
            } else {
                str = strValueOf + ":" + path;
            }
            fieldA.set(classLoader, str);
            Field fieldA2 = com.kwai.sodler.lib.ext.e.a(classLoader, "libraryPathElements");
            List list = (List) fieldA2.get(classLoader);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (path.equals((String) it.next())) {
                    return;
                }
            }
            list.add(path);
            fieldA2.set(classLoader, list);
        }
    }

    public static synchronized void a(ClassLoader classLoader, File file) {
        if (file != null) {
            if (file.exists()) {
                if ((Build.VERSION.SDK_INT == 25 && Build.VERSION.PREVIEW_SDK_INT != 0) || Build.VERSION.SDK_INT > 25) {
                    try {
                        C0099d.b(classLoader, file);
                    } catch (Throwable th) {
                        Log.e("Sodler.ShareLibraryLoader", String.format("installNativeLibraryPath, v25 fail, sdk: %d, error: %s, try to fallback to V23", Integer.valueOf(Build.VERSION.SDK_INT), th.getMessage()));
                        b.b(classLoader, file);
                    }
                    return;
                }
                if (Build.VERSION.SDK_INT == 24) {
                    try {
                        c.b(classLoader, file);
                    } finally {
                        a.b(classLoader, file);
                    }
                    return;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    try {
                        b.b(classLoader, file);
                    } finally {
                        a.b(classLoader, file);
                    }
                } else if (Build.VERSION.SDK_INT >= 14) {
                    a.b(classLoader, file);
                } else {
                    e.b(classLoader, file);
                }
                return;
            }
        }
        Log.e("Sodler.ShareLibraryLoader", String.format("installNativeLibraryPath, folder %s is illegal", file));
    }
}
