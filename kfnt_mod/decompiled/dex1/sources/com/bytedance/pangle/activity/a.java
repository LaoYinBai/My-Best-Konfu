package com.bytedance.pangle.activity;

import android.app.Activity;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final Activity f4520a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4521b;

    /* renamed from: c, reason: collision with root package name */
    private final int f4522c;
    private Method d;

    public a(@NonNull Activity activity, int i, @NonNull String str) {
        this.f4520a = activity;
        this.f4521b = str;
        this.f4522c = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(@NonNull View view) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this.d == null) {
            try {
                Method method = this.f4520a.getClass().getMethod(this.f4521b, View.class);
                if (method != null) {
                    this.d = method;
                }
            } catch (NoSuchMethodException unused) {
            }
            throw new IllegalStateException("Could not find method " + this.f4521b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f4522c);
        }
        try {
            this.d.invoke(this.f4520a, view);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException("Could not execute method for android:onClick", e2);
        }
    }
}
