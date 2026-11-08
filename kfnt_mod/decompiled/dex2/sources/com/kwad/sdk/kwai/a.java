package com.kwad.sdk.kwai;

import android.location.Location;
import com.kuaishou.weapon.IWeaponInitParams;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.utils.ao;
import com.kwad.sdk.utils.av;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements IWeaponInitParams {
    public String getAndroidID() {
        return ao.f();
    }

    public String getAppKey() {
        return "400000";
    }

    public String getChannel() {
        return "";
    }

    public String getDeviceId() {
        return av.u();
    }

    public String getHost() {
        return "";
    }

    public String getImei() {
        return ao.d();
    }

    public String[] getImeis() {
        return ao.e();
    }

    public List<String> getInstalledPackages() {
        return ao.n();
    }

    public Location getLocation() {
        return ao.b();
    }

    public String getMacAddress() {
        return ao.h();
    }

    public String getOaid() {
        return com.kwad.sdk.core.f.a.a();
    }

    public int getPS() {
        long j = ao.c() ? 4227L : 0L;
        if (ao.g()) {
            j |= 4;
        }
        if (ao.i()) {
            j |= 2048;
        }
        if (ao.l()) {
            j |= 8192;
        }
        if (ao.a()) {
            j |= 64;
        }
        if (ao.k()) {
            j |= 40;
        }
        if (ao.m()) {
            j |= 16;
        }
        return (int) (j | b.Y());
    }

    public boolean getPrivacySwitch() {
        return true;
    }

    public String getProductName() {
        return "ksadsdk";
    }

    public String getSecKey() {
        return "f12536c198aee4d8198aad2300827430";
    }

    public String getUserId() {
        return "";
    }
}
