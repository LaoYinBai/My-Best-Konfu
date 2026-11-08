package com.bytedance.pangle.transform;

import androidx.annotation.Keep;
import androidx.fragment.app.FragmentActivity;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class HostPartUtils {
    private Class fragmentActivityClazz;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final HostPartUtils f4707a;

        static {
            System.out.println("HostPartUtils SingletonHolder");
            f4707a = new HostPartUtils();
        }
    }

    public HostPartUtils() {
        try {
            this.fragmentActivityClazz = FragmentActivity.class;
        } catch (Throwable unused) {
        }
    }

    public static final Class getFragmentActivityClass() {
        System.out.println("HostPartUtils getFragmentActivityClass");
        return a.f4707a.fragmentActivityClazz;
    }

    public static FragmentActivity getFragmentActivity(Object obj, String str) {
        return (FragmentActivity) ZeusTransformUtils._getActivity(obj, str);
    }
}
