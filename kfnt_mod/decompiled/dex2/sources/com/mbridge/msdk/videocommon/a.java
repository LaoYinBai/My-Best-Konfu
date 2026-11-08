package com.mbridge.msdk.videocommon;

import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: TemplateWebviewCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static ConcurrentHashMap<String, C0146a> a = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> b = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> c = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> d = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> e = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> f = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> g = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> h = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> i = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C0146a> j = new ConcurrentHashMap<>();

    /* compiled from: TemplateWebviewCache.java */
    /* renamed from: com.mbridge.msdk.videocommon.a$a, reason: collision with other inner class name */
    public static class C0146a {
        private WindVaneWebView a;
        private boolean b;

        public final WindVaneWebView a() {
            return this.a;
        }

        public final void a(WindVaneWebView windVaneWebView) {
            this.a = windVaneWebView;
        }

        public final void a(String str) {
            if (this.a != null) {
                this.a.setTag(str);
            }
        }

        public final String b() {
            return this.a != null ? (String) this.a.getTag() : "";
        }

        public final boolean c() {
            return this.b;
        }

        public final void a(boolean z) {
            this.b = z;
        }
    }

    public static C0146a a(String str) {
        if (g.containsKey(str)) {
            return g.get(str);
        }
        if (h.containsKey(str)) {
            return h.get(str);
        }
        if (i.containsKey(str)) {
            return i.get(str);
        }
        if (j.containsKey(str)) {
            return j.get(str);
        }
        return null;
    }

    public static void a(String str, C0146a c0146a, boolean z, boolean z2) {
        if (z) {
            if (z2) {
                h.put(str, c0146a);
                return;
            } else {
                g.put(str, c0146a);
                return;
            }
        }
        if (z2) {
            j.put(str, c0146a);
        } else {
            i.put(str, c0146a);
        }
    }

    public static void b(String str) {
        if (g.containsKey(str)) {
            g.remove(str);
        }
        if (i.containsKey(str)) {
            i.remove(str);
        }
        if (h.containsKey(str)) {
            h.remove(str);
        }
        if (j.containsKey(str)) {
            j.remove(str);
        }
    }

    public static void a() {
        g.clear();
        h.clear();
    }

    public static void b() {
        i.clear();
        j.clear();
    }

    public static void c(String str) {
        for (Map.Entry<String, C0146a> entry : g.entrySet()) {
            if (entry.getKey().contains(str)) {
                g.remove(entry.getKey());
            }
        }
    }

    public static void d(String str) {
        for (Map.Entry<String, C0146a> entry : h.entrySet()) {
            if (entry.getKey().contains(str)) {
                h.remove(entry.getKey());
            }
        }
    }

    public static C0146a a(int i2, CampaignEx campaignEx) {
        if (campaignEx == null) {
            return null;
        }
        try {
            String requestIdNotice = campaignEx.getRequestIdNotice();
            if (i2 == 288) {
                requestIdNotice = campaignEx.getKeyIaUrl();
            }
            if (i2 == 94) {
                if (campaignEx.isBidCampaign()) {
                    if (b != null && b.size() > 0) {
                        return b.get(requestIdNotice);
                    }
                } else if (e != null && e.size() > 0) {
                    return e.get(requestIdNotice);
                }
            } else {
                switch (i2) {
                    case 287:
                        if (campaignEx.isBidCampaign()) {
                            if (c != null && c.size() > 0) {
                                return c.get(requestIdNotice);
                            }
                        } else if (f != null && f.size() > 0) {
                            return f.get(requestIdNotice);
                        }
                        break;
                    case 288:
                        if (d != null && d.size() > 0) {
                            return d.get(requestIdNotice);
                        }
                        break;
                    default:
                        if (a != null && a.size() > 0) {
                            return a.get(requestIdNotice);
                        }
                        break;
                }
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void b(int i2, CampaignEx campaignEx) {
        if (campaignEx == null) {
        }
        try {
            String requestIdNotice = campaignEx.getRequestIdNotice();
            if (i2 == 288) {
                requestIdNotice = campaignEx.getKeyIaUrl();
            }
            if (i2 == 94) {
                if (campaignEx.isBidCampaign()) {
                    if (b != null) {
                        b.remove(requestIdNotice);
                        return;
                    }
                    return;
                } else {
                    if (e != null) {
                        e.remove(requestIdNotice);
                        return;
                    }
                    return;
                }
            }
            switch (i2) {
                case 287:
                    if (campaignEx.isBidCampaign()) {
                        if (c != null) {
                            c.remove(requestIdNotice);
                            break;
                        }
                    } else if (f != null) {
                        f.remove(requestIdNotice);
                        break;
                    }
                    break;
                case 288:
                    if (d != null) {
                        d.remove(requestIdNotice);
                        break;
                    }
                    break;
                default:
                    if (a != null) {
                        a.remove(requestIdNotice);
                        break;
                    }
                    break;
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(int i2) {
        try {
            if (i2 == 94) {
                if (e != null) {
                    e.clear();
                }
            } else {
                switch (i2) {
                    case 287:
                        if (f != null) {
                            f.clear();
                            break;
                        }
                        break;
                    case 288:
                        if (d != null) {
                            d.clear();
                            break;
                        }
                        break;
                    default:
                        if (a != null) {
                            a.clear();
                            break;
                        }
                        break;
                }
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(int i2, String str, C0146a c0146a) {
        try {
            if (i2 == 94) {
                if (b == null) {
                    b = new ConcurrentHashMap<>();
                }
                b.put(str, c0146a);
            } else {
                if (i2 != 287) {
                    return;
                }
                if (c == null) {
                    c = new ConcurrentHashMap<>();
                }
                c.put(str, c0146a);
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    public static void b(int i2, String str, C0146a c0146a) {
        try {
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
            return;
        }
        if (i2 == 94) {
            if (e == null) {
                e = new ConcurrentHashMap<>();
            }
            e.put(str, c0146a);
            return;
        }
        switch (i2) {
            case 287:
                if (f == null) {
                    f = new ConcurrentHashMap<>();
                }
                f.put(str, c0146a);
                break;
            case 288:
                if (d == null) {
                    d = new ConcurrentHashMap<>();
                }
                d.put(str, c0146a);
                break;
            default:
                if (a == null) {
                    a = new ConcurrentHashMap<>();
                }
                a.put(str, c0146a);
                break;
        }
    }
}
