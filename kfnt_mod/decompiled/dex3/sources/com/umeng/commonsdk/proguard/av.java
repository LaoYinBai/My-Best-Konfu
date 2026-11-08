package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.am;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class av {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(as asVar, byte b) throws z {
        a(asVar, b, a);
    }

    public static void a(as asVar, byte b, int i) throws z {
        if (i <= 0) {
            throw new z("Maximum skip depth exceeded");
        }
        int i2 = 0;
        switch (b) {
            case 2:
                asVar.t();
                return;
            case 3:
                asVar.u();
                return;
            case 4:
                asVar.y();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                asVar.v();
                return;
            case 8:
                asVar.w();
                return;
            case 10:
                asVar.x();
                return;
            case 11:
                asVar.A();
                return;
            case 12:
                asVar.j();
                while (true) {
                    an anVarL = asVar.l();
                    if (anVarL.b != 0) {
                        a(asVar, anVarL.b, i - 1);
                        asVar.m();
                    } else {
                        asVar.k();
                        return;
                    }
                }
            case 13:
                ap apVarN = asVar.n();
                while (i2 < apVarN.c) {
                    int i3 = i - 1;
                    a(asVar, apVarN.a, i3);
                    a(asVar, apVarN.b, i3);
                    i2++;
                }
                asVar.o();
                return;
            case 14:
                aw awVarR = asVar.r();
                while (i2 < awVarR.b) {
                    a(asVar, awVarR.a, i - 1);
                    i2++;
                }
                asVar.s();
                return;
            case 15:
                ao aoVarP = asVar.p();
                while (i2 < aoVarP.b) {
                    a(asVar, aoVarP.a, i - 1);
                    i2++;
                }
                asVar.q();
                return;
        }
    }

    public static au a(byte[] bArr, au auVar) {
        if (bArr[0] > 16) {
            return new am.a();
        }
        return (bArr.length <= 1 || (bArr[1] & 128) == 0) ? auVar : new am.a();
    }
}
