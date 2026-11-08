package com.xiaomi.push;

import com.vivo.push.PushClientConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class fv {
    private static int a = 5000;

    /* renamed from: a, reason: collision with other field name */
    private static Vector<String> f410a = new Vector<>();
    private static int b = 330000;
    private static int c = 600000;
    private static int d = 330000;

    static {
        InputStream inputStreamOpenStream;
        Exception e;
        try {
            for (ClassLoader classLoader : m340a()) {
                Enumeration<URL> resources = classLoader.getResources("META-INF/smack-config.xml");
                while (resources.hasMoreElements()) {
                    try {
                        inputStreamOpenStream = resources.nextElement().openStream();
                        try {
                            try {
                                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                                xmlPullParserNewPullParser.setInput(inputStreamOpenStream, "UTF-8");
                                int eventType = xmlPullParserNewPullParser.getEventType();
                                do {
                                    if (eventType == 2) {
                                        if (xmlPullParserNewPullParser.getName().equals(PushClientConstants.TAG_CLASS_NAME)) {
                                            a(xmlPullParserNewPullParser);
                                        } else if (xmlPullParserNewPullParser.getName().equals("packetReplyTimeout")) {
                                            a = a(xmlPullParserNewPullParser, a);
                                        } else if (xmlPullParserNewPullParser.getName().equals("keepAliveInterval")) {
                                            b = a(xmlPullParserNewPullParser, b);
                                        } else if (xmlPullParserNewPullParser.getName().equals("mechName")) {
                                            f410a.add(xmlPullParserNewPullParser.nextText());
                                        }
                                    }
                                    eventType = xmlPullParserNewPullParser.next();
                                } while (eventType != 1);
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                inputStreamOpenStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                inputStreamOpenStream.close();
                            } catch (Exception unused) {
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        inputStreamOpenStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamOpenStream = null;
                    }
                    try {
                        inputStreamOpenStream.close();
                    } catch (Exception unused2) {
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private fv() {
    }

    public static int a() {
        return b;
    }

    private static int a(XmlPullParser xmlPullParser, int i) {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m339a() {
        return "3.1.0";
    }

    private static void a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ClassNotFoundException {
        String strNextText = xmlPullParser.nextText();
        try {
            Class.forName(strNextText);
        } catch (ClassNotFoundException unused) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + strNextText);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private static ClassLoader[] m340a() {
        ClassLoader[] classLoaderArr = {fv.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    public static int b() {
        return c;
    }
}
