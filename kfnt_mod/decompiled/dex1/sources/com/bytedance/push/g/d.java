package com.bytedance.push.g;

import com.appsflyer.share.Constants;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements Externalizable {

    /* renamed from: a, reason: collision with root package name */
    private String f4820a;

    /* renamed from: b, reason: collision with root package name */
    private String f4821b;

    /* renamed from: c, reason: collision with root package name */
    private e f4822c;

    public d() {
        this.f4820a = "application";
        this.f4821b = "*";
        this.f4822c = new e();
    }

    public d(String str) throws f {
        b(str);
    }

    private void b(String str) throws f {
        int iIndexOf = str.indexOf(47);
        int iIndexOf2 = str.indexOf(59);
        if (iIndexOf < 0 && iIndexOf2 < 0) {
            throw new f("Unable to find a sub type.");
        }
        if (iIndexOf < 0 && iIndexOf2 >= 0) {
            throw new f("Unable to find a sub type.");
        }
        if (iIndexOf >= 0 && iIndexOf2 < 0) {
            this.f4820a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f4821b = str.substring(iIndexOf + 1).trim().toLowerCase(Locale.ENGLISH);
            this.f4822c = new e();
        } else if (iIndexOf < iIndexOf2) {
            this.f4820a = str.substring(0, iIndexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f4821b = str.substring(iIndexOf + 1, iIndexOf2).trim().toLowerCase(Locale.ENGLISH);
            this.f4822c = new e(str.substring(iIndexOf2));
        } else {
            throw new f("Unable to find a sub type.");
        }
        if (!c(this.f4820a)) {
            throw new f("Primary type is invalid.");
        }
        if (!c(this.f4821b)) {
            throw new f("Sub type is invalid.");
        }
    }

    public String a(String str) {
        return this.f4822c.b(str);
    }

    public String toString() {
        return a() + this.f4822c.toString();
    }

    public String a() {
        return this.f4820a + Constants.URL_PATH_DELIMITER + this.f4821b;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(toString());
        objectOutput.flush();
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        try {
            b(objectInput.readUTF());
        } catch (f e) {
            throw new IOException(e.toString());
        }
    }

    private static boolean a(char c2) {
        return c2 > ' ' && c2 < 127 && "()<>@,;:/[]?=\\\"".indexOf(c2) < 0;
    }

    private boolean c(String str) {
        int length = str.length();
        if (length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
