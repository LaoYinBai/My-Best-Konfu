package com.xiaomi.push.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.taobao.accs.utl.UtilityImpl;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class av {
    private static av a;

    /* renamed from: a, reason: collision with other field name */
    private ConcurrentHashMap<String, HashMap<String, b>> f894a = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with other field name */
    private List<a> f893a = new ArrayList();

    public interface a {
        void a();
    }

    public static class b {

        /* renamed from: a, reason: collision with other field name */
        public Context f895a;

        /* renamed from: a, reason: collision with other field name */
        Messenger f897a;

        /* renamed from: a, reason: collision with other field name */
        private XMPushService f899a;

        /* renamed from: a, reason: collision with other field name */
        public e f902a;

        /* renamed from: a, reason: collision with other field name */
        public String f903a;

        /* renamed from: a, reason: collision with other field name */
        public boolean f905a;

        /* renamed from: b, reason: collision with other field name */
        public String f906b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;

        /* renamed from: a, reason: collision with other field name */
        c f901a = c.unbind;
        private int a = 0;

        /* renamed from: a, reason: collision with other field name */
        private List<a> f904a = new ArrayList();
        c b = null;

        /* renamed from: b, reason: collision with other field name */
        private boolean f907b = false;

        /* renamed from: a, reason: collision with other field name */
        private XMPushService.b f898a = new XMPushService.b(this);

        /* renamed from: a, reason: collision with other field name */
        IBinder.DeathRecipient f896a = null;

        /* renamed from: a, reason: collision with other field name */
        final C0166b f900a = new C0166b();

        public interface a {
            void a(c cVar, c cVar2, int i);
        }

        /* renamed from: com.xiaomi.push.service.av$b$b, reason: collision with other inner class name */
        class C0166b extends XMPushService.i {

            /* renamed from: a, reason: collision with other field name */
            String f908a;
            int b;

            /* renamed from: b, reason: collision with other field name */
            String f909b;
            int c;

            public C0166b() {
                super(0);
            }

            public XMPushService.i a(int i, int i2, String str, String str2) {
                this.b = i;
                this.c = i2;
                this.f909b = str2;
                this.f908a = str;
                return this;
            }

            @Override // com.xiaomi.push.service.XMPushService.i
            public String a() {
                return "notify job";
            }

            @Override // com.xiaomi.push.service.XMPushService.i
            /* renamed from: a */
            public void mo342a() throws RemoteException {
                if (b.this.a(this.b, this.c, this.f909b)) {
                    b.this.a(this.b, this.c, this.f908a, this.f909b);
                    return;
                }
                com.xiaomi.channel.commonutils.logger.b.b(" ignore notify client :" + b.this.g);
            }
        }

        class c implements IBinder.DeathRecipient {
            final Messenger a;

            /* renamed from: a, reason: collision with other field name */
            final b f910a;

            c(b bVar, Messenger messenger) {
                this.f910a = bVar;
                this.a = messenger;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                com.xiaomi.channel.commonutils.logger.b.b("peer died, chid = " + this.f910a.g);
                b.this.f899a.a(new ax(this, 0), 0L);
                if ("9".equals(this.f910a.g) && "com.xiaomi.xmsf".equals(b.this.f899a.getPackageName())) {
                    b.this.f899a.a(new ay(this, 0), 60000L);
                }
            }
        }

        public b() {
        }

        public b(XMPushService xMPushService) {
            this.f899a = xMPushService;
            a(new aw(this));
        }

        public static String a(String str) {
            int iLastIndexOf;
            return (TextUtils.isEmpty(str) || (iLastIndexOf = str.lastIndexOf("/")) == -1) ? "" : str.substring(iLastIndexOf + 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, int i2, String str, String str2) throws RemoteException {
            this.b = this.f901a;
            if (i == 2) {
                this.f902a.a(this.f895a, this, i2);
                return;
            }
            if (i == 3) {
                this.f902a.a(this.f895a, this, str2, str);
                return;
            }
            if (i == 1) {
                boolean z = this.f901a == c.binded;
                if (!z && "wait".equals(str2)) {
                    this.a++;
                } else if (z) {
                    this.a = 0;
                    if (this.f897a != null) {
                        try {
                            this.f897a.send(Message.obtain(null, 16, this.f899a.f833a));
                        } catch (RemoteException unused) {
                        }
                    }
                }
                this.f902a.a(this.f899a, this, z, i2, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(int i, int i2, String str) {
            StringBuilder sb;
            String str2;
            if (this.b == null || !this.f907b) {
                return true;
            }
            if (this.b == this.f901a) {
                sb = new StringBuilder();
                str2 = " status recovered, don't notify client:";
            } else {
                if (this.f897a != null && this.f907b) {
                    com.xiaomi.channel.commonutils.logger.b.b("Peer alive notify status to client:" + this.g);
                    return true;
                }
                sb = new StringBuilder();
                str2 = "peer died, ignore notify ";
            }
            sb.append(str2);
            sb.append(this.g);
            com.xiaomi.channel.commonutils.logger.b.b(sb.toString());
            return false;
        }

        private boolean b(int i, int i2, String str) {
            switch (i) {
                case 1:
                    return (this.f901a == c.binded || !this.f899a.m574c() || i2 == 21 || (i2 == 7 && "wait".equals(str))) ? false : true;
                case 2:
                    return this.f899a.m574c();
                case 3:
                    return !"wait".equals(str);
                default:
                    return false;
            }
        }

        public long a() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((this.a + 1) * 15)) * 1000;
        }

        public String a(int i) {
            switch (i) {
                case 1:
                    return "OPEN";
                case 2:
                    return "CLOSE";
                case 3:
                    return "KICK";
                default:
                    return UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        void m611a() {
            try {
                Messenger messenger = this.f897a;
                if (messenger != null && this.f896a != null) {
                    messenger.getBinder().unlinkToDeath(this.f896a, 0);
                }
            } catch (Exception unused) {
            }
            this.b = null;
        }

        void a(Messenger messenger) {
            m611a();
            try {
                if (messenger != null) {
                    this.f897a = messenger;
                    this.f907b = true;
                    this.f896a = new c(this, messenger);
                    messenger.getBinder().linkToDeath(this.f896a, 0);
                } else {
                    com.xiaomi.channel.commonutils.logger.b.b("peer linked with old sdk chid = " + this.g);
                }
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.b("peer linkToDeath err: " + e.getMessage());
                this.f897a = null;
                this.f907b = false;
            }
        }

        public void a(a aVar) {
            synchronized (this.f904a) {
                this.f904a.add(aVar);
            }
        }

        public void a(c cVar, int i, int i2, String str, String str2) {
            synchronized (this.f904a) {
                Iterator<a> it = this.f904a.iterator();
                while (it.hasNext()) {
                    it.next().a(this.f901a, cVar, i2);
                }
            }
            int i3 = 0;
            if (this.f901a != cVar) {
                com.xiaomi.channel.commonutils.logger.b.m22a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", this.f901a, cVar, a(i), az.a(i2), str, str2, this.g));
                this.f901a = cVar;
            }
            if (this.f902a == null) {
                com.xiaomi.channel.commonutils.logger.b.d("status changed while the client dispatcher is missing");
                return;
            }
            if (cVar == c.binding) {
                return;
            }
            if (this.b != null && this.f907b) {
                i3 = (this.f897a == null || !this.f907b) ? 10100 : 1000;
            }
            this.f899a.b(this.f900a);
            if (b(i, i2, str2)) {
                a(i, i2, str, str2);
            } else {
                this.f899a.a(this.f900a.a(i, i2, str, str2), i3);
            }
        }

        public void b(a aVar) {
            synchronized (this.f904a) {
                this.f904a.remove(aVar);
            }
        }
    }

    public enum c {
        unbind,
        binding,
        binded
    }

    private av() {
    }

    public static synchronized av a() {
        if (a == null) {
            a = new av();
        }
        return a;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int iIndexOf = str.indexOf("@");
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized int m603a() {
        return this.f894a.size();
    }

    public synchronized b a(String str, String str2) {
        HashMap<String, b> map = this.f894a.get(str);
        if (map == null) {
            return null;
        }
        return map.get(a(str2));
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized ArrayList<b> m604a() {
        ArrayList<b> arrayList;
        arrayList = new ArrayList<>();
        Iterator<HashMap<String, b>> it = this.f894a.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().values());
        }
        return arrayList;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized Collection<b> m605a(String str) {
        if (this.f894a.containsKey(str)) {
            return ((HashMap) this.f894a.get(str).clone()).values();
        }
        return new ArrayList();
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized List<String> m606a(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<HashMap<String, b>> it = this.f894a.values().iterator();
        while (it.hasNext()) {
            for (b bVar : it.next().values()) {
                if (str.equals(bVar.f903a)) {
                    arrayList.add(bVar.g);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m607a() {
        Iterator<b> it = m604a().iterator();
        while (it.hasNext()) {
            it.next().m611a();
        }
        this.f894a.clear();
    }

    public synchronized void a(Context context) {
        Iterator<HashMap<String, b>> it = this.f894a.values().iterator();
        while (it.hasNext()) {
            Iterator<b> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                it2.next().a(c.unbind, 1, 3, (String) null, (String) null);
            }
        }
    }

    public synchronized void a(Context context, int i) {
        Iterator<HashMap<String, b>> it = this.f894a.values().iterator();
        while (it.hasNext()) {
            Iterator<b> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                it2.next().a(c.unbind, 2, i, (String) null, (String) null);
            }
        }
    }

    public synchronized void a(a aVar) {
        this.f893a.add(aVar);
    }

    public synchronized void a(b bVar) {
        HashMap<String, b> map = this.f894a.get(bVar.g);
        if (map == null) {
            map = new HashMap<>();
            this.f894a.put(bVar.g, map);
        }
        map.put(a(bVar.f906b), bVar);
        Iterator<a> it = this.f893a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m608a(String str) {
        HashMap<String, b> map = this.f894a.get(str);
        if (map != null) {
            Iterator<b> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().m611a();
            }
            map.clear();
            this.f894a.remove(str);
        }
        Iterator<a> it2 = this.f893a.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m609a(String str, String str2) {
        HashMap<String, b> map = this.f894a.get(str);
        if (map != null) {
            b bVar = map.get(a(str2));
            if (bVar != null) {
                bVar.m611a();
            }
            map.remove(a(str2));
            if (map.isEmpty()) {
                this.f894a.remove(str);
            }
        }
        Iterator<a> it = this.f893a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public synchronized void b() {
        this.f893a.clear();
    }
}
