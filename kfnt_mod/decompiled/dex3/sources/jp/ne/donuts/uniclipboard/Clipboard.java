package jp.ne.donuts.uniclipboard;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import com.unity3d.player.UnityPlayer;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Clipboard {
    public static void setText(final String str) {
        final Activity activity = UnityPlayer.currentActivity;
        activity.runOnUiThread(new Runnable() { // from class: jp.ne.donuts.uniclipboard.Clipboard.1
            @Override // java.lang.Runnable
            public void run() {
                ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(new ClipData(new ClipDescription("text_data", new String[]{"text/uri-list"}), new ClipData.Item(str)));
            }
        });
    }

    public static String getText() {
        final Activity activity = UnityPlayer.currentActivity;
        FutureTask futureTask = new FutureTask(new Callable<String>() { // from class: jp.ne.donuts.uniclipboard.Clipboard.2
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                ClipData primaryClip = ((ClipboardManager) activity.getSystemService("clipboard")).getPrimaryClip();
                return primaryClip != null ? primaryClip.getItemAt(0).getText().toString() : "";
            }
        });
        activity.runOnUiThread(futureTask);
        try {
            return (String) futureTask.get();
        } catch (Exception unused) {
            return "";
        }
    }
}
