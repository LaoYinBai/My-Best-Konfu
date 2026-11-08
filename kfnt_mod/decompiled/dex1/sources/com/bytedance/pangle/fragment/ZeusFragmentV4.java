package com.bytedance.pangle.fragment;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.fragment.app.Fragment;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ZeusFragmentV4 extends Fragment {
    public ZeusFragmentV4() throws NoSuchFieldException {
        try {
            Field declaredField = Fragment.class.getDeclaredField("sClassMap");
            declaredField.setAccessible(true);
            ((SimpleArrayMap) declaredField.get(null)).put(getClass().getName(), ZeusFragmentV4.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public Context getContext() {
        try {
            return ZeusTransformUtils.wrapperContext(super.getContext(), (String) MethodUtils.invokeStaticMethod(getClass(), "_GET_PLUGIN_PKG", new Object[0]));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return super.getContext();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return super.getContext();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return super.getContext();
        }
    }
}
