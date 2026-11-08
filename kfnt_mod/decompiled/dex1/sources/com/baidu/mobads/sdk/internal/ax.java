package com.baidu.mobads.sdk.internal;

import android.content.Context;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import com.baidu.mobads.sdk.api.MobadsPermissionSettings;
import com.baidu.mobads.sdk.internal.bh;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ax {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3136a = "ContainerFactoryBuilder";
    private static IXAdContainerFactory e;

    /* renamed from: c, reason: collision with root package name */
    private Context f3138c;
    private Class<?> d;

    /* renamed from: b, reason: collision with root package name */
    public double f3137b = 0.1d;
    private bb f = bb.a();

    public ax(Class<?> cls, Context context) {
        this.d = null;
        this.d = cls;
        this.f3138c = context;
    }

    public IXAdContainerFactory a() throws bh.a {
        if (e == null) {
            try {
                e = (IXAdContainerFactory) this.d.getDeclaredConstructor(Context.class).newInstance(this.f3138c);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p_ver", "9.171");
                e.initConfig(jSONObject);
                this.f3137b = e.getRemoteVersion();
                e.onTaskDistribute(al.f3114a, MobadsPermissionSettings.getPermissionInfo());
                e.initCommonModuleObj(l.a());
            } catch (Throwable th) {
                this.f.b(f3136a, th.getMessage());
                throw new bh.a("ContainerFactory() failed, possibly API incompatible: " + th.getMessage());
            }
        }
        return e;
    }

    public void b() {
        e = null;
    }
}
