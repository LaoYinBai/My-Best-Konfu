package com.mbridge.msdk.foundation.same.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.mbridge.msdk.foundation.same.c.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.tools.w;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/* compiled from: CommonImageLoader.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static b a;
    private com.mbridge.msdk.foundation.same.e.b b;
    private LinkedHashMap<String, List<c>> d = new LinkedHashMap<>();
    private Handler e = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.foundation.same.c.b.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                if (message.what == 1) {
                    String string = message.getData().getString("message_key");
                    Bitmap bitmapA = a.a(message.getData().getString("message_bitmap"));
                    b.this.a(string, bitmapA);
                    LinkedList linkedList = (LinkedList) b.this.d.get(string);
                    if (linkedList != null) {
                        Iterator it = linkedList.iterator();
                        while (it.hasNext()) {
                            c cVar = (c) it.next();
                            if (cVar != null) {
                                cVar.onSuccessLoad(bitmapA, string);
                            }
                        }
                    }
                    b.this.d.remove(string);
                    return;
                }
                if (message.what == 2) {
                    String string2 = message.getData().getString("message_key");
                    String string3 = message.getData().getString("message_message");
                    LinkedList linkedList2 = (LinkedList) b.this.d.get(string2);
                    if (linkedList2 != null) {
                        Iterator it2 = linkedList2.iterator();
                        while (it2.hasNext()) {
                            c cVar2 = (c) it2.next();
                            if (cVar2 != null) {
                                cVar2.onFailedLoad(string3, string2);
                            }
                        }
                    }
                    b.this.d.remove(string2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private com.mbridge.msdk.foundation.same.a.c<String, Bitmap> c = new com.mbridge.msdk.foundation.same.a.a(((int) Runtime.getRuntime().maxMemory()) / 5);

    public final void a(String str, Bitmap bitmap) {
        if (d(str) != null || bitmap == null) {
            return;
        }
        this.c.a(str, bitmap);
    }

    private Bitmap d(String str) {
        return this.c.b(str);
    }

    private b(Context context) {
        this.b = new com.mbridge.msdk.foundation.same.e.b(context);
    }

    public static b a(Context context) {
        if (a == null) {
            a = new b(context);
        }
        return a;
    }

    public final void a() {
        this.c.b();
        if (this.d != null) {
            this.d.clear();
        }
    }

    public static void b() {
        a.c();
    }

    public final void a(String str, c cVar) {
        String strA = v.a(str);
        if (w.a(str) || w.a(str) || w.a(strA)) {
            return;
        }
        File file = new File(strA);
        Bitmap bitmapD = d(str);
        if (bitmapD != null && !bitmapD.isRecycled()) {
            cVar.onSuccessLoad(bitmapD, str);
            return;
        }
        if (file.exists()) {
            Bitmap bitmapA = a.a(strA);
            if (bitmapA != null && !bitmapA.isRecycled()) {
                s.a("ImageLoader", "url image [" + str + "] is downloaded, save by file [" + strA + "]");
                a(str, bitmapA);
                cVar.onSuccessLoad(bitmapA, str);
                return;
            }
            a(str, str, strA, true, false, cVar);
            return;
        }
        a(str, str, strA, false, false, cVar);
    }

    public final Bitmap a(String str) {
        Bitmap bitmapA;
        if (w.a(str)) {
            return null;
        }
        String strA = v.a(str);
        File file = new File(strA);
        if (d(str) != null) {
            return d(str);
        }
        if (!file.exists() || (bitmapA = a.a(strA)) == null) {
            return null;
        }
        a(str, bitmapA);
        return bitmapA;
    }

    public final boolean b(String str) {
        if (w.a(str)) {
            return false;
        }
        return d(str) != null || new File(v.a(str)).exists();
    }

    private void a(String str, String str2, String str3, boolean z, boolean z2, c cVar) {
        if (!this.d.containsKey(str2)) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(cVar);
            this.d.put(str2, linkedList);
            this.b.b(a(str, str2, str3, z, z2));
            return;
        }
        LinkedList linkedList2 = (LinkedList) this.d.get(str2);
        if (linkedList2 != null && !linkedList2.contains(cVar)) {
            linkedList2.add(cVar);
        }
        s.a("ImageLoader", "loading:" + str2);
    }

    private d a(String str, String str2, String str3, boolean z, boolean z2) {
        d.a aVar = new d.a() { // from class: com.mbridge.msdk.foundation.same.c.b.2
            @Override // com.mbridge.msdk.foundation.same.c.d.a
            public final void a(String str4, String str5) {
                Message messageObtainMessage = b.this.e.obtainMessage();
                messageObtainMessage.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("message_key", str4);
                bundle.putString("message_bitmap", str5);
                messageObtainMessage.setData(bundle);
                b.this.e.sendMessage(messageObtainMessage);
            }

            @Override // com.mbridge.msdk.foundation.same.c.d.a
            public final void b(String str4, String str5) {
                Message messageObtainMessage = b.this.e.obtainMessage();
                messageObtainMessage.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("message_key", str4);
                bundle.putString("message_message", str5);
                messageObtainMessage.setData(bundle);
                b.this.e.sendMessage(messageObtainMessage);
            }
        };
        d dVar = new d(str, str2, str3);
        dVar.a(z);
        dVar.a(aVar);
        return dVar;
    }

    public final void c() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public final void c(String str) {
        try {
            if (this.c == null || !this.c.a().contains(str)) {
                return;
            }
            this.c.a(str);
        } catch (Throwable th) {
            s.d("ImageLoader", th.getMessage());
        }
    }
}
