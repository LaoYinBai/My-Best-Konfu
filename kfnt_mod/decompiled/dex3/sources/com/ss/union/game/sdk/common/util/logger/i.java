package com.ss.union.game.sdk.common.util.logger;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements k {
    private static final int a = 2;
    private final ThreadLocal<String> b = new ThreadLocal<>();
    private final List<e> c = new ArrayList();

    i() {
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public k a(String str) {
        if (str != null) {
            this.b.set(str);
        }
        return this;
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void a(String str, Object... objArr) {
        a(3, (Throwable) null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void a(Object obj) {
        if (obj != null) {
            if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
                b(obj.toString());
            } else {
                a(3, (Throwable) null, j.a(obj), new Object[0]);
            }
        }
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void b(String str, Object... objArr) {
        a(null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void a(Throwable th, String str, Object... objArr) {
        a(6, th, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void c(String str, Object... objArr) {
        a(5, (Throwable) null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void d(String str, Object... objArr) {
        a(4, (Throwable) null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void e(String str, Object... objArr) {
        a(2, (Throwable) null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void f(String str, Object... objArr) {
        a(7, (Throwable) null, str, objArr);
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void b(String str) {
        if (str == null || str.length() <= 0) {
            a("Empty/Null json content");
            return;
        }
        try {
            String strTrim = str.trim();
            if (strTrim.startsWith("{")) {
                a((Object) new JSONObject(strTrim).toString(2));
            } else if (strTrim.startsWith("[")) {
                a((Object) new JSONArray(strTrim).toString(2));
            } else {
                b("Invalid Json", new Object[0]);
            }
        } catch (Throwable unused) {
            b("Invalid Json", new Object[0]);
        }
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void c(String str) {
        if (str == null || str.length() <= 0) {
            a("Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("indent", "yes");
            transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformerNewTransformer.transform(streamSource, streamResult);
            a((Object) streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (Throwable unused) {
            b("Invalid xml", new Object[0]);
        }
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public synchronized void a(int i, String str, String str2, Throwable th) {
        if (th != null && str2 != null) {
            try {
                str2 = str2 + " : " + j.a(th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (th != null && str2 == null) {
            str2 = j.a(th);
        }
        if (str2 == null || str2.length() <= 0) {
            str2 = "Empty/NULL log message";
        }
        for (e eVar : this.c) {
            if (eVar.a(i, str)) {
                eVar.a(i, str, str2);
            }
        }
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void a() {
        this.c.clear();
    }

    @Override // com.ss.union.game.sdk.common.util.logger.k
    public void a(e eVar) {
        if (eVar != null) {
            this.c.add(eVar);
        }
    }

    private synchronized void a(int i, Throwable th, String str, Object... objArr) {
        if (str == null) {
            str = "";
        }
        try {
            a(i, b(), g(str, objArr), th);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private String b() {
        String str = this.b.get();
        if (str == null) {
            return null;
        }
        this.b.remove();
        return str;
    }

    private String g(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }
}
