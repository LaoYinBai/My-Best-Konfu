package com.bytedance.a;

import com.bytedance.applog.profile.UserProfileCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aq implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3366a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ba f3367b;

    public aq(ba baVar, int i) {
        this.f3367b = baVar;
        this.f3366a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        UserProfileCallback userProfileCallback = this.f3367b.e;
        if (userProfileCallback != null) {
            userProfileCallback.onFail(this.f3366a);
        }
    }
}
