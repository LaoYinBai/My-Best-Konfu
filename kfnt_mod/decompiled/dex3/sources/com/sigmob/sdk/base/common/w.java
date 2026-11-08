package com.sigmob.sdk.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class w {
    private static final Object a = new Object();
    private static w b;
    private final Context c;
    private final HashMap<BroadcastReceiver, ArrayList<b>> d = new HashMap<>();
    private final HashMap<String, ArrayList<b>> e = new HashMap<>();
    private final ArrayList<a> f = new ArrayList<>();
    private final Handler g;

    private static final class a {
        final Intent a;
        final ArrayList<b> b;

        a(Intent intent, ArrayList<b> arrayList) {
            this.a = intent;
            this.b = arrayList;
        }
    }

    private static final class b {
        final IntentFilter a;
        final BroadcastReceiver b;
        boolean c;
        boolean d;

        b(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.a = intentFilter;
            this.b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.b);
            sb.append(" filter=");
            sb.append(this.a);
            if (this.d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private w(Context context) {
        this.c = context;
        this.g = new Handler(context.getMainLooper()) { // from class: com.sigmob.sdk.base.common.w.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    w.this.a();
                }
            }
        };
    }

    public static w a(Context context) {
        w wVar;
        synchronized (a) {
            if (b == null) {
                b = new w(context.getApplicationContext());
            }
            wVar = b;
        }
        return wVar;
    }

    void a() {
        a[] aVarArr;
        while (true) {
            synchronized (this.d) {
                int size = this.f.size();
                if (size <= 0) {
                    return;
                }
                aVarArr = new a[size];
                this.f.toArray(aVarArr);
                this.f.clear();
            }
            for (a aVar : aVarArr) {
                int size2 = aVar.b.size();
                for (int i = 0; i < size2; i++) {
                    b bVar = aVar.b.get(i);
                    if (!bVar.d) {
                        bVar.b.onReceive(this.c, aVar.a);
                    }
                }
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        synchronized (this.d) {
            ArrayList<b> arrayListRemove = this.d.remove(broadcastReceiver);
            if (arrayListRemove == null) {
                return;
            }
            for (int size = arrayListRemove.size() - 1; size >= 0; size--) {
                b bVar = arrayListRemove.get(size);
                bVar.d = true;
                for (int i = 0; i < bVar.a.countActions(); i++) {
                    String action = bVar.a.getAction(i);
                    ArrayList<b> arrayList = this.e.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            b bVar2 = arrayList.get(size2);
                            if (bVar2.b == broadcastReceiver) {
                                bVar2.d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.e.remove(action);
                        }
                    }
                }
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.d) {
            b bVar = new b(intentFilter, broadcastReceiver);
            ArrayList<b> arrayList = this.d.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.d.put(broadcastReceiver, arrayList);
            }
            arrayList.add(bVar);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<b> arrayList2 = this.e.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.e.put(action, arrayList2);
                }
                arrayList2.add(bVar);
            }
        }
    }

    public boolean a(Intent intent, int i) {
        String str;
        ArrayList arrayList;
        int i2;
        ArrayList<b> arrayList2;
        String str2;
        Uri uri;
        String str3;
        synchronized (this.d) {
            String action = intent.getAction();
            String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.c.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<b> arrayList3 = this.e.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i3 = 0;
                while (i3 < arrayList3.size()) {
                    b bVar = arrayList3.get(i3);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + bVar.a);
                    }
                    if (bVar.c) {
                        if (z) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i2 = i3;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = strResolveTypeIfNeeded;
                        uri = data;
                        arrayList = arrayList4;
                    } else {
                        IntentFilter intentFilter = bVar.a;
                        String str4 = action;
                        str = action;
                        arrayList = arrayList4;
                        i2 = i3;
                        arrayList2 = arrayList3;
                        str2 = strResolveTypeIfNeeded;
                        uri = data;
                        int iMatch = intentFilter.match(str4, strResolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (iMatch >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(bVar);
                            bVar.c = true;
                            i3 = i2 + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            strResolveTypeIfNeeded = str2;
                            data = uri;
                        } else if (z) {
                            switch (iMatch) {
                                case -4:
                                    str3 = "category";
                                    break;
                                case -3:
                                    str3 = "action";
                                    break;
                                case -2:
                                    str3 = "data";
                                    break;
                                case -1:
                                    str3 = "type";
                                    break;
                                default:
                                    str3 = "unknown reason";
                                    break;
                            }
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + str3);
                        }
                    }
                    arrayList4 = arrayList;
                    i3 = i2 + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    strResolveTypeIfNeeded = str2;
                    data = uri;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i4 = 0; i4 < arrayList5.size(); i4++) {
                        ((b) arrayList5.get(i4)).c = false;
                    }
                    this.f.add(new a(intent, arrayList5));
                    if (!this.g.hasMessages(1)) {
                        if (i > 0) {
                            this.g.sendEmptyMessageDelayed(1, i);
                        } else {
                            this.g.sendEmptyMessage(1);
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
