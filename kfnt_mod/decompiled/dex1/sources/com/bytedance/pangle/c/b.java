package com.bytedance.pangle.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.PluginContext;
import com.bytedance.pangle.log.ZeusLogger;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<File> f4536a = new HashSet();

    /* renamed from: b, reason: collision with root package name */
    private static final boolean f4537b = a(System.getProperty("java.vm.version"));

    public static void a(PluginContext pluginContext) {
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Installing application");
        if (f4537b) {
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex VM has multidex support, MultiDex support library is disabled.");
            return;
        }
        if (Build.VERSION.SDK_INT < 4) {
            throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT + " is unsupported. Min SDK version is 4.");
        }
        try {
            ApplicationInfo applicationInfoA = a((Context) pluginContext);
            if (applicationInfoA == null) {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
            } else {
                a(applicationInfoA, pluginContext, new File(pluginContext.mPlugin.mHostApplicationInfoHookSomeField.sourceDir), "secondary-dexes", applicationInfoA.packageName);
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex install done");
            }
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex MultiDex installation failure", e);
            throw new RuntimeException("MultiDex installation failed (" + e.getMessage() + ").");
        }
    }

    private static void a(@NonNull ApplicationInfo applicationInfo, PluginContext pluginContext, File file, String str, String str2) {
        synchronized (f4536a) {
            if (!f4536a.contains(file)) {
                f4536a.add(file);
                if (Build.VERSION.SDK_INT > 20) {
                    ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex MultiDex is not guaranteed to work in SDK version " + Build.VERSION.SDK_INT + ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
                }
                try {
                    PluginClassLoader pluginClassLoader = pluginContext.mPlugin.mClassLoader;
                    if (pluginClassLoader == null) {
                        ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Context class loader is null. Must be running in test mode. Skip patching.");
                    } else {
                        try {
                            String str3 = applicationInfo.packageName;
                            b(pluginContext);
                        } catch (Throwable th) {
                            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", th);
                        }
                        File fileA = a(pluginContext, pluginContext.getDataDir(), str);
                        com.bytedance.pangle.c.a aVar = new com.bytedance.pangle.c.a(file, fileA);
                        IOException e = null;
                        try {
                            try {
                                a(pluginClassLoader, fileA, aVar.a(pluginContext, str2, false));
                            } catch (IOException e2) {
                                ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDexFailed to install extracted secondary dex files, retrying with forced extraction", e2);
                                a(pluginClassLoader, fileA, aVar.a(pluginContext, str2, true));
                            }
                            try {
                            } catch (IOException e3) {
                                e = e3;
                            }
                            if (e != null) {
                                throw e;
                            }
                        } finally {
                            try {
                                aVar.close();
                            } catch (IOException unused) {
                            }
                        }
                    }
                } catch (RuntimeException e4) {
                    ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", e4);
                }
            }
        }
    }

    private static ApplicationInfo a(Context context) {
        try {
            return context.getApplicationInfo();
        } catch (RuntimeException e) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDexFailure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static boolean a(String str) throws NumberFormatException {
        boolean z = false;
        if (str != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
            if (matcher.matches()) {
                try {
                    int i = Integer.parseInt(matcher.group(1));
                    int i2 = Integer.parseInt(matcher.group(2));
                    if (i > 2 || (i == 2 && i2 > 0)) {
                        z = true;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        StringBuilder sb = new StringBuilder("PluginMultiDex VM with version ");
        sb.append(str);
        sb.append(z ? " has multidex support" : " does not have multidex support");
        ZeusLogger.i(ZeusLogger.TAG_LOAD, sb.toString());
        return z;
    }

    private static void a(ClassLoader classLoader, File file, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
        if (list.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            C0137b.a(classLoader, list, file);
        } else if (Build.VERSION.SDK_INT >= 14) {
            a.a(classLoader, list);
        } else {
            c.a(classLoader, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Field b(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method b(Object obj, String str, Class... clsArr) throws NoSuchMethodException, SecurityException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    private static void b(PluginContext pluginContext) {
        File file = new File(pluginContext.getDataDir().getAbsolutePath() + "/files/secondary-dexes");
        if (file.isDirectory()) {
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Clearing old secondary dex dir (" + file.getPath() + ").");
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to list secondary dex dir content (" + file.getPath() + ").");
                return;
            }
            for (File file2 : fileArrListFiles) {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                if (file2.delete()) {
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Deleted old file " + file2.getPath());
                } else {
                    ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to delete old file " + file2.getPath());
                }
            }
            if (file.delete()) {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Deleted old secondary dex dir " + file.getPath());
            } else {
                ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to delete secondary dex dir " + file.getPath());
            }
        }
    }

    private static File a(Context context, File file, String str) throws JSONException, IOException {
        File file2 = new File(file, "code_cache");
        try {
            a(file2);
        } catch (IOException unused) {
            file2 = new File(context.getFilesDir(), "code_cache");
            a(file2);
        }
        File file3 = new File(file2, str);
        a(file3);
        return file3;
    }

    private static void a(File file) throws JSONException, IOException {
        file.mkdir();
        if (file.isDirectory()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to create dir " + file.getPath() + ". Parent file is null.");
        } else {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
        }
        throw new IOException("Failed to create directory " + file.getPath());
    }

    static final class c {
        static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            int size = list.size();
            Field fieldB = b.b(classLoader, "path");
            StringBuilder sb = new StringBuilder((String) fieldB.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<? extends File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                String absolutePath = next.getAbsolutePath();
                sb.append(':');
                sb.append(absolutePath);
                int iPreviousIndex = listIterator.previousIndex();
                strArr[iPreviousIndex] = absolutePath;
                fileArr[iPreviousIndex] = next;
                zipFileArr[iPreviousIndex] = new ZipFile(next);
                dexFileArr[iPreviousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
            }
            fieldB.set(classLoader, sb.toString());
            b.a(classLoader, "mPaths", strArr);
            b.a(classLoader, "mFiles", fileArr);
            b.a(classLoader, "mZips", zipFileArr);
            b.a(classLoader, "mDexs", dexFileArr);
        }
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private static final int f4538a = 4;

        /* renamed from: b, reason: collision with root package name */
        private final InterfaceC0135a f4539b;

        /* renamed from: com.bytedance.pangle.c.b$a$a, reason: collision with other inner class name */
        interface InterfaceC0135a {
            Object a(File file, DexFile dexFile);
        }

        static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Object obj = b.b(classLoader, "pathList").get(classLoader);
            a aVar = new a();
            Object[] objArr = new Object[list.size()];
            for (int i = 0; i < objArr.length; i++) {
                File file = list.get(i);
                InterfaceC0135a interfaceC0135a = aVar.f4539b;
                String path = file.getPath();
                File parentFile = file.getParentFile();
                String name = file.getName();
                objArr[i] = interfaceC0135a.a(file, DexFile.loadDex(path, new File(parentFile, name.substring(0, name.length() - f4538a) + ".dex").getPath(), 0));
            }
            try {
                b.a(obj, "dexElements", objArr);
            } catch (NoSuchFieldException e) {
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed find field 'dexElements' attempting 'pathElements'", e);
                b.a(obj, "pathElements", objArr);
            }
        }

        private a() throws ClassNotFoundException {
            InterfaceC0135a dVar;
            Class<?> cls = Class.forName("dalvik.system.DexPathList$Element");
            try {
                try {
                    dVar = new C0136b(cls);
                } catch (NoSuchMethodException unused) {
                    dVar = new c(cls);
                }
            } catch (NoSuchMethodException unused2) {
                dVar = new d(cls);
            }
            this.f4539b = dVar;
        }

        static class d implements InterfaceC0135a {

            /* renamed from: a, reason: collision with root package name */
            private final Constructor<?> f4542a;

            d(Class<?> cls) {
                this.f4542a = cls.getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                this.f4542a.setAccessible(true);
            }

            @Override // com.bytedance.pangle.c.b.a.InterfaceC0135a
            public final Object a(File file, DexFile dexFile) {
                return this.f4542a.newInstance(file, Boolean.FALSE, file, dexFile);
            }
        }

        static class c implements InterfaceC0135a {

            /* renamed from: a, reason: collision with root package name */
            private final Constructor<?> f4541a;

            c(Class<?> cls) {
                this.f4541a = cls.getConstructor(File.class, File.class, DexFile.class);
                this.f4541a.setAccessible(true);
            }

            @Override // com.bytedance.pangle.c.b.a.InterfaceC0135a
            public final Object a(File file, DexFile dexFile) {
                return this.f4541a.newInstance(file, file, dexFile);
            }
        }

        /* renamed from: com.bytedance.pangle.c.b$a$b, reason: collision with other inner class name */
        static class C0136b implements InterfaceC0135a {

            /* renamed from: a, reason: collision with root package name */
            private final Constructor<?> f4540a;

            C0136b(Class<?> cls) {
                this.f4540a = cls.getConstructor(File.class, ZipFile.class, DexFile.class);
                this.f4540a.setAccessible(true);
            }

            @Override // com.bytedance.pangle.c.b.a.InterfaceC0135a
            public final Object a(File file, DexFile dexFile) {
                return this.f4540a.newInstance(file, new ZipFile(file), dexFile);
            }
        }
    }

    /* renamed from: com.bytedance.pangle.c.b$b, reason: collision with other inner class name */
    static final class C0137b {
        static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
            IOException[] iOExceptionArr;
            Object obj = b.b(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            b.a(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Exception in makeDexElement", (IOException) it.next());
                }
                Field fieldB = b.b(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldB.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldB.set(obj, iOExceptionArr);
                IOException iOException = new IOException("I/O exception during makeDexElement");
                iOException.initCause((Throwable) arrayList.get(0));
                throw iOException;
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) b.b(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, arrayList, file, arrayList2);
        }
    }

    static /* synthetic */ void a(Object obj, String str, Object[] objArr) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field fieldB = b(obj, str);
        Object[] objArr2 = (Object[]) fieldB.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        fieldB.set(obj, objArr3);
    }
}
