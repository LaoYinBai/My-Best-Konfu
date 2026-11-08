package com.meizu.cloud.pushsdk.c.f;

import android.content.Context;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";

    public static Map a(String str, Context context) throws IOException {
        try {
            c.b(a, "Attempting to retrieve map from: %s", str);
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(str));
            HashMap map = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            c.b(a, " + Retrieved map from file: %s", map);
            return map;
        } catch (IOException | ClassNotFoundException e) {
            c.a(a, " + Exception getting vars map: %s", e.getMessage());
            return null;
        }
    }

    public static boolean a(String str, Map map, Context context) throws IOException {
        try {
            c.b(a, "Attempting to save: %s", map);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            c.b(a, " + Successfully saved KV Pairs to: %s", str);
            return true;
        } catch (IOException e) {
            c.a(a, " + Exception saving vars map: %s", e.getMessage());
            return false;
        }
    }
}
