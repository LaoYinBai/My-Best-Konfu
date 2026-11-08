package com.qq.e.comm.managers.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.managers.setting.f;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.qq.e.comm.util.SystemUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SM {
    private String b;
    private a c;
    private String e;
    private e f;
    private String h;
    private String k;
    private Context m;
    private String l = "";
    private a a = new a();
    private a d = new a();
    private e g = new c();
    private d i = new d();
    private final d j = new d();
    private String n = SystemUtil.buildNewPathByProcessName("e_qq_com_setting");

    public SM(Context context) {
        this.m = context;
        d();
        c();
        a();
        b();
    }

    private void a() {
        f.b<a> bVarA = f.a(this.m);
        if (bVarA == null) {
            GDTLogger.d("Load Local DEV Cloud setting fail");
        } else {
            this.c = bVarA.a();
            this.b = bVarA.b();
        }
    }

    private void b() {
        f.b<d> bVarB = f.b(this.m);
        if (bVarB == null) {
            GDTLogger.d("Load Local DEV Cloud setting fail");
        } else {
            this.i = bVarB.a();
            this.h = bVarB.b();
        }
    }

    private void c() {
        f.b<e> bVarC = f.c(this.m);
        if (bVarC == null) {
            GDTLogger.d("Load Local SDK Cloud setting fail");
        } else {
            this.e = bVarC.b();
            this.f = bVarC.a();
        }
    }

    private void d() {
        try {
            this.k = StringUtil.readAll(new File(this.m.getDir(this.n, 0), "gdt_suid"));
        } catch (Throwable unused) {
            this.k = null;
            GDTLogger.d("IO Exception while loading suid");
        }
    }

    public Object get(String str) {
        Object objOpt;
        Object objOpt2;
        Object objOpt3;
        Object objOpt4;
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            if (this.a != null && (objOpt4 = this.a.a.opt(str)) != null) {
                return objOpt4;
            }
            if (this.c != null && (objOpt3 = this.c.a.opt(str)) != null) {
                return objOpt3;
            }
            if (this.d != null && (objOpt2 = this.d.a.opt(str)) != null) {
                return objOpt2;
            }
            if (this.f != null && (objOpt = this.f.a.opt(str)) != null) {
                return objOpt;
            }
            if (this.g != null) {
                return this.g.a.opt(str);
            }
            return null;
        } catch (Throwable unused) {
            GDTLogger.d("Exception in settingManager.get Setting for key");
            return null;
        }
    }

    public String getDevCloudSettingSig() {
        return this.b;
    }

    public Object getDisplaySetting(String str) {
        return this.j.a.opt(str);
    }

    public Object getForPlacement(String str, String str2) {
        Object objA;
        Object objA2;
        Object objA3;
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        if (StringUtil.isEmpty(str2)) {
            return get(str);
        }
        try {
            JSONObject jSONObjectOptJSONObject = this.j.a.optJSONObject(str2);
            Object objOpt = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.opt(str) : null;
            if (objOpt != null) {
                return objOpt;
            }
            if (this.i != null) {
                JSONObject jSONObjectOptJSONObject2 = this.i.a.optJSONObject(str2);
                Object objOpt2 = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.opt(str) : null;
                if (objOpt2 != null) {
                    return objOpt2;
                }
            }
            return (this.a == null || (objA3 = this.a.a(str, str2)) == null) ? (this.c == null || (objA2 = this.c.a(str, str2)) == null) ? (this.d == null || (objA = this.d.a(str, str2)) == null) ? get(str) : objA : objA2 : objA3;
        } catch (Throwable unused) {
            GDTLogger.d("Exception in settingManager.getForPlacement");
            return null;
        }
    }

    @Deprecated
    public int getInteger(String str, int i) {
        Object obj = get(str);
        return (obj == null || !(obj instanceof Integer)) ? i : ((Integer) obj).intValue();
    }

    public int getIntegerForPlacement(String str, String str2, int i) {
        Object forPlacement = getForPlacement(str, str2);
        return (forPlacement == null || !(forPlacement instanceof Integer)) ? i : ((Integer) forPlacement).intValue();
    }

    public String getPlacementCloudSettingSig() {
        return this.h;
    }

    public String getSdkCloudSettingSig() {
        return this.e;
    }

    public String getSettingDir() {
        return this.n;
    }

    public Set<String> getSettingKeySet(String str) {
        JSONObject jSONObject;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.j);
        arrayList2.add(this.i);
        arrayList2.add(this.a);
        arrayList2.add(this.c);
        arrayList2.add(this.d);
        arrayList2.add(this.f);
        arrayList2.add(this.g);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null && (jSONObject = bVar.a) != null) {
                arrayList.add(jSONObject);
                arrayList.add(bVar.a.optJSONObject(str));
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            JSONObject jSONObject2 = (JSONObject) it2.next();
            if (jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    hashSet.add(itKeys.next());
                }
            }
        }
        return hashSet;
    }

    public String getSid() {
        return this.l;
    }

    @Deprecated
    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Deprecated
    public String getString(String str, String str2) {
        Object obj = get(str);
        return obj == null ? str2 : obj.toString();
    }

    public String getStringForPlacement(String str, String str2) {
        return getStringForPlacement(str, str2, null);
    }

    public String getStringForPlacement(String str, String str2, String str3) {
        Object forPlacement = getForPlacement(str, str2);
        return forPlacement == null ? str3 : forPlacement.toString();
    }

    public String getSuid() {
        return this.k;
    }

    public void setDEVCodeSetting(String str, Object obj) throws JSONException {
        a aVar = this.d;
        if (aVar == null) {
            throw null;
        }
        try {
            aVar.a.putOpt(str, obj);
        } catch (JSONException unused) {
            GDTLogger.d("Exception while update setting");
        }
    }

    public void updateContextSetting(String str) {
        try {
            a aVar = new a();
            if (!TextUtils.isEmpty(str)) {
                aVar = new a(new String(Base64.decode(str, 0), "UTF-8"));
            }
            this.a = aVar;
        } catch (Throwable unused) {
            GDTLogger.d("Exception while update Context Setting");
        }
    }

    public void updateDEVCloudSetting(String str, String str2) {
        if (f.a(this.m, str, str2)) {
            a();
        }
    }

    public void updateDisplaySetting(String str, Object obj) throws JSONException {
        d dVar = this.j;
        if (dVar == null) {
            throw null;
        }
        try {
            dVar.a.putOpt(str, obj);
        } catch (JSONException e) {
            GDTLogger.d(e.getMessage());
        }
    }

    public void updatePlacementSetting(String str, String str2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            if (f.b(this.m, str, str2)) {
                b();
                return;
            }
            return;
        }
        File dir = this.m.getDir(SystemUtil.buildNewPathByProcessName("e_qq_com_setting"), 0);
        if (dir.exists()) {
            File file = new File(dir, "placementCloudSetting.cfg");
            if (file.exists()) {
                file.delete();
            }
            File file2 = new File(dir, "placementCloudSetting.sig");
            if (file2.exists()) {
                file2.delete();
            }
        }
        this.i = new d();
        this.h = null;
    }

    public void updateSDKCloudSetting(String str, String str2) {
        if (f.c(this.m, str, str2)) {
            c();
        }
    }

    public void updateSID(String str) {
        this.l = str;
    }

    public void updateSUID(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.k)) {
            return;
        }
        this.k = str;
        try {
            StringUtil.writeTo(str, new File(this.m.getDir(this.n, 0), "gdt_suid"));
        } catch (IOException unused) {
            GDTLogger.d("Exception while persit suid");
        }
    }

    public void setDEVCodeSetting(String str, Object obj, String str2) throws JSONException {
        a aVar = this.d;
        if (aVar == null) {
            throw null;
        }
        try {
            JSONObject jSONObjectOptJSONObject = aVar.a.optJSONObject(Constants.KEYS.PLACEMENTS);
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
                aVar.a.putOpt(Constants.KEYS.PLACEMENTS, jSONObjectOptJSONObject);
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(str2);
            if (jSONObjectOptJSONObject2 == null) {
                jSONObjectOptJSONObject2 = new JSONObject();
                jSONObjectOptJSONObject.putOpt(str2, jSONObjectOptJSONObject2);
            }
            if (obj == null) {
                jSONObjectOptJSONObject2.remove(str);
            } else {
                jSONObjectOptJSONObject2.putOpt(str, obj);
            }
        } catch (JSONException unused) {
            GDTLogger.d("Exception while update setting");
        }
    }
}
