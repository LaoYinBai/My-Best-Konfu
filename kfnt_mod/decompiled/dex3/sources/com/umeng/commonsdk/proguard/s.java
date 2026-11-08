package com.umeng.commonsdk.proguard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class s extends z {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final ax j = new ax("TApplicationException");
    private static final an k = new an("message", (byte) 11, 1);
    private static final an l = new an("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i;

    public s() {
        this.i = 0;
    }

    public s(int i) {
        this.i = 0;
        this.i = i;
    }

    public s(int i, String str) {
        super(str);
        this.i = 0;
        this.i = i;
    }

    public s(String str) {
        super(str);
        this.i = 0;
    }

    public int a() {
        return this.i;
    }

    public static s a(as asVar) throws z {
        asVar.j();
        String strZ = null;
        int iW = 0;
        while (true) {
            an anVarL = asVar.l();
            if (anVarL.b != 0) {
                switch (anVarL.c) {
                    case 1:
                        if (anVarL.b == 11) {
                            strZ = asVar.z();
                            break;
                        } else {
                            av.a(asVar, anVarL.b);
                            break;
                        }
                    case 2:
                        if (anVarL.b == 8) {
                            iW = asVar.w();
                            break;
                        } else {
                            av.a(asVar, anVarL.b);
                            break;
                        }
                    default:
                        av.a(asVar, anVarL.b);
                        break;
                }
                asVar.m();
            } else {
                asVar.k();
                return new s(iW, strZ);
            }
        }
    }

    public void b(as asVar) throws z {
        asVar.a(j);
        if (getMessage() != null) {
            asVar.a(k);
            asVar.a(getMessage());
            asVar.c();
        }
        asVar.a(l);
        asVar.a(this.i);
        asVar.c();
        asVar.d();
        asVar.b();
    }
}
