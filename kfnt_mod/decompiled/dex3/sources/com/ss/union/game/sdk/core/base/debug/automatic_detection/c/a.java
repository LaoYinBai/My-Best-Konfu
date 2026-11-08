package com.ss.union.game.sdk.core.base.debug.automatic_detection.c;

import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String c = "id";
    private static final String d = "created_at";
    private static final String e = "finished_at";
    private static final String f = "detect_items";
    public long a;
    public List<c> b;
    private long g;
    private long h;

    public String a() {
        return DateUtils.dateToString(new Date(a(this.g)), "yyyy-MM-dd HH:mm");
    }

    public String b() {
        return DateUtils.dateToString(new Date(a(this.h)), "yyyy-MM-dd HH:mm");
    }

    private long a(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("");
        return sb.toString().length() == 10 ? j * 1000 : j;
    }

    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = jSONObject.optLong("id");
            this.g = jSONObject.optLong(d);
            this.h = jSONObject.optLong(e);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(f);
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
                this.b = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    c cVar = new c();
                    cVar.b(jSONArrayOptJSONArray.optJSONObject(i).toString());
                    this.b.add(cVar);
                }
            }
            if (this.b != null) {
                this.b = a(this.b);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private List<c> a(List<c> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (c cVar : list) {
            if (cVar.a() == LGDetectionConstant.DetectionState.FAIL) {
                arrayList2.add(cVar);
            } else if (cVar.a() == LGDetectionConstant.DetectionState.PASS) {
                arrayList3.add(cVar);
            } else {
                arrayList4.add(cVar);
            }
        }
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        return arrayList;
    }
}
