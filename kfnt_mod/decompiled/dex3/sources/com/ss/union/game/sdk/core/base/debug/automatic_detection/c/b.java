package com.ss.union.game.sdk.core.base.debug.automatic_detection.c;

import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public String a;
    private Set<String> b = new LinkedHashSet();

    public void a(String str) {
        this.b.add(str);
    }

    public String a() {
        return this.b.size() != 0 ? this.b.iterator().next() : "";
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        if (this.b.size() == 1) {
            sb.append(a());
        } else {
            int i = 0;
            for (String str : this.b) {
                i++;
                sb.append("ID");
                sb.append(i);
                sb.append("：");
                sb.append(str);
                if (this.b.size() != i) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        if (this.b != null) {
            Iterator<String> it = this.b.iterator();
            while (it.hasNext()) {
                try {
                    jSONArray.put(Long.parseLong(it.next()));
                } catch (NumberFormatException unused) {
                }
            }
        }
        return jSONArray;
    }

    public int hashCode() {
        return 527 + this.a.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b) || this.a == null) {
            return false;
        }
        return this.a.equals(((b) obj).a);
    }
}
