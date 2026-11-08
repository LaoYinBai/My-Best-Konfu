package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzik implements Serializable, zzih {
    final Object zza;

    zzik(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof zzik)) {
            return false;
        }
        Object obj2 = this.zza;
        Object obj3 = ((zzik) obj).zza;
        return obj2 == obj3 || obj2.equals(obj3);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        return "Suppliers.ofInstance(" + this.zza + ")";
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    public final Object zza() {
        return this.zza;
    }
}
