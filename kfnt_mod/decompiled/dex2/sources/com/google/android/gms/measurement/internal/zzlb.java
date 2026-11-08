package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzlk;
import com.google.android.gms.internal.measurement.zzoz;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzlb extends zzkn {
    zzlb(zzkz zzkzVar) {
        super(zzkzVar);
    }

    @WorkerThread
    static final boolean zzA(zzaw zzawVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzawVar);
        Preconditions.checkNotNull(zzqVar);
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    static final com.google.android.gms.internal.measurement.zzfw zzB(com.google.android.gms.internal.measurement.zzfs zzfsVar, String str) {
        for (com.google.android.gms.internal.measurement.zzfw zzfwVar : zzfsVar.zzi()) {
            if (zzfwVar.zzg().equals(str)) {
                return zzfwVar;
            }
        }
        return null;
    }

    static final Object zzC(com.google.android.gms.internal.measurement.zzfs zzfsVar, String str) {
        com.google.android.gms.internal.measurement.zzfw zzfwVarZzB = zzB(zzfsVar, str);
        if (zzfwVarZzB == null) {
            return null;
        }
        if (zzfwVarZzB.zzy()) {
            return zzfwVarZzB.zzh();
        }
        if (zzfwVarZzB.zzw()) {
            return Long.valueOf(zzfwVarZzB.zzd());
        }
        if (zzfwVarZzB.zzu()) {
            return Double.valueOf(zzfwVarZzB.zza());
        }
        if (zzfwVarZzB.zzc() <= 0) {
            return null;
        }
        List<com.google.android.gms.internal.measurement.zzfw> listZzi = zzfwVarZzB.zzi();
        ArrayList arrayList = new ArrayList();
        for (com.google.android.gms.internal.measurement.zzfw zzfwVar : listZzi) {
            if (zzfwVar != null) {
                Bundle bundle = new Bundle();
                for (com.google.android.gms.internal.measurement.zzfw zzfwVar2 : zzfwVar.zzi()) {
                    if (zzfwVar2.zzy()) {
                        bundle.putString(zzfwVar2.zzg(), zzfwVar2.zzh());
                    } else if (zzfwVar2.zzw()) {
                        bundle.putLong(zzfwVar2.zzg(), zzfwVar2.zzd());
                    } else if (zzfwVar2.zzu()) {
                        bundle.putDouble(zzfwVar2.zzg(), zzfwVar2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private final void zzD(StringBuilder sb, int i, List list) {
        if (list == null) {
            return;
        }
        int i2 = i + 1;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) it.next();
            if (zzfwVar != null) {
                zzF(sb, i2);
                sb.append("param {\n");
                zzI(sb, i2, "name", zzfwVar.zzx() ? this.zzs.zzj().zze(zzfwVar.zzg()) : null);
                zzI(sb, i2, "string_value", zzfwVar.zzy() ? zzfwVar.zzh() : null);
                zzI(sb, i2, "int_value", zzfwVar.zzw() ? Long.valueOf(zzfwVar.zzd()) : null);
                zzI(sb, i2, "double_value", zzfwVar.zzu() ? Double.valueOf(zzfwVar.zza()) : null);
                if (zzfwVar.zzc() > 0) {
                    zzD(sb, i2, zzfwVar.zzi());
                }
                zzF(sb, i2);
                sb.append("}\n");
            }
        }
    }

    private final void zzE(StringBuilder sb, int i, com.google.android.gms.internal.measurement.zzel zzelVar) {
        String str;
        if (zzelVar == null) {
            return;
        }
        zzF(sb, i);
        sb.append("filter {\n");
        if (zzelVar.zzh()) {
            zzI(sb, i, "complement", Boolean.valueOf(zzelVar.zzg()));
        }
        if (zzelVar.zzj()) {
            zzI(sb, i, "param_name", this.zzs.zzj().zze(zzelVar.zze()));
        }
        if (zzelVar.zzk()) {
            int i2 = i + 1;
            com.google.android.gms.internal.measurement.zzex zzexVarZzd = zzelVar.zzd();
            if (zzexVarZzd != null) {
                zzF(sb, i2);
                sb.append("string_filter {\n");
                if (zzexVarZzd.zzi()) {
                    switch (zzexVarZzd.zzj()) {
                        case 1:
                            str = "UNKNOWN_MATCH_TYPE";
                            break;
                        case 2:
                            str = "REGEXP";
                            break;
                        case 3:
                            str = "BEGINS_WITH";
                            break;
                        case 4:
                            str = "ENDS_WITH";
                            break;
                        case 5:
                            str = "PARTIAL";
                            break;
                        case 6:
                            str = "EXACT";
                            break;
                        default:
                            str = "IN_LIST";
                            break;
                    }
                    zzI(sb, i2, "match_type", str);
                }
                if (zzexVarZzd.zzh()) {
                    zzI(sb, i2, "expression", zzexVarZzd.zzd());
                }
                if (zzexVarZzd.zzg()) {
                    zzI(sb, i2, "case_sensitive", Boolean.valueOf(zzexVarZzd.zzf()));
                }
                if (zzexVarZzd.zza() > 0) {
                    zzF(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str2 : zzexVarZzd.zze()) {
                        zzF(sb, i2 + 2);
                        sb.append(str2);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zzF(sb, i2);
                sb.append("}\n");
            }
        }
        if (zzelVar.zzi()) {
            zzJ(sb, i + 1, "number_filter", zzelVar.zzc());
        }
        zzF(sb, i);
        sb.append("}\n");
    }

    private static final void zzF(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static final String zzG(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static final void zzH(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzgh zzghVar) {
        if (zzghVar == null) {
            return;
        }
        zzF(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (zzghVar.zzb() != 0) {
            zzF(sb, 4);
            sb.append("results: ");
            int i2 = 0;
            for (Long l : zzghVar.zzk()) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    sb.append(", ");
                }
                sb.append(l);
                i2 = i3;
            }
            sb.append('\n');
        }
        if (zzghVar.zzd() != 0) {
            zzF(sb, 4);
            sb.append("status: ");
            int i4 = 0;
            for (Long l2 : zzghVar.zzn()) {
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(l2);
                i4 = i5;
            }
            sb.append('\n');
        }
        if (zzghVar.zza() != 0) {
            zzF(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i6 = 0;
            for (com.google.android.gms.internal.measurement.zzfq zzfqVar : zzghVar.zzj()) {
                int i7 = i6 + 1;
                if (i6 != 0) {
                    sb.append(", ");
                }
                sb.append(zzfqVar.zzh() ? Integer.valueOf(zzfqVar.zza()) : null);
                sb.append(":");
                sb.append(zzfqVar.zzg() ? Long.valueOf(zzfqVar.zzb()) : null);
                i6 = i7;
            }
            sb.append("}\n");
        }
        if (zzghVar.zzc() != 0) {
            zzF(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i8 = 0;
            for (com.google.android.gms.internal.measurement.zzgj zzgjVar : zzghVar.zzm()) {
                int i9 = i8 + 1;
                if (i8 != 0) {
                    sb.append(", ");
                }
                sb.append(zzgjVar.zzi() ? Integer.valueOf(zzgjVar.zzb()) : null);
                sb.append(": [");
                Iterator it = zzgjVar.zzf().iterator();
                int i10 = 0;
                while (it.hasNext()) {
                    long jLongValue = ((Long) it.next()).longValue();
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(jLongValue);
                    i10 = i11;
                }
                sb.append("]");
                i8 = i9;
            }
            sb.append("}\n");
        }
        zzF(sb, 3);
        sb.append("}\n");
    }

    private static final void zzI(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        zzF(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    private static final void zzJ(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzeq zzeqVar) {
        String str2;
        if (zzeqVar == null) {
            return;
        }
        zzF(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (zzeqVar.zzg()) {
            switch (zzeqVar.zzm()) {
                case 1:
                    str2 = "UNKNOWN_COMPARISON_TYPE";
                    break;
                case 2:
                    str2 = "LESS_THAN";
                    break;
                case 3:
                    str2 = "GREATER_THAN";
                    break;
                case 4:
                    str2 = "EQUAL";
                    break;
                default:
                    str2 = "BETWEEN";
                    break;
            }
            zzI(sb, i, "comparison_type", str2);
        }
        if (zzeqVar.zzi()) {
            zzI(sb, i, "match_as_float", Boolean.valueOf(zzeqVar.zzf()));
        }
        if (zzeqVar.zzh()) {
            zzI(sb, i, "comparison_value", zzeqVar.zzc());
        }
        if (zzeqVar.zzk()) {
            zzI(sb, i, "min_comparison_value", zzeqVar.zze());
        }
        if (zzeqVar.zzj()) {
            zzI(sb, i, "max_comparison_value", zzeqVar.zzd());
        }
        zzF(sb, i);
        sb.append("}\n");
    }

    static int zza(com.google.android.gms.internal.measurement.zzgb zzgbVar, String str) {
        if (zzgbVar != null) {
            for (int i = 0; i < zzgbVar.zzb(); i++) {
                if (str.equals(zzgbVar.zzao(i).zzf())) {
                    return i;
                }
            }
        }
        return -1;
    }

    static zzlk zzl(zzlk zzlkVar, byte[] bArr) throws com.google.android.gms.internal.measurement.zzko {
        com.google.android.gms.internal.measurement.zzjq zzjqVarZza = com.google.android.gms.internal.measurement.zzjq.zza();
        return zzjqVarZza != null ? zzlkVar.zzaA(bArr, zzjqVarZza) : zzlkVar.zzaz(bArr);
    }

    static List zzr(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    static boolean zzv(List list, int i) {
        if (i < list.size() * 64) {
            return ((1 << (i % 64)) & ((Long) list.get(i / 64)).longValue()) != 0;
        }
        return false;
    }

    static boolean zzx(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static final void zzz(com.google.android.gms.internal.measurement.zzfr zzfrVar, String str, Object obj) {
        List listZzp = zzfrVar.zzp();
        int i = 0;
        while (true) {
            if (i >= listZzp.size()) {
                i = -1;
                break;
            } else if (str.equals(((com.google.android.gms.internal.measurement.zzfw) listZzp.get(i)).zzg())) {
                break;
            } else {
                i++;
            }
        }
        com.google.android.gms.internal.measurement.zzfv zzfvVarZze = com.google.android.gms.internal.measurement.zzfw.zze();
        zzfvVarZze.zzj(str);
        if (obj instanceof Long) {
            zzfvVarZze.zzi(((Long) obj).longValue());
        }
        if (i >= 0) {
            zzfrVar.zzj(i, zzfvVarZze);
        } else {
            zzfrVar.zze(zzfvVarZze);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    protected final boolean zzb() {
        return false;
    }

    @WorkerThread
    final long zzd(byte[] bArr) throws NoSuchAlgorithmException {
        Preconditions.checkNotNull(bArr);
        this.zzs.zzv().zzg();
        MessageDigest messageDigestZzF = zzlh.zzF();
        if (messageDigestZzF != null) {
            return zzlh.zzp(messageDigestZzF.digest(bArr));
        }
        this.zzs.zzay().zzd().zza("Failed to get MD5");
        return 0L;
    }

    final Bundle zzf(Map map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    arrayList2.add(zzf((Map) arrayList.get(i), false));
                }
                bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    final Parcelable zzh(byte[] bArr, Parcelable.Creator creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return (Parcelable) creator.createFromParcel(parcelObtain);
        } catch (SafeParcelReader.ParseException unused) {
            this.zzs.zzay().zzd().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            parcelObtain.recycle();
        }
    }

    final zzaw zzi(com.google.android.gms.internal.measurement.zzaa zzaaVar) {
        Object obj;
        Bundle bundleZzf = zzf(zzaaVar.zze(), true);
        String string = PushConstants.EXTRA_APPLICATION_PENDING_INTENT;
        if (bundleZzf.containsKey("_o") && (obj = bundleZzf.get("_o")) != null) {
            string = obj.toString();
        }
        String str = string;
        String strZzb = zzgv.zzb(zzaaVar.zzd());
        if (strZzb == null) {
            strZzb = zzaaVar.zzd();
        }
        return new zzaw(strZzb, new zzau(bundleZzf), str, zzaaVar.zza());
    }

    final com.google.android.gms.internal.measurement.zzfs zzj(zzar zzarVar) {
        com.google.android.gms.internal.measurement.zzfr zzfrVarZze = com.google.android.gms.internal.measurement.zzfs.zze();
        zzfrVarZze.zzl(zzarVar.zze);
        zzat zzatVar = new zzat(zzarVar.zzf);
        while (zzatVar.hasNext()) {
            String next = zzatVar.next();
            com.google.android.gms.internal.measurement.zzfv zzfvVarZze = com.google.android.gms.internal.measurement.zzfw.zze();
            zzfvVarZze.zzj(next);
            Object objZzf = zzarVar.zzf.zzf(next);
            Preconditions.checkNotNull(objZzf);
            zzt(zzfvVarZze, objZzf);
            zzfrVarZze.zze(zzfvVarZze);
        }
        return (com.google.android.gms.internal.measurement.zzfs) zzfrVarZze.zzaE();
    }

    final String zzm(com.google.android.gms.internal.measurement.zzga zzgaVar) {
        if (zzgaVar == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (com.google.android.gms.internal.measurement.zzgc zzgcVar : zzgaVar.zzd()) {
            if (zzgcVar != null) {
                zzF(sb, 1);
                sb.append("bundle {\n");
                if (zzgcVar.zzbj()) {
                    zzI(sb, 1, "protocol_version", Integer.valueOf(zzgcVar.zzd()));
                }
                zzoz.zzc();
                if (this.zzs.zzf().zzs(null, zzeb.zzar) && zzgcVar.zzbm()) {
                    zzI(sb, 1, "session_stitching_token", zzgcVar.zzK());
                }
                zzI(sb, 1, "platform", zzgcVar.zzI());
                if (zzgcVar.zzbf()) {
                    zzI(sb, 1, "gmp_version", Long.valueOf(zzgcVar.zzm()));
                }
                if (zzgcVar.zzbq()) {
                    zzI(sb, 1, "uploading_gmp_version", Long.valueOf(zzgcVar.zzr()));
                }
                if (zzgcVar.zzbd()) {
                    zzI(sb, 1, "dynamite_version", Long.valueOf(zzgcVar.zzj()));
                }
                if (zzgcVar.zzba()) {
                    zzI(sb, 1, "config_version", Long.valueOf(zzgcVar.zzh()));
                }
                zzI(sb, 1, "gmp_app_id", zzgcVar.zzF());
                zzI(sb, 1, "admob_app_id", zzgcVar.zzw());
                zzI(sb, 1, "app_id", zzgcVar.zzx());
                zzI(sb, 1, "app_version", zzgcVar.zzA());
                if (zzgcVar.zzaY()) {
                    zzI(sb, 1, "app_version_major", Integer.valueOf(zzgcVar.zza()));
                }
                zzI(sb, 1, "firebase_instance_id", zzgcVar.zzE());
                if (zzgcVar.zzbc()) {
                    zzI(sb, 1, "dev_cert_hash", Long.valueOf(zzgcVar.zzi()));
                }
                zzI(sb, 1, "app_store", zzgcVar.zzz());
                if (zzgcVar.zzbp()) {
                    zzI(sb, 1, "upload_timestamp_millis", Long.valueOf(zzgcVar.zzq()));
                }
                if (zzgcVar.zzbn()) {
                    zzI(sb, 1, "start_timestamp_millis", Long.valueOf(zzgcVar.zzp()));
                }
                if (zzgcVar.zzbe()) {
                    zzI(sb, 1, "end_timestamp_millis", Long.valueOf(zzgcVar.zzk()));
                }
                if (zzgcVar.zzbi()) {
                    zzI(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzgcVar.zzo()));
                }
                if (zzgcVar.zzbh()) {
                    zzI(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzgcVar.zzn()));
                }
                zzI(sb, 1, "app_instance_id", zzgcVar.zzy());
                zzI(sb, 1, "resettable_device_id", zzgcVar.zzJ());
                zzI(sb, 1, "ds_id", zzgcVar.zzD());
                if (zzgcVar.zzbg()) {
                    zzI(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzgcVar.zzaW()));
                }
                zzI(sb, 1, "os_version", zzgcVar.zzH());
                zzI(sb, 1, "device_model", zzgcVar.zzC());
                zzI(sb, 1, "user_default_language", zzgcVar.zzL());
                if (zzgcVar.zzbo()) {
                    zzI(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzgcVar.zzf()));
                }
                if (zzgcVar.zzaZ()) {
                    zzI(sb, 1, "bundle_sequential_index", Integer.valueOf(zzgcVar.zzb()));
                }
                if (zzgcVar.zzbl()) {
                    zzI(sb, 1, "service_upload", Boolean.valueOf(zzgcVar.zzaX()));
                }
                zzI(sb, 1, "health_monitor", zzgcVar.zzG());
                if (zzgcVar.zzbk()) {
                    zzI(sb, 1, "retry_counter", Integer.valueOf(zzgcVar.zze()));
                }
                if (zzgcVar.zzbb()) {
                    zzI(sb, 1, "consent_signals", zzgcVar.zzB());
                }
                List<com.google.android.gms.internal.measurement.zzgl> listZzO = zzgcVar.zzO();
                if (listZzO != null) {
                    for (com.google.android.gms.internal.measurement.zzgl zzglVar : listZzO) {
                        if (zzglVar != null) {
                            zzF(sb, 2);
                            sb.append("user_property {\n");
                            zzI(sb, 2, "set_timestamp_millis", zzglVar.zzs() ? Long.valueOf(zzglVar.zzc()) : null);
                            zzI(sb, 2, "name", this.zzs.zzj().zzf(zzglVar.zzf()));
                            zzI(sb, 2, "string_value", zzglVar.zzg());
                            zzI(sb, 2, "int_value", zzglVar.zzr() ? Long.valueOf(zzglVar.zzb()) : null);
                            zzI(sb, 2, "double_value", zzglVar.zzq() ? Double.valueOf(zzglVar.zza()) : null);
                            zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzfo> listZzM = zzgcVar.zzM();
                if (listZzM != null) {
                    for (com.google.android.gms.internal.measurement.zzfo zzfoVar : listZzM) {
                        if (zzfoVar != null) {
                            zzF(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzfoVar.zzk()) {
                                zzI(sb, 2, "audience_id", Integer.valueOf(zzfoVar.zza()));
                            }
                            if (zzfoVar.zzm()) {
                                zzI(sb, 2, "new_audience", Boolean.valueOf(zzfoVar.zzj()));
                            }
                            zzH(sb, 2, "current_data", zzfoVar.zzd());
                            if (zzfoVar.zzn()) {
                                zzH(sb, 2, "previous_data", zzfoVar.zze());
                            }
                            zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzfs> listZzN = zzgcVar.zzN();
                if (listZzN != null) {
                    for (com.google.android.gms.internal.measurement.zzfs zzfsVar : listZzN) {
                        if (zzfsVar != null) {
                            zzF(sb, 2);
                            sb.append("event {\n");
                            zzI(sb, 2, "name", this.zzs.zzj().zzd(zzfsVar.zzh()));
                            if (zzfsVar.zzu()) {
                                zzI(sb, 2, "timestamp_millis", Long.valueOf(zzfsVar.zzd()));
                            }
                            if (zzfsVar.zzt()) {
                                zzI(sb, 2, "previous_timestamp_millis", Long.valueOf(zzfsVar.zzc()));
                            }
                            if (zzfsVar.zzs()) {
                                zzI(sb, 2, "count", Integer.valueOf(zzfsVar.zza()));
                            }
                            if (zzfsVar.zzb() != 0) {
                                zzD(sb, 2, zzfsVar.zzi());
                            }
                            zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zzF(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    final String zzo(com.google.android.gms.internal.measurement.zzej zzejVar) {
        if (zzejVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzejVar.zzp()) {
            zzI(sb, 0, "filter_id", Integer.valueOf(zzejVar.zzb()));
        }
        zzI(sb, 0, "event_name", this.zzs.zzj().zzd(zzejVar.zzg()));
        String strZzG = zzG(zzejVar.zzk(), zzejVar.zzm(), zzejVar.zzn());
        if (!strZzG.isEmpty()) {
            zzI(sb, 0, "filter_type", strZzG);
        }
        if (zzejVar.zzo()) {
            zzJ(sb, 1, "event_count_filter", zzejVar.zzf());
        }
        if (zzejVar.zza() > 0) {
            sb.append("  filters {\n");
            Iterator it = zzejVar.zzh().iterator();
            while (it.hasNext()) {
                zzE(sb, 2, (com.google.android.gms.internal.measurement.zzel) it.next());
            }
        }
        zzF(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    final String zzp(com.google.android.gms.internal.measurement.zzes zzesVar) {
        if (zzesVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzesVar.zzj()) {
            zzI(sb, 0, "filter_id", Integer.valueOf(zzesVar.zza()));
        }
        zzI(sb, 0, "property_name", this.zzs.zzj().zzf(zzesVar.zze()));
        String strZzG = zzG(zzesVar.zzg(), zzesVar.zzh(), zzesVar.zzi());
        if (!strZzG.isEmpty()) {
            zzI(sb, 0, "filter_type", strZzG);
        }
        zzE(sb, 1, zzesVar.zzb());
        sb.append("}\n");
        return sb.toString();
    }

    final List zzq(List list, List list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() < 0) {
                this.zzs.zzay().zzk().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                int iIntValue = num.intValue() / 64;
                if (iIntValue >= arrayList.size()) {
                    this.zzs.zzay().zzk().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(iIntValue, Long.valueOf(((Long) arrayList.get(iIntValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size < 0 || ((Long) arrayList.get(size)).longValue() != 0) {
                break;
            }
            size2 = size - 1;
        }
        return arrayList.subList(0, i);
    }

    final Map zzs(Bundle bundle, boolean z) {
        HashMap map = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            boolean z2 = obj instanceof Parcelable[];
            if (z2 || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    if (z2) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zzs((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        for (int i = 0; i < size; i++) {
                            Object obj2 = arrayList2.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.add(zzs((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(zzs((Bundle) obj, false));
                    }
                    map.put(str, arrayList);
                }
            } else if (obj != null) {
                map.put(str, obj);
            }
        }
        return map;
    }

    final void zzt(com.google.android.gms.internal.measurement.zzfv zzfvVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfvVar.zzg();
        zzfvVar.zze();
        zzfvVar.zzd();
        zzfvVar.zzf();
        if (obj instanceof String) {
            zzfvVar.zzk((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzfvVar.zzi(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            zzfvVar.zzh(((Double) obj).doubleValue());
            return;
        }
        if (!(obj instanceof Bundle[])) {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) event param value", obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : (Bundle[]) obj) {
            if (bundle != null) {
                com.google.android.gms.internal.measurement.zzfv zzfvVarZze = com.google.android.gms.internal.measurement.zzfw.zze();
                for (String str : bundle.keySet()) {
                    com.google.android.gms.internal.measurement.zzfv zzfvVarZze2 = com.google.android.gms.internal.measurement.zzfw.zze();
                    zzfvVarZze2.zzj(str);
                    Object obj2 = bundle.get(str);
                    if (obj2 instanceof Long) {
                        zzfvVarZze2.zzi(((Long) obj2).longValue());
                    } else if (obj2 instanceof String) {
                        zzfvVarZze2.zzk((String) obj2);
                    } else if (obj2 instanceof Double) {
                        zzfvVarZze2.zzh(((Double) obj2).doubleValue());
                    }
                    zzfvVarZze.zzc(zzfvVarZze2);
                }
                if (zzfvVarZze.zza() > 0) {
                    arrayList.add((com.google.android.gms.internal.measurement.zzfw) zzfvVarZze.zzaE());
                }
            }
        }
        zzfvVar.zzb(arrayList);
    }

    final void zzu(com.google.android.gms.internal.measurement.zzgk zzgkVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzgkVar.zzc();
        zzgkVar.zzb();
        zzgkVar.zza();
        if (obj instanceof String) {
            zzgkVar.zzh((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzgkVar.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzgkVar.zzd(((Double) obj).doubleValue());
        } else {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }

    final boolean zzw(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzs.zzav().currentTimeMillis() - j) > j2;
    }

    final byte[] zzy(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            this.zzs.zzay().zzd().zzb("Failed to gzip content", e);
            throw e;
        }
    }
}
