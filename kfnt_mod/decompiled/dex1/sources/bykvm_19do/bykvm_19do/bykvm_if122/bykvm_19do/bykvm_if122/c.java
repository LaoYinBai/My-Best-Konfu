package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122;

import androidx.core.app.NotificationManagerCompat;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.b0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static Map<Integer, List<i>> a(List<i> list) {
        int iM;
        List arrayList;
        if (list == null || list.size() == 0) {
            return null;
        }
        b0.a(list);
        HashMap map = new HashMap();
        int i = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        for (int i2 = 0; i2 < list.size(); i2++) {
            i iVar = list.get(i2);
            if (iVar != null) {
                if (iVar.m() != i) {
                    arrayList = new ArrayList();
                    iM = iVar.m();
                } else {
                    iM = i;
                    arrayList = (List) map.get(Integer.valueOf(i));
                }
                if (arrayList != null) {
                    arrayList.add(iVar);
                    map.put(Integer.valueOf(iM), arrayList);
                }
                i = iM;
            }
        }
        return map;
    }
}
