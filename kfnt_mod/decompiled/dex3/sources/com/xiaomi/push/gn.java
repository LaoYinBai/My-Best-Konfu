package com.xiaomi.push;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class gn {
    private static gn a;

    /* renamed from: a, reason: collision with other field name */
    private Map<String, Object> f438a = new ConcurrentHashMap();
    private Map<String, Object> b = new ConcurrentHashMap();

    private gn() throws Throwable {
        m362a();
    }

    public static synchronized gn a() {
        if (a == null) {
            a = new gn();
        }
        return a;
    }

    private String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        sb.append("/>");
        if (str != null) {
            sb.append("<");
            sb.append(str2);
            sb.append("/>");
        }
        return sb.toString();
    }

    /* renamed from: a, reason: collision with other method in class */
    private ClassLoader[] m360a() {
        ClassLoader[] classLoaderArr = {gn.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    /* renamed from: a, reason: collision with other method in class */
    public Object m361a(String str, String str2) {
        return this.f438a.get(a(str, str2));
    }

    /* renamed from: a, reason: collision with other method in class */
    protected void m362a() throws Throwable {
        InputStream inputStreamOpenStream;
        Map<String, Object> map;
        Object objNewInstance;
        Map<String, Object> map2;
        Object objNewInstance2;
        try {
            for (ClassLoader classLoader : m360a()) {
                Enumeration<URL> resources = classLoader.getResources("META-INF/smack.providers");
                while (resources.hasMoreElements()) {
                    try {
                        inputStreamOpenStream = resources.nextElement().openStream();
                        try {
                            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                            xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                            xmlPullParserNewPullParser.setInput(inputStreamOpenStream, "UTF-8");
                            int eventType = xmlPullParserNewPullParser.getEventType();
                            do {
                                if (eventType == 2) {
                                    if (xmlPullParserNewPullParser.getName().equals("iqProvider")) {
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText = xmlPullParserNewPullParser.nextText();
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText2 = xmlPullParserNewPullParser.nextText();
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText3 = xmlPullParserNewPullParser.nextText();
                                        String strA = a(strNextText, strNextText2);
                                        if (!this.b.containsKey(strA)) {
                                            try {
                                                Class<?> cls = Class.forName(strNextText3);
                                                if (gl.class.isAssignableFrom(cls)) {
                                                    map2 = this.b;
                                                    objNewInstance2 = cls.newInstance();
                                                } else if (ge.class.isAssignableFrom(cls)) {
                                                    map2 = this.b;
                                                    objNewInstance2 = cls;
                                                }
                                                map2.put(strA, objNewInstance2);
                                            } catch (ClassNotFoundException e) {
                                                e = e;
                                                e.printStackTrace();
                                                eventType = xmlPullParserNewPullParser.next();
                                            }
                                        }
                                    } else if (xmlPullParserNewPullParser.getName().equals("extensionProvider")) {
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText4 = xmlPullParserNewPullParser.nextText();
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText5 = xmlPullParserNewPullParser.nextText();
                                        xmlPullParserNewPullParser.next();
                                        xmlPullParserNewPullParser.next();
                                        String strNextText6 = xmlPullParserNewPullParser.nextText();
                                        String strA2 = a(strNextText4, strNextText5);
                                        if (!this.f438a.containsKey(strA2)) {
                                            try {
                                                Class<?> cls2 = Class.forName(strNextText6);
                                                if (gm.class.isAssignableFrom(cls2)) {
                                                    map = this.f438a;
                                                    objNewInstance = cls2.newInstance();
                                                } else if (gh.class.isAssignableFrom(cls2)) {
                                                    map = this.f438a;
                                                    objNewInstance = cls2;
                                                }
                                                map.put(strA2, objNewInstance);
                                            } catch (ClassNotFoundException e2) {
                                                e = e2;
                                                e.printStackTrace();
                                                eventType = xmlPullParserNewPullParser.next();
                                            }
                                        }
                                    }
                                }
                                eventType = xmlPullParserNewPullParser.next();
                            } while (eventType != 1);
                            inputStreamOpenStream.close();
                        } catch (Throwable th) {
                            th = th;
                            try {
                                inputStreamOpenStream.close();
                            } catch (Exception unused) {
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamOpenStream = null;
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void a(String str, String str2, Object obj) {
        if (!(obj instanceof gm) && !(obj instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        this.f438a.put(a(str, str2), obj);
    }
}
