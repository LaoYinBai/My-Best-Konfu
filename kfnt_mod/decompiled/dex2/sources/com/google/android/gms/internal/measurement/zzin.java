package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzim;
import com.google.android.gms.internal.measurement.zzin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class zzin<MessageType extends zzin<MessageType, BuilderType>, BuilderType extends zzim<MessageType, BuilderType>> implements zzll {
    protected int zzb = 0;

    protected static void zzbt(Iterable iterable, List list) {
        zzkm.zze(iterable);
        if (iterable instanceof zzkt) {
            List listZzh = ((zzkt) iterable).zzh();
            zzkt zzktVar = (zzkt) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzktVar.size() - size) + " is null.";
                    for (int size2 = zzktVar.size() - 1; size2 >= size; size2--) {
                        zzktVar.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof zzjd) {
                    zzktVar.zzi((zzjd) obj);
                } else {
                    zzktVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzls) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size3 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                    list.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            list.add(obj2);
        }
    }

    int zzbr() {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzll
    public final zzjd zzbs() {
        try {
            int iZzbw = zzbw();
            zzjd zzjdVar = zzjd.zzb;
            byte[] bArr = new byte[iZzbw];
            zzjl zzjlVarZzC = zzjl.zzC(bArr);
            zzbK(zzjlVarZzC);
            zzjlVarZzC.zzD();
            return new zzja(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    void zzbu(int i) {
        throw null;
    }

    public final byte[] zzbv() {
        try {
            byte[] bArr = new byte[zzbw()];
            zzjl zzjlVarZzC = zzjl.zzC(bArr);
            zzbK(zzjlVarZzC);
            zzjlVarZzC.zzD();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
