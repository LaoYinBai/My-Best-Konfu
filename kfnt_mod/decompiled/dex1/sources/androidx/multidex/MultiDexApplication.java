package androidx.multidex;

import android.app.Application;
import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MultiDexApplication extends Application {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
