package com.peakxgames.gamelib.unitycall;

import android.app.Activity;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UnityReflection {
    public static String TAG = "UnityReflection";
    private static Class<?> unityPlayer;

    public static void SendMessage(String str, String str2, String str3) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            if (unityPlayer == null) {
                unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            }
            unityPlayer.getMethod("UnitySendMessage", String.class, String.class, String.class).invoke(unityPlayer, str, str2, str3);
        } catch (Exception e) {
            Log.d("TODO", e.toString());
        }
    }

    public static Activity GetUnityActivity() {
        try {
            if (unityPlayer == null) {
                unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            }
            Activity activity = (Activity) unityPlayer.getField("currentActivity").get(null);
            if (activity == null) {
                Log.d(TAG, "Current unity activity is null");
            }
            return activity;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            return null;
        }
    }
}
