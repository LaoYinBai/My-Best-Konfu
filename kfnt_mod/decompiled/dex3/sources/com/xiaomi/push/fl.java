package com.xiaomi.push;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fl {
    private XmlPullParser a;

    fl() throws XmlPullParserException {
        try {
            this.a = XmlPullParserFactory.newInstance().newPullParser();
            this.a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException unused) {
        }
    }

    gg a(byte[] bArr, fp fpVar) throws XmlPullParserException, ga, IOException {
        this.a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.a.next();
        int eventType = this.a.getEventType();
        String name = this.a.getName();
        if (eventType != 2) {
            return null;
        }
        if (name.equals("message")) {
            return go.a(this.a);
        }
        if (name.equals("iq")) {
            return go.a(this.a, fpVar);
        }
        if (name.equals("presence")) {
            return go.m363a(this.a);
        }
        if (this.a.getName().equals("stream")) {
            return null;
        }
        if (this.a.getName().equals("error")) {
            throw new ga(go.m364a(this.a));
        }
        if (!this.a.getName().equals("warning")) {
            this.a.getName().equals("bind");
            return null;
        }
        this.a.next();
        this.a.getName().equals("multi-login");
        return null;
    }
}
