package com.ss.union.game.sdk.core.base.debug.automatic_detection.c;

import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String e = "key";
    private static final String f = "name";
    private static final String g = "successful";
    private static final String h = "errors";
    public String a;
    public String b;
    public boolean c;
    public boolean d;
    private LGDetectionConstant.DetectionState i = LGDetectionConstant.DetectionState.UN_DETECTION;
    private List<String> j;

    c() {
    }

    public c(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public LGDetectionConstant.DetectionState a() {
        return this.i;
    }

    public void a(String str) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        if (this.j.contains(str)) {
            return;
        }
        this.j.add(str);
    }

    public void a(String str, LGDetectionConstant.DetectionState detectionState) {
        boolean z = true;
        this.d = true;
        if (detectionState == LGDetectionConstant.DetectionState.PASS && this.j != null) {
            Iterator<String> it = this.j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (next.contains(str)) {
                    this.j.remove(next);
                    break;
                }
            }
        }
        if (this.j != null && this.j.size() != 0) {
            z = false;
        }
        this.c = !z;
        this.i = z ? LGDetectionConstant.DetectionState.PASS : LGDetectionConstant.DetectionState.FAIL;
    }

    public String b() {
        if (this.j == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : this.j) {
            i++;
            sb.append(i);
            sb.append("、");
            sb.append(str);
            if (this.j.size() != i) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    void b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = jSONObject.optString("key");
            this.b = jSONObject.optString("name");
            this.i = LGDetectionConstant.DetectionState.intToEnum(jSONObject.optInt(g));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(h);
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                return;
            }
            this.j = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                this.j.add(jSONArrayOptJSONArray.optString(i));
            }
            this.c = this.i == LGDetectionConstant.DetectionState.FAIL;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("key", this.a);
            jSONObject.putOpt("name", this.b);
            jSONObject.putOpt(g, Integer.valueOf(a().getValue()));
            if (this.j != null && this.j.size() != 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = this.j.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.putOpt(h, jSONArray);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
