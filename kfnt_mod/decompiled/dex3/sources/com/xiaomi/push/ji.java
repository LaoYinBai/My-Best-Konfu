package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ji {
    private static int a = Integer.MAX_VALUE;

    public static void a(jf jfVar, byte b) {
        a(jfVar, b, a);
    }

    public static void a(jf jfVar, byte b, int i) throws iz {
        if (i <= 0) {
            throw new iz("Maximum skip depth exceeded");
        }
        int i2 = 0;
        switch (b) {
            case 2:
                jfVar.mo541a();
                return;
            case 3:
                jfVar.a();
                return;
            case 4:
                jfVar.mo528a();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                jfVar.mo538a();
                return;
            case 8:
                jfVar.mo529a();
                return;
            case 10:
                jfVar.mo530a();
                return;
            case 11:
                jfVar.mo537a();
                return;
            case 12:
                jfVar.mo535a();
                while (true) {
                    jc jcVarMo531a = jfVar.mo531a();
                    if (jcVarMo531a.a == 0) {
                        jfVar.f();
                        return;
                    } else {
                        a(jfVar, jcVarMo531a.a, i - 1);
                        jfVar.g();
                    }
                }
            case 13:
                je jeVarMo533a = jfVar.mo533a();
                while (i2 < jeVarMo533a.f814a) {
                    int i3 = i - 1;
                    a(jfVar, jeVarMo533a.a, i3);
                    a(jfVar, jeVarMo533a.b, i3);
                    i2++;
                }
                jfVar.h();
                return;
            case 14:
                jj jjVarMo534a = jfVar.mo534a();
                while (i2 < jjVarMo534a.f815a) {
                    a(jfVar, jjVarMo534a.a, i - 1);
                    i2++;
                }
                jfVar.j();
                return;
            case 15:
                jd jdVarMo532a = jfVar.mo532a();
                while (i2 < jdVarMo532a.f813a) {
                    a(jfVar, jdVarMo532a.a, i - 1);
                    i2++;
                }
                jfVar.i();
                return;
        }
    }
}
