package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzbk extends zzaw {
    protected zzbk() {
        this.zza.add(zzbl.ASSIGN);
        this.zza.add(zzbl.CONST);
        this.zza.add(zzbl.CREATE_ARRAY);
        this.zza.add(zzbl.CREATE_OBJECT);
        this.zza.add(zzbl.EXPRESSION_LIST);
        this.zza.add(zzbl.GET);
        this.zza.add(zzbl.GET_INDEX);
        this.zza.add(zzbl.GET_PROPERTY);
        this.zza.add(zzbl.NULL);
        this.zza.add(zzbl.SET_PROPERTY);
        this.zza.add(zzbl.TYPEOF);
        this.zza.add(zzbl.UNDEFINED);
        this.zza.add(zzbl.VAR);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List list) {
        String str2;
        zzbl zzblVar = zzbl.ADD;
        int i = 0;
        switch (zzh.zze(str).ordinal()) {
            case 3:
                zzh.zzh(zzbl.ASSIGN.name(), 2, list);
                zzap zzapVarZzb = zzgVar.zzb((zzap) list.get(0));
                if (!(zzapVarZzb instanceof zzat)) {
                    throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", zzapVarZzb.getClass().getCanonicalName()));
                }
                if (!zzgVar.zzh(zzapVarZzb.zzi())) {
                    throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", zzapVarZzb.zzi()));
                }
                zzap zzapVarZzb2 = zzgVar.zzb((zzap) list.get(1));
                zzgVar.zzg(zzapVarZzb.zzi(), zzapVarZzb2);
                return zzapVarZzb2;
            case 14:
                zzh.zzi(zzbl.CONST.name(), 2, list);
                if (list.size() % 2 != 0) {
                    throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", Integer.valueOf(list.size())));
                }
                for (int i2 = 0; i2 < list.size() - 1; i2 += 2) {
                    zzap zzapVarZzb3 = zzgVar.zzb((zzap) list.get(i2));
                    if (!(zzapVarZzb3 instanceof zzat)) {
                        throw new IllegalArgumentException(String.format("Expected string for const name. got %s", zzapVarZzb3.getClass().getCanonicalName()));
                    }
                    zzgVar.zzf(zzapVarZzb3.zzi(), zzgVar.zzb((zzap) list.get(i2 + 1)));
                }
                return zzap.zzf;
            case 17:
                if (list.isEmpty()) {
                    return new zzae();
                }
                zzae zzaeVar = new zzae();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    zzap zzapVarZzb4 = zzgVar.zzb((zzap) it.next());
                    if (zzapVarZzb4 instanceof zzag) {
                        throw new IllegalStateException("Failed to evaluate array element");
                    }
                    zzaeVar.zzq(i, zzapVarZzb4);
                    i++;
                }
                return zzaeVar;
            case 18:
                if (list.isEmpty()) {
                    return new zzam();
                }
                if (list.size() % 2 != 0) {
                    throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", Integer.valueOf(list.size())));
                }
                zzam zzamVar = new zzam();
                while (i < list.size() - 1) {
                    zzap zzapVarZzb5 = zzgVar.zzb((zzap) list.get(i));
                    zzap zzapVarZzb6 = zzgVar.zzb((zzap) list.get(i + 1));
                    if ((zzapVarZzb5 instanceof zzag) || (zzapVarZzb6 instanceof zzag)) {
                        throw new IllegalStateException("Failed to evaluate map entry");
                    }
                    zzamVar.zzr(zzapVarZzb5.zzi(), zzapVarZzb6);
                    i += 2;
                }
                return zzamVar;
            case 24:
                zzh.zzi(zzbl.EXPRESSION_LIST.name(), 1, list);
                zzap zzapVarZzb7 = zzap.zzf;
                while (i < list.size()) {
                    zzapVarZzb7 = zzgVar.zzb((zzap) list.get(i));
                    if (zzapVarZzb7 instanceof zzag) {
                        throw new IllegalStateException("ControlValue cannot be in an expression list");
                    }
                    i++;
                }
                return zzapVarZzb7;
            case 33:
                zzh.zzh(zzbl.GET.name(), 1, list);
                zzap zzapVarZzb8 = zzgVar.zzb((zzap) list.get(0));
                if (zzapVarZzb8 instanceof zzat) {
                    return zzgVar.zzd(zzapVarZzb8.zzi());
                }
                throw new IllegalArgumentException(String.format("Expected string for get var. got %s", zzapVarZzb8.getClass().getCanonicalName()));
            case 35:
            case 36:
                zzh.zzh(zzbl.GET_PROPERTY.name(), 2, list);
                zzap zzapVarZzb9 = zzgVar.zzb((zzap) list.get(0));
                zzap zzapVarZzb10 = zzgVar.zzb((zzap) list.get(1));
                if ((zzapVarZzb9 instanceof zzae) && zzh.zzk(zzapVarZzb10)) {
                    return ((zzae) zzapVarZzb9).zze(zzapVarZzb10.zzh().intValue());
                }
                if (zzapVarZzb9 instanceof zzal) {
                    return ((zzal) zzapVarZzb9).zzf(zzapVarZzb10.zzi());
                }
                if (zzapVarZzb9 instanceof zzat) {
                    if ("length".equals(zzapVarZzb10.zzi())) {
                        return new zzah(Double.valueOf(zzapVarZzb9.zzi().length()));
                    }
                    if (zzh.zzk(zzapVarZzb10) && zzapVarZzb10.zzh().doubleValue() < zzapVarZzb9.zzi().length()) {
                        return new zzat(String.valueOf(zzapVarZzb9.zzi().charAt(zzapVarZzb10.zzh().intValue())));
                    }
                }
                return zzap.zzf;
            case 49:
                zzh.zzh(zzbl.NULL.name(), 0, list);
                return zzap.zzg;
            case 58:
                zzh.zzh(zzbl.SET_PROPERTY.name(), 3, list);
                zzap zzapVarZzb11 = zzgVar.zzb((zzap) list.get(0));
                zzap zzapVarZzb12 = zzgVar.zzb((zzap) list.get(1));
                zzap zzapVarZzb13 = zzgVar.zzb((zzap) list.get(2));
                if (zzapVarZzb11 == zzap.zzf || zzapVarZzb11 == zzap.zzg) {
                    throw new IllegalStateException(String.format("Can't set property %s of %s", zzapVarZzb12.zzi(), zzapVarZzb11.zzi()));
                }
                if ((zzapVarZzb11 instanceof zzae) && (zzapVarZzb12 instanceof zzah)) {
                    ((zzae) zzapVarZzb11).zzq(zzapVarZzb12.zzh().intValue(), zzapVarZzb13);
                } else if (zzapVarZzb11 instanceof zzal) {
                    ((zzal) zzapVarZzb11).zzr(zzapVarZzb12.zzi(), zzapVarZzb13);
                }
                return zzapVarZzb13;
            case 62:
                zzh.zzh(zzbl.TYPEOF.name(), 1, list);
                zzap zzapVarZzb14 = zzgVar.zzb((zzap) list.get(0));
                if (zzapVarZzb14 instanceof zzau) {
                    str2 = "undefined";
                } else if (zzapVarZzb14 instanceof zzaf) {
                    str2 = "boolean";
                } else if (zzapVarZzb14 instanceof zzah) {
                    str2 = "number";
                } else if (zzapVarZzb14 instanceof zzat) {
                    str2 = "string";
                } else if (zzapVarZzb14 instanceof zzao) {
                    str2 = "function";
                } else {
                    if ((zzapVarZzb14 instanceof zzaq) || (zzapVarZzb14 instanceof zzag)) {
                        throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", zzapVarZzb14));
                    }
                    str2 = "object";
                }
                return new zzat(str2);
            case 63:
                zzh.zzh(zzbl.UNDEFINED.name(), 0, list);
                return zzap.zzf;
            case 64:
                zzh.zzi(zzbl.VAR.name(), 1, list);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    zzap zzapVarZzb15 = zzgVar.zzb((zzap) it2.next());
                    if (!(zzapVarZzb15 instanceof zzat)) {
                        throw new IllegalArgumentException(String.format("Expected string for var name. got %s", zzapVarZzb15.getClass().getCanonicalName()));
                    }
                    zzgVar.zze(zzapVarZzb15.zzi(), zzap.zzf);
                }
                return zzap.zzf;
            default:
                return super.zzb(str);
        }
    }
}
