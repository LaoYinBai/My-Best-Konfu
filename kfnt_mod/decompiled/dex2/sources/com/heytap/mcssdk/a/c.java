package com.heytap.mcssdk.a;

import android.content.Context;
import android.content.Intent;
import com.heytap.mcssdk.PushManager;
import com.heytap.mcssdk.mode.Message;
import com.heytap.mcssdk.utils.CryptoUtil;
import com.heytap.mcssdk.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c implements d {
    public static List<Message> a(Context context, Intent intent) throws NumberFormatException {
        Message messageA;
        if (intent == null) {
            return null;
        }
        int i = 4096;
        try {
            i = Integer.parseInt(CryptoUtil.desDecrypt(intent.getStringExtra(Message.TYPE)));
        } catch (Exception e) {
            LogUtil.e("MessageParser--getMessageByIntent--Exception:" + e.getMessage());
        }
        LogUtil.d("MessageParser--getMessageByIntent--type:" + i);
        ArrayList arrayList = new ArrayList();
        for (d dVar : PushManager.getInstance().getParsers()) {
            if (dVar != null && (messageA = dVar.a(context, i, intent)) != null) {
                arrayList.add(messageA);
            }
        }
        return arrayList;
    }

    public abstract Message a(Intent intent);
}
