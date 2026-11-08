package com.bytedance.pangle;

import android.os.Build;
import androidx.annotation.Keep;
import dalvik.system.DexClassLoader;
import java.util.Iterator;
import java.util.List;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PluginClassLoader extends DexClassLoader {
    private static final String TAG = "PluginClassLoader";
    private final ClassLoader hostClassLoader;
    private final List<ClassLoader> otherPluginClassLoader;

    public PluginClassLoader(String str, String str2, String str3, List<ClassLoader> list) {
        super(str, str2, str3, getSystemClassLoader().getParent());
        this.hostClassLoader = PluginClassLoader.class.getClassLoader();
        this.otherPluginClassLoader = list;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> clsLoadClass = null;
        try {
            e = null;
            clsLoadClass = super.findClass(str);
        } catch (ClassNotFoundException e) {
            e = e;
        }
        StringBuilder sb = new StringBuilder("loadClass from :\n");
        if (clsLoadClass == null && this.otherPluginClassLoader != null) {
            Iterator<ClassLoader> it = this.otherPluginClassLoader.iterator();
            while (it.hasNext()) {
                try {
                    clsLoadClass = it.next().loadClass(str);
                } catch (ClassNotFoundException e2) {
                    e = handleException(sb, e, e2);
                }
            }
        }
        if (clsLoadClass == null) {
            try {
                clsLoadClass = this.hostClassLoader.loadClass(str);
            } catch (ClassNotFoundException e3) {
                e = handleException(sb, e, e3);
            }
        }
        if (clsLoadClass != null) {
            return clsLoadClass;
        }
        if (e == null) {
            throw new ClassNotFoundException(str + " class not found in PluginClassLoader");
        }
        throw e;
    }

    private ClassNotFoundException handleException(StringBuilder sb, ClassNotFoundException classNotFoundException, ClassNotFoundException classNotFoundException2) {
        if (classNotFoundException == null) {
            return classNotFoundException2;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            classNotFoundException.addSuppressed(classNotFoundException2);
            return classNotFoundException;
        }
        sb.append(classNotFoundException2.getCause());
        sb.append("\n");
        return new ClassNotFoundException(sb.toString(), classNotFoundException2);
    }
}
