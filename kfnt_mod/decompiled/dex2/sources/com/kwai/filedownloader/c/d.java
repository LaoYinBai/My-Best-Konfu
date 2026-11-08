package com.kwai.filedownloader.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public static boolean a(int i) {
        return i < 0;
    }

    public static boolean a(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || a(i)) {
            return false;
        }
        if (i >= 1 && i <= 6 && i2 >= 10 && i2 <= 11) {
            return false;
        }
        switch (i) {
            case 1:
                if (i2 != 0) {
                }
                break;
            case 2:
                if (i2 != 6) {
                    switch (i2) {
                    }
                }
                break;
            case 3:
                if (i2 != 6) {
                    switch (i2) {
                    }
                }
                break;
            case 5:
                if (i2 == 1 || i2 == 6) {
                }
                break;
            case 6:
                switch (i2) {
                }
        }
        return false;
    }

    public static boolean a(com.kwai.filedownloader.a aVar) {
        return aVar.v() == 0 || aVar.v() == 3;
    }

    public static boolean b(int i) {
        return i > 0;
    }

    public static boolean b(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || a(i)) {
            return false;
        }
        if (i2 == -2 || i2 == -1) {
            return true;
        }
        switch (i) {
            case 0:
                if (i2 != 10) {
                }
                break;
            case 1:
                if (i2 != 6) {
                }
                break;
            case 2:
            case 3:
                if (i2 == -3 || i2 == 3 || i2 == 5) {
                }
                break;
            case 5:
            case 6:
                if (i2 == 2 || i2 == 5) {
                }
                break;
            case 10:
                if (i2 != 11) {
                }
                break;
            case 11:
                if (i2 != 1) {
                    switch (i2) {
                    }
                }
                break;
        }
        return false;
    }
}
