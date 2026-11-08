package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzbi extends zzaw {
    protected zzbi() {
        this.zza.add(zzbl.ADD);
        this.zza.add(zzbl.DIVIDE);
        this.zza.add(zzbl.MODULUS);
        this.zza.add(zzbl.MULTIPLY);
        this.zza.add(zzbl.NEGATE);
        this.zza.add(zzbl.POST_DECREMENT);
        this.zza.add(zzbl.POST_INCREMENT);
        this.zza.add(zzbl.PRE_DECREMENT);
        this.zza.add(zzbl.PRE_INCREMENT);
        this.zza.add(zzbl.SUBTRACT);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List list) {
        zzbl zzblVar = zzbl.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 0:
                zzh.zzh(zzbl.ADD.name(), 2, list);
                zzap zzapVarZzb = zzgVar.zzb((zzap) list.get(0));
                zzap zzapVarZzb2 = zzgVar.zzb((zzap) list.get(1));
                return ((zzapVarZzb instanceof zzal) || (zzapVarZzb instanceof zzat) || (zzapVarZzb2 instanceof zzal) || (zzapVarZzb2 instanceof zzat)) ? new zzat(String.valueOf(zzapVarZzb.zzi()).concat(String.valueOf(zzapVarZzb2.zzi()))) : new zzah(Double.valueOf(zzapVarZzb.zzh().doubleValue() + zzapVarZzb2.zzh().doubleValue()));
            case 21:
                zzh.zzh(zzbl.DIVIDE.name(), 2, list);
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue() / zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()));
            case 44:
                zzh.zzh(zzbl.MODULUS.name(), 2, list);
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue() % zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()));
            case 45:
                zzh.zzh(zzbl.MULTIPLY.name(), 2, list);
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue() * zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()));
            case 46:
                zzh.zzh(zzbl.NEGATE.name(), 1, list);
                return new zzah(Double.valueOf(-zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue()));
            case 52:
            case 53:
                zzh.zzh(str, 2, list);
                zzap zzapVarZzb3 = zzgVar.zzb((zzap) list.get(0));
                zzgVar.zzb((zzap) list.get(1));
                return zzapVarZzb3;
            case 55:
            case 56:
                zzh.zzh(str, 1, list);
                return zzgVar.zzb((zzap) list.get(0));
            case 59:
                zzh.zzh(zzbl.SUBTRACT.name(), 2, list);
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue() + new zzah(Double.valueOf(-zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue())).zzh().doubleValue()));
            default:
                return super.zzb(str);
        }
    }
}
