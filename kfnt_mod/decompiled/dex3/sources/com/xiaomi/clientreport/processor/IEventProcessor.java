package com.xiaomi.clientreport.processor;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IEventProcessor extends c, d {
    String bytesToString(byte[] bArr);

    void setEventMap(HashMap<String, ArrayList<com.xiaomi.clientreport.data.a>> map);

    byte[] stringToBytes(String str);
}
