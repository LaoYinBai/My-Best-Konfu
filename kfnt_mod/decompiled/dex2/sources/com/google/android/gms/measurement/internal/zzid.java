package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zznv;
import com.google.android.gms.internal.measurement.zzoe;
import com.google.android.gms.internal.measurement.zzoz;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.interstitial.view.MBInterstitialActivity;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzid extends zzf {

    @VisibleForTesting
    protected zzic zza;
    final zzs zzb;

    @VisibleForTesting
    protected boolean zzc;
    private zzgy zzd;
    private final Set zze;
    private boolean zzf;
    private final AtomicReference zzg;
    private final Object zzh;

    @GuardedBy("consentLock")
    private zzai zzi;

    @GuardedBy("consentLock")
    private int zzj;
    private final AtomicLong zzk;
    private long zzl;
    private int zzm;
    private final zzlg zzn;

    protected zzid(zzfy zzfyVar) {
        super(zzfyVar);
        this.zze = new CopyOnWriteArraySet();
        this.zzh = new Object();
        this.zzc = true;
        this.zzn = new zzhr(this);
        this.zzg = new AtomicReference();
        this.zzi = new zzai(null, null);
        this.zzj = 100;
        this.zzl = -1L;
        this.zzm = 100;
        this.zzk = new AtomicLong(0L);
        this.zzb = new zzs(zzfyVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzaa(Boolean bool, boolean z) throws IllegalStateException {
        zzg();
        zza();
        this.zzs.zzay().zzc().zzb("Setting app measurement enabled (FE)", bool);
        this.zzs.zzm().zzh(bool);
        if (z) {
            zzfd zzfdVarZzm = this.zzs.zzm();
            zzfy zzfyVar = zzfdVarZzm.zzs;
            zzfdVarZzm.zzg();
            SharedPreferences.Editor editorEdit = zzfdVarZzm.zza().edit();
            if (bool != null) {
                editorEdit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                editorEdit.remove("measurement_enabled_from_api");
            }
            editorEdit.apply();
        }
        if (this.zzs.zzK() || !(bool == null || bool.booleanValue())) {
            zzab();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzab() throws IllegalStateException {
        zzg();
        String strZza = this.zzs.zzm().zzh.zza();
        if (strZza != null) {
            if ("unset".equals(strZza)) {
                zzY(PushConstants.EXTRA_APPLICATION_PENDING_INTENT, "_npa", null, this.zzs.zzav().currentTimeMillis());
            } else {
                zzY(PushConstants.EXTRA_APPLICATION_PENDING_INTENT, "_npa", Long.valueOf(true != "true".equals(strZza) ? 0L : 1L), this.zzs.zzav().currentTimeMillis());
            }
        }
        if (!this.zzs.zzJ() || !this.zzc) {
            this.zzs.zzay().zzc().zza("Updating Scion state (FE)");
            this.zzs.zzt().zzI();
            return;
        }
        this.zzs.zzay().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzz();
        zzoe.zzc();
        if (this.zzs.zzf().zzs(null, zzeb.zzad)) {
            this.zzs.zzu().zza.zza();
        }
        this.zzs.zzaz().zzp(new zzhg(this));
    }

    static /* bridge */ /* synthetic */ void zzv(zzid zzidVar, zzai zzaiVar, zzai zzaiVar2) {
        boolean z;
        zzah[] zzahVarArr = {zzah.ANALYTICS_STORAGE, zzah.AD_STORAGE};
        int i = 0;
        while (true) {
            if (i >= 2) {
                z = false;
                break;
            }
            zzah zzahVar = zzahVarArr[i];
            if (!zzaiVar2.zzi(zzahVar) && zzaiVar.zzi(zzahVar)) {
                z = true;
                break;
            }
            i++;
        }
        boolean zZzl = zzaiVar.zzl(zzaiVar2, zzah.ANALYTICS_STORAGE, zzah.AD_STORAGE);
        if (z || zZzl) {
            zzidVar.zzs.zzh().zzo();
        }
    }

    static /* synthetic */ void zzw(zzid zzidVar, zzai zzaiVar, int i, long j, boolean z, boolean z2) {
        zzidVar.zzg();
        zzidVar.zza();
        if (j <= zzidVar.zzl && zzai.zzj(zzidVar.zzm, i)) {
            zzidVar.zzs.zzay().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzaiVar);
            return;
        }
        zzfd zzfdVarZzm = zzidVar.zzs.zzm();
        zzfy zzfyVar = zzfdVarZzm.zzs;
        zzfdVarZzm.zzg();
        if (!zzfdVarZzm.zzl(i)) {
            zzidVar.zzs.zzay().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(i));
            return;
        }
        SharedPreferences.Editor editorEdit = zzfdVarZzm.zza().edit();
        editorEdit.putString("consent_settings", zzaiVar.zzh());
        editorEdit.putInt("consent_source", i);
        editorEdit.apply();
        zzidVar.zzl = j;
        zzidVar.zzm = i;
        zzidVar.zzs.zzt().zzF(z);
        if (z2) {
            zzidVar.zzs.zzt().zzu(new AtomicReference());
        }
    }

    public final void zzA(String str, String str2, Bundle bundle) throws IllegalStateException {
        long jCurrentTimeMillis = this.zzs.zzav().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, jCurrentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        this.zzs.zzaz().zzp(new zzhn(this, bundle2));
    }

    public final void zzB() {
        if (!(this.zzs.zzau().getApplicationContext() instanceof Application) || this.zza == null) {
            return;
        }
        ((Application) this.zzs.zzau().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
    }

    final /* synthetic */ void zzC(Bundle bundle) {
        if (bundle == null) {
            this.zzs.zzm().zzr.zzb(new Bundle());
            return;
        }
        Bundle bundleZza = this.zzs.zzm().zzr.zza();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                if (this.zzs.zzv().zzaf(obj)) {
                    this.zzs.zzv().zzN(this.zzn, null, 27, null, null, 0);
                }
                this.zzs.zzay().zzl().zzc("Invalid default event parameter type. Name, value", str, obj);
            } else if (zzlh.zzah(str)) {
                this.zzs.zzay().zzl().zzb("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                bundleZza.remove(str);
            } else {
                zzlh zzlhVarZzv = this.zzs.zzv();
                this.zzs.zzf();
                if (zzlhVarZzv.zzaa(RemoteMessageConst.MessageBody.PARAM, str, 100, obj)) {
                    this.zzs.zzv().zzO(bundleZza, str, obj);
                }
            }
        }
        this.zzs.zzv();
        int iZzc = this.zzs.zzf().zzc();
        if (bundleZza.size() > iZzc) {
            int i = 0;
            for (String str2 : new TreeSet(bundleZza.keySet())) {
                i++;
                if (i > iZzc) {
                    bundleZza.remove(str2);
                }
            }
            this.zzs.zzv().zzN(this.zzn, null, 26, null, null, 0);
            this.zzs.zzay().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        this.zzs.zzm().zzr.zzb(bundleZza);
        this.zzs.zzt().zzH(bundleZza);
    }

    public final void zzD(String str, String str2, Bundle bundle) throws IllegalStateException {
        zzE(str, str2, bundle, true, true, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzE(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws IllegalStateException {
        String str3 = str == null ? PushConstants.EXTRA_APPLICATION_PENDING_INTENT : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (str2 == FirebaseAnalytics.Event.SCREEN_VIEW || (str2 != null && str2.equals(FirebaseAnalytics.Event.SCREEN_VIEW))) {
            this.zzs.zzs().zzx(bundle2, j);
        } else {
            zzM(str3, str2, j, bundle2, z2, !z2 || this.zzd == null || zzlh.zzah(str2), z, null);
        }
    }

    public final void zzF(String str, String str2, Bundle bundle, String str3) throws IllegalStateException {
        zzfy.zzO();
        zzM("auto", str2, this.zzs.zzav().currentTimeMillis(), bundle, false, true, true, str3);
    }

    @WorkerThread
    final void zzG(String str, String str2, Bundle bundle) {
        zzg();
        zzH(str, str2, this.zzs.zzav().currentTimeMillis(), bundle);
    }

    @WorkerThread
    final void zzH(String str, String str2, long j, Bundle bundle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        zzg();
        zzI(str, str2, j, bundle, true, this.zzd == null || zzlh.zzah(str2), true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v6 */
    @WorkerThread
    protected final void zzI(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int i;
        boolean z4;
        boolean z5;
        ArrayList arrayList;
        long j2;
        Bundle[] bundleArr;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzg();
        zza();
        if (!this.zzs.zzJ()) {
            this.zzs.zzay().zzc().zza("Event not sent since app measurement is disabled");
            return;
        }
        List listZzn = this.zzs.zzh().zzn();
        if (listZzn != null && !listZzn.contains(str2)) {
            this.zzs.zzay().zzc().zzc("Dropping non-safelisted event. event name, origin", str2, str);
            return;
        }
        if (!this.zzf) {
            this.zzf = true;
            try {
                try {
                    (!this.zzs.zzN() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.zzs.zzau().getClassLoader()) : Class.forName("com.google.android.gms.tagmanager.TagManagerService")).getDeclaredMethod("initialize", Context.class).invoke(null, this.zzs.zzau());
                } catch (Exception e) {
                    this.zzs.zzay().zzk().zzb("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException unused) {
                this.zzs.zzay().zzi().zza("Tag Manager is not found and thus will not be used");
            }
        }
        if ("_cmp".equals(str2) && bundle.containsKey("gclid")) {
            this.zzs.zzaw();
            i = 1;
            zzY("auto", "_lgclid", bundle.getString("gclid"), this.zzs.zzav().currentTimeMillis());
        } else {
            i = 1;
        }
        this.zzs.zzaw();
        if (z && zzlh.zzal(str2)) {
            this.zzs.zzv().zzL(bundle, this.zzs.zzm().zzr.zza());
        }
        if (!z3) {
            this.zzs.zzaw();
            if (!"_iap".equals(str2)) {
                zzlh zzlhVarZzv = this.zzs.zzv();
                int i2 = 2;
                if (zzlhVarZzv.zzac("event", str2)) {
                    if (zzlhVarZzv.zzZ("event", zzgv.zza, zzgv.zzb, str2)) {
                        zzlhVarZzv.zzs.zzf();
                        if (zzlhVarZzv.zzY("event", 40, str2)) {
                            i2 = 0;
                        }
                    } else {
                        i2 = 13;
                    }
                }
                if (i2 != 0) {
                    this.zzs.zzay().zze().zzb("Invalid public event name. Event will not be logged (FE)", this.zzs.zzj().zzd(str2));
                    zzlh zzlhVarZzv2 = this.zzs.zzv();
                    this.zzs.zzf();
                    this.zzs.zzv().zzN(this.zzn, null, i2, "_ev", zzlhVarZzv2.zzD(str2, 40, i), str2 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        this.zzs.zzaw();
        zzik zzikVarZzj = this.zzs.zzs().zzj(false);
        if (zzikVarZzj != null && !bundle.containsKey("_sc")) {
            zzikVarZzj.zzd = i;
        }
        zzlh.zzK(zzikVarZzj, bundle, z && !z3);
        boolean zEquals = "am".equals(str);
        boolean zZzah = zzlh.zzah(str2);
        if (!z || this.zzd == null || zZzah) {
            z4 = zEquals;
        } else {
            if (!zEquals) {
                this.zzs.zzay().zzc().zzc("Passing event to registered event handler (FE)", this.zzs.zzj().zzd(str2), this.zzs.zzj().zzb(bundle));
                Preconditions.checkNotNull(this.zzd);
                this.zzd.interceptEvent(str, str2, bundle, j);
                return;
            }
            z4 = true;
        }
        if (this.zzs.zzM()) {
            int iZzh = this.zzs.zzv().zzh(str2);
            if (iZzh != 0) {
                this.zzs.zzay().zze().zzb("Invalid event name. Event will not be logged (FE)", this.zzs.zzj().zzd(str2));
                zzlh zzlhVarZzv3 = this.zzs.zzv();
                this.zzs.zzf();
                this.zzs.zzv().zzN(this.zzn, str3, iZzh, "_ev", zzlhVarZzv3.zzD(str2, 40, i), str2 != null ? str2.length() : 0);
                return;
            }
            Bundle bundleZzy = this.zzs.zzv().zzy(str3, str2, bundle, CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
            Preconditions.checkNotNull(bundleZzy);
            this.zzs.zzaw();
            if (this.zzs.zzs().zzj(false) == null || !"_ae".equals(str2)) {
                z5 = z4;
            } else {
                zzkg zzkgVar = this.zzs.zzu().zzb;
                long jElapsedRealtime = zzkgVar.zzc.zzs.zzav().elapsedRealtime();
                z5 = z4;
                long j3 = jElapsedRealtime - zzkgVar.zzb;
                zzkgVar.zzb = jElapsedRealtime;
                if (j3 > 0) {
                    this.zzs.zzv().zzI(bundleZzy, j3);
                }
            }
            zznv.zzc();
            if (this.zzs.zzf().zzs(null, zzeb.zzac)) {
                if (!"auto".equals(str) && "_ssr".equals(str2)) {
                    zzlh zzlhVarZzv4 = this.zzs.zzv();
                    String string = bundleZzy.getString("_ffr");
                    if (Strings.isEmptyOrWhitespace(string)) {
                        string = null;
                    } else if (string != null) {
                        string = string.trim();
                    }
                    if (zzlf.zza(string, zzlhVarZzv4.zzs.zzm().zzo.zza())) {
                        zzlhVarZzv4.zzs.zzay().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        return;
                    }
                    zzlhVarZzv4.zzs.zzm().zzo.zzb(string);
                } else if ("_ae".equals(str2)) {
                    String strZza = this.zzs.zzv().zzs.zzm().zzo.zza();
                    if (!TextUtils.isEmpty(strZza)) {
                        bundleZzy.putString("_ffr", strZza);
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(bundleZzy);
            if (this.zzs.zzm().zzj.zza() > 0 && this.zzs.zzm().zzk(j) && this.zzs.zzm().zzl.zzb()) {
                this.zzs.zzay().zzj().zza("Current session is expired, remove the session number, ID, and engagement time");
                arrayList = arrayList2;
                j2 = 0;
                zzY("auto", "_sid", null, this.zzs.zzav().currentTimeMillis());
                zzY("auto", "_sno", null, this.zzs.zzav().currentTimeMillis());
                zzY("auto", "_se", null, this.zzs.zzav().currentTimeMillis());
            } else {
                arrayList = arrayList2;
                j2 = 0;
            }
            if (bundleZzy.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j2) == 1) {
                this.zzs.zzay().zzj().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                this.zzs.zzu().zza.zzb(j, i);
            }
            ArrayList arrayList3 = new ArrayList(bundleZzy.keySet());
            Collections.sort(arrayList3);
            int size = arrayList3.size();
            for (int i3 = 0; i3 < size; i3++) {
                String str4 = (String) arrayList3.get(i3);
                if (str4 != null) {
                    this.zzs.zzv();
                    Object obj = bundleZzy.get(str4);
                    if (obj instanceof Bundle) {
                        Bundle[] bundleArr2 = new Bundle[i];
                        bundleArr2[0] = (Bundle) obj;
                        bundleArr = bundleArr2;
                    } else if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        bundleArr = (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList4 = (ArrayList) obj;
                        bundleArr = (Bundle[]) arrayList4.toArray(new Bundle[arrayList4.size()]);
                    } else {
                        bundleArr = null;
                    }
                    if (bundleArr != null) {
                        bundleZzy.putParcelableArray(str4, bundleArr);
                    }
                }
            }
            int i4 = 0;
            while (i4 < arrayList.size()) {
                ArrayList arrayList5 = arrayList;
                Bundle bundleZzt = (Bundle) arrayList5.get(i4);
                String str5 = i4 != 0 ? "_ep" : str2;
                bundleZzt.putString("_o", str);
                if (z2) {
                    bundleZzt = this.zzs.zzv().zzt(bundleZzt);
                }
                Bundle bundle2 = bundleZzt;
                this.zzs.zzt().zzA(new zzaw(str5, new zzau(bundle2), str, j), str3);
                if (!z5) {
                    Iterator it = this.zze.iterator();
                    while (it.hasNext()) {
                        ((zzgz) it.next()).onEvent(str, str2, new Bundle(bundle2), j);
                    }
                }
                i4++;
                arrayList = arrayList5;
            }
            this.zzs.zzaw();
            if (this.zzs.zzs().zzj(false) == null || !"_ae".equals(str2)) {
                return;
            }
            this.zzs.zzu().zzb.zzd(true, true, this.zzs.zzav().elapsedRealtime());
        }
    }

    public final void zzJ(zzgz zzgzVar) {
        zza();
        Preconditions.checkNotNull(zzgzVar);
        if (this.zze.add(zzgzVar)) {
            return;
        }
        this.zzs.zzay().zzk().zza("OnEventListener already registered");
    }

    public final void zzK(long j) throws IllegalStateException {
        this.zzg.set(null);
        this.zzs.zzaz().zzp(new zzhl(this, j));
    }

    final void zzL(long j, boolean z) {
        zzg();
        zza();
        this.zzs.zzay().zzc().zza("Resetting analytics data (FE)");
        zzki zzkiVarZzu = this.zzs.zzu();
        zzkiVarZzu.zzg();
        zzkh zzkhVar = zzkiVarZzu.zza;
        zzkiVarZzu.zzb.zza();
        zzoz.zzc();
        if (this.zzs.zzf().zzs(null, zzeb.zzas)) {
            this.zzs.zzh().zzo();
        }
        boolean zZzJ = this.zzs.zzJ();
        zzfd zzfdVarZzm = this.zzs.zzm();
        zzfdVarZzm.zzc.zzb(j);
        if (!TextUtils.isEmpty(zzfdVarZzm.zzs.zzm().zzo.zza())) {
            zzfdVarZzm.zzo.zzb(null);
        }
        zzoe.zzc();
        if (zzfdVarZzm.zzs.zzf().zzs(null, zzeb.zzad)) {
            zzfdVarZzm.zzj.zzb(0L);
        }
        if (!zzfdVarZzm.zzs.zzf().zzv()) {
            zzfdVarZzm.zzi(!zZzJ);
        }
        zzfdVarZzm.zzp.zzb(null);
        zzfdVarZzm.zzq.zzb(0L);
        zzfdVarZzm.zzr.zzb(null);
        if (z) {
            this.zzs.zzt().zzC();
        }
        zzoe.zzc();
        if (this.zzs.zzf().zzs(null, zzeb.zzad)) {
            this.zzs.zzu().zza.zza();
        }
        this.zzc = !zZzJ;
    }

    protected final void zzM(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) throws IllegalStateException {
        Bundle bundle2 = new Bundle(bundle);
        for (String str4 : bundle2.keySet()) {
            Object obj = bundle2.get(str4);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str4, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelable);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        this.zzs.zzaz().zzp(new zzhi(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    final void zzN(String str, String str2, long j, Object obj) throws IllegalStateException {
        this.zzs.zzaz().zzp(new zzhj(this, str, str2, obj, j));
    }

    final void zzO(String str) {
        this.zzg.set(str);
    }

    public final void zzP(Bundle bundle) throws IllegalStateException {
        zzQ(bundle, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzQ(Bundle bundle, long j) throws IllegalStateException {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.zzs.zzay().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzgu.zza(bundle2, "app_id", String.class, null);
        zzgu.zza(bundle2, "origin", String.class, null);
        zzgu.zza(bundle2, "name", String.class, null);
        zzgu.zza(bundle2, "value", Object.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgu.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString("name");
        Object obj = bundle2.get("value");
        if (this.zzs.zzv().zzl(string) != 0) {
            this.zzs.zzay().zzd().zzb("Invalid conditional user property name", this.zzs.zzj().zzf(string));
            return;
        }
        if (this.zzs.zzv().zzd(string, obj) != 0) {
            this.zzs.zzay().zzd().zzc("Invalid conditional user property value", this.zzs.zzj().zzf(string), obj);
            return;
        }
        Object objZzB = this.zzs.zzv().zzB(string, obj);
        if (objZzB == null) {
            this.zzs.zzay().zzd().zzc("Unable to normalize conditional user property value", this.zzs.zzj().zzf(string), obj);
            return;
        }
        zzgu.zzb(bundle2, objZzB);
        long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
        if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
            this.zzs.zzf();
            if (j2 > 15552000000L || j2 < 1) {
                this.zzs.zzay().zzd().zzc("Invalid conditional user property timeout", this.zzs.zzj().zzf(string), Long.valueOf(j2));
                return;
            }
        }
        long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
        this.zzs.zzf();
        if (j3 > 15552000000L || j3 < 1) {
            this.zzs.zzay().zzd().zzc("Invalid conditional user property time to live", this.zzs.zzj().zzf(string), Long.valueOf(j3));
        } else {
            this.zzs.zzaz().zzp(new zzhm(this, bundle2));
        }
    }

    public final void zzR(Bundle bundle, int i, long j) {
        zza();
        String strZzg = zzai.zzg(bundle);
        if (strZzg != null) {
            this.zzs.zzay().zzl().zzb("Ignoring invalid consent setting", strZzg);
            this.zzs.zzay().zzl().zza("Valid consent values are 'granted', 'denied'");
        }
        zzS(zzai.zza(bundle), i, j);
    }

    public final void zzS(zzai zzaiVar, int i, long j) {
        zzai zzaiVar2;
        boolean z;
        boolean zZzk;
        boolean z2;
        zzai zzaiVarZzd = zzaiVar;
        zza();
        if (i != -10 && zzaiVar.zze() == null && zzaiVar.zzf() == null) {
            this.zzs.zzay().zzl().zza("Discarding empty consent settings");
            return;
        }
        synchronized (this.zzh) {
            zzaiVar2 = this.zzi;
            z = true;
            boolean z3 = false;
            if (zzai.zzj(i, this.zzj)) {
                zZzk = zzaiVarZzd.zzk(this.zzi);
                if (zzaiVarZzd.zzi(zzah.ANALYTICS_STORAGE) && !this.zzi.zzi(zzah.ANALYTICS_STORAGE)) {
                    z3 = true;
                }
                zzaiVarZzd = zzaiVarZzd.zzd(this.zzi);
                this.zzi = zzaiVarZzd;
                this.zzj = i;
                z2 = z3;
            } else {
                zZzk = false;
                z = false;
                z2 = false;
            }
        }
        if (!z) {
            this.zzs.zzay().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzaiVarZzd);
            return;
        }
        long andIncrement = this.zzk.getAndIncrement();
        if (zZzk) {
            this.zzg.set(null);
            this.zzs.zzaz().zzq(new zzhx(this, zzaiVarZzd, j, i, andIncrement, z2, zzaiVar2));
            return;
        }
        zzhy zzhyVar = new zzhy(this, zzaiVarZzd, i, andIncrement, z2, zzaiVar2);
        if (i == 30 || i == -10) {
            this.zzs.zzaz().zzq(zzhyVar);
        } else {
            this.zzs.zzaz().zzp(zzhyVar);
        }
    }

    @WorkerThread
    public final void zzT(zzgy zzgyVar) {
        zzgy zzgyVar2;
        zzg();
        zza();
        if (zzgyVar != null && zzgyVar != (zzgyVar2 = this.zzd)) {
            Preconditions.checkState(zzgyVar2 == null, "EventInterceptor already set.");
        }
        this.zzd = zzgyVar;
    }

    public final void zzU(Boolean bool) throws IllegalStateException {
        zza();
        this.zzs.zzaz().zzp(new zzhw(this, bool));
    }

    @WorkerThread
    final void zzV(zzai zzaiVar) {
        zzg();
        boolean z = (zzaiVar.zzi(zzah.ANALYTICS_STORAGE) && zzaiVar.zzi(zzah.AD_STORAGE)) || this.zzs.zzt().zzM();
        if (z != this.zzs.zzK()) {
            this.zzs.zzG(z);
            zzfd zzfdVarZzm = this.zzs.zzm();
            zzfy zzfyVar = zzfdVarZzm.zzs;
            zzfdVarZzm.zzg();
            Boolean boolValueOf = zzfdVarZzm.zza().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzfdVarZzm.zza().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || boolValueOf == null || boolValueOf.booleanValue()) {
                zzaa(Boolean.valueOf(z), false);
            }
        }
    }

    public final void zzW(String str, String str2, Object obj, boolean z) throws IllegalStateException {
        zzX("auto", "_ldl", obj, true, this.zzs.zzav().currentTimeMillis());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.Long] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzY(java.lang.String r7, java.lang.String r8, java.lang.Object r9, long r10) {
        /*
            r6 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            r6.zzg()
            r6.zza()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L66
            boolean r0 = r9 instanceof java.lang.String
            if (r0 == 0) goto L55
            r0 = r9
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L55
            r8 = 1
            java.lang.String r9 = "false"
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r1)
            boolean r9 = r9.equals(r0)
            r0 = 1
            if (r8 == r9) goto L35
            r8 = 0
            goto L36
        L35:
            r8 = r0
        L36:
            java.lang.Long r9 = java.lang.Long.valueOf(r8)
            com.google.android.gms.measurement.internal.zzfy r8 = r6.zzs
            com.google.android.gms.measurement.internal.zzfd r8 = r8.zzm()
            com.google.android.gms.measurement.internal.zzfc r8 = r8.zzh
            long r2 = r9.longValue()
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 != 0) goto L4d
            java.lang.String r0 = "true"
            goto L4f
        L4d:
            java.lang.String r0 = "false"
        L4f:
            r8.zzb(r0)
            java.lang.String r8 = "_npa"
            goto L66
        L55:
            if (r9 != 0) goto L66
            com.google.android.gms.measurement.internal.zzfy r8 = r6.zzs
            com.google.android.gms.measurement.internal.zzfd r8 = r8.zzm()
            com.google.android.gms.measurement.internal.zzfc r8 = r8.zzh
            java.lang.String r0 = "unset"
            r8.zzb(r0)
            java.lang.String r8 = "_npa"
        L66:
            r1 = r8
            r4 = r9
            com.google.android.gms.measurement.internal.zzfy r8 = r6.zzs
            boolean r8 = r8.zzJ()
            if (r8 != 0) goto L80
            com.google.android.gms.measurement.internal.zzfy r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzem r7 = r7.zzj()
            java.lang.String r8 = "User property not set since app measurement is disabled"
            r7.zza(r8)
            return
        L80:
            com.google.android.gms.measurement.internal.zzfy r8 = r6.zzs
            boolean r8 = r8.zzM()
            if (r8 != 0) goto L89
            return
        L89:
            com.google.android.gms.measurement.internal.zzlc r8 = new com.google.android.gms.measurement.internal.zzlc
            r0 = r8
            r2 = r10
            r5 = r7
            r0.<init>(r1, r2, r4, r5)
            com.google.android.gms.measurement.internal.zzfy r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzjs r7 = r7.zzt()
            r7.zzK(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzid.zzY(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final void zzZ(zzgz zzgzVar) {
        zza();
        Preconditions.checkNotNull(zzgzVar);
        if (this.zze.remove(zzgzVar)) {
            return;
        }
        this.zzs.zzay().zzk().zza("OnEventListener had not been registered");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    public final int zzh(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzs.zzf();
        return 25;
    }

    public final Boolean zzi() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.zzs.zzaz().zzd(atomicReference, MBInterstitialActivity.WEB_LOAD_TIME, "boolean test flag value", new zzhp(this, atomicReference));
    }

    public final Double zzj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.zzs.zzaz().zzd(atomicReference, MBInterstitialActivity.WEB_LOAD_TIME, "double test flag value", new zzhv(this, atomicReference));
    }

    public final Integer zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.zzs.zzaz().zzd(atomicReference, MBInterstitialActivity.WEB_LOAD_TIME, "int test flag value", new zzhu(this, atomicReference));
    }

    public final Long zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.zzs.zzaz().zzd(atomicReference, MBInterstitialActivity.WEB_LOAD_TIME, "long test flag value", new zzht(this, atomicReference));
    }

    public final String zzo() {
        return (String) this.zzg.get();
    }

    public final String zzp() {
        zzik zzikVarZzi = this.zzs.zzs().zzi();
        if (zzikVarZzi != null) {
            return zzikVarZzi.zzb;
        }
        return null;
    }

    public final String zzq() {
        zzik zzikVarZzi = this.zzs.zzs().zzi();
        if (zzikVarZzi != null) {
            return zzikVarZzi.zza;
        }
        return null;
    }

    public final String zzr() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.zzs.zzaz().zzd(atomicReference, MBInterstitialActivity.WEB_LOAD_TIME, "String test flag value", new zzhs(this, atomicReference));
    }

    public final ArrayList zzs(String str, String str2) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        this.zzs.zzaw();
        if (zzab.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().zzd(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get conditional user properties", new zzho(this, atomicReference, null, str, str2));
        List list = (List) atomicReference.get();
        if (list != null) {
            return zzlh.zzH(list);
        }
        this.zzs.zzay().zzd().zzb("Timed out waiting for get conditional user properties", null);
        return new ArrayList();
    }

    public final List zzt(boolean z) {
        zza();
        this.zzs.zzay().zzj().zza("Getting user properties (FE)");
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        this.zzs.zzaw();
        if (zzab.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().zzd(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get user properties", new zzhk(this, atomicReference, z));
        List list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        this.zzs.zzay().zzd().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
        return Collections.emptyList();
    }

    public final Map zzu(String str, String str2, boolean z) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        this.zzs.zzaw();
        if (zzab.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().zzd(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get user properties", new zzhq(this, atomicReference, null, str, str2, z));
        List<zzlc> list = (List) atomicReference.get();
        if (list == null) {
            this.zzs.zzay().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzlc zzlcVar : list) {
            Object objZza = zzlcVar.zza();
            if (objZza != null) {
                arrayMap.put(zzlcVar.zzb, objZza);
            }
        }
        return arrayMap;
    }

    @WorkerThread
    public final void zzz() {
        zzg();
        zza();
        if (this.zzs.zzM()) {
            if (this.zzs.zzf().zzs(null, zzeb.zzX)) {
                zzag zzagVarZzf = this.zzs.zzf();
                zzagVarZzf.zzs.zzaw();
                Boolean boolZzk = zzagVarZzf.zzk("google_analytics_deferred_deep_link_enabled");
                if (boolZzk != null && boolZzk.booleanValue()) {
                    this.zzs.zzay().zzc().zza("Deferred Deep Link feature enabled.");
                    this.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhf
                        @Override // java.lang.Runnable
                        public final void run() throws IllegalStateException {
                            zzid zzidVar = this.zza;
                            zzidVar.zzg();
                            if (zzidVar.zzs.zzm().zzm.zzb()) {
                                zzidVar.zzs.zzay().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
                                return;
                            }
                            long jZza = zzidVar.zzs.zzm().zzn.zza();
                            zzidVar.zzs.zzm().zzn.zzb(1 + jZza);
                            zzidVar.zzs.zzf();
                            if (jZza < 5) {
                                zzidVar.zzs.zzE();
                            } else {
                                zzidVar.zzs.zzay().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                                zzidVar.zzs.zzm().zzm.zza(true);
                            }
                        }
                    });
                }
            }
            this.zzs.zzt().zzq();
            this.zzc = false;
            zzfd zzfdVarZzm = this.zzs.zzm();
            zzfdVarZzm.zzg();
            String string = zzfdVarZzm.zza().getString("previous_os_version", null);
            zzfdVarZzm.zzs.zzg().zzu();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor editorEdit = zzfdVarZzm.zza().edit();
                editorEdit.putString("previous_os_version", str);
                editorEdit.apply();
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.zzs.zzg().zzu();
            if (string.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", string);
            zzG("auto", "_ou", bundle);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzX(java.lang.String r16, java.lang.String r17, java.lang.Object r18, boolean r19, long r20) throws java.lang.IllegalStateException {
        /*
            r15 = this;
            r6 = r15
            r2 = r17
            r0 = r18
            if (r16 != 0) goto La
            java.lang.String r1 = "app"
            goto Lc
        La:
            r1 = r16
        Lc:
            r3 = 6
            r4 = 0
            r5 = 24
            if (r19 == 0) goto L1e
            com.google.android.gms.measurement.internal.zzfy r3 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r3 = r3.zzv()
            int r3 = r3.zzl(r2)
            r11 = r3
            goto L4d
        L1e:
            com.google.android.gms.measurement.internal.zzfy r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r7 = r7.zzv()
            java.lang.String r8 = "user property"
            boolean r8 = r7.zzac(r8, r2)
            if (r8 != 0) goto L2e
        L2c:
            r11 = 6
            goto L4d
        L2e:
            java.lang.String r8 = "user property"
            java.lang.String[] r9 = com.google.android.gms.measurement.internal.zzgx.zza
            r10 = 0
            boolean r8 = r7.zzZ(r8, r9, r10, r2)
            if (r8 != 0) goto L3e
            r3 = 15
            r11 = 15
            goto L4d
        L3e:
            com.google.android.gms.measurement.internal.zzfy r8 = r7.zzs
            r8.zzf()
            java.lang.String r8 = "user property"
            boolean r7 = r7.zzY(r8, r5, r2)
            if (r7 != 0) goto L4c
            goto L2c
        L4c:
            r11 = 0
        L4d:
            r3 = 1
            if (r11 == 0) goto L77
            com.google.android.gms.measurement.internal.zzfy r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r0 = r0.zzv()
            com.google.android.gms.measurement.internal.zzfy r1 = r6.zzs
            r1.zzf()
            java.lang.String r13 = r0.zzD(r2, r5, r3)
            if (r2 == 0) goto L67
            int r4 = r17.length()
            r14 = r4
            goto L68
        L67:
            r14 = 0
        L68:
            com.google.android.gms.measurement.internal.zzfy r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r8 = r0.zzv()
            com.google.android.gms.measurement.internal.zzlg r9 = r6.zzn
            r10 = 0
            java.lang.String r12 = "_ev"
            r8.zzN(r9, r10, r11, r12, r13, r14)
            return
        L77:
            if (r0 == 0) goto Lcc
            com.google.android.gms.measurement.internal.zzfy r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r7 = r7.zzv()
            int r11 = r7.zzd(r2, r0)
            if (r11 == 0) goto Lb7
            com.google.android.gms.measurement.internal.zzfy r1 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzfy r7 = r6.zzs
            r7.zzf()
            java.lang.String r13 = r1.zzD(r2, r5, r3)
            boolean r1 = r0 instanceof java.lang.String
            if (r1 != 0) goto L9f
            boolean r1 = r0 instanceof java.lang.CharSequence
            if (r1 == 0) goto L9d
            goto L9f
        L9d:
            r14 = 0
            goto La8
        L9f:
            java.lang.String r0 = r18.toString()
            int r4 = r0.length()
            r14 = r4
        La8:
            com.google.android.gms.measurement.internal.zzfy r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r8 = r0.zzv()
            com.google.android.gms.measurement.internal.zzlg r9 = r6.zzn
            r10 = 0
            java.lang.String r12 = "_ev"
            r8.zzN(r9, r10, r11, r12, r13, r14)
            return
        Lb7:
            com.google.android.gms.measurement.internal.zzfy r3 = r6.zzs
            com.google.android.gms.measurement.internal.zzlh r3 = r3.zzv()
            java.lang.Object r5 = r3.zzB(r2, r0)
            if (r5 == 0) goto Lcb
            r0 = r15
            r2 = r17
            r3 = r20
            r0.zzN(r1, r2, r3, r5)
        Lcb:
            return
        Lcc:
            r5 = 0
            r0 = r15
            r2 = r17
            r3 = r20
            r0.zzN(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzid.zzX(java.lang.String, java.lang.String, java.lang.Object, boolean, long):void");
    }
}
