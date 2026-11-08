package universal.tools.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class Profile {
    private static final String COLOR = "COLOR";
    static final String DEFAULT_ID = "__default_profile";
    private static final String HIGH_PRIORITY = "HIGH_PRIORITY";
    private static final String PROFILE = "PROFILE";
    final Integer color;
    final String description;
    final boolean highPriority;
    final String id;
    final String name;

    Profile(String str, SharedPreferences sharedPreferences) {
        if (!sharedPreferences.contains(highPrioritySettingName(str))) {
            Log.w(Profile.class.getName(), "Notification Profile " + str + " is not registered! Using default profile instead.");
            str = DEFAULT_ID;
        }
        String strColorSettingName = colorSettingName(str);
        this.id = str;
        this.name = null;
        this.description = null;
        this.highPriority = sharedPreferences.getBoolean(highPrioritySettingName(str), false);
        this.color = sharedPreferences.contains(strColorSettingName) ? Integer.valueOf(sharedPreferences.getInt(strColorSettingName, 0)) : null;
    }

    Profile(String str, String str2, String str3, boolean z, Integer num) {
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.highPriority = z;
        this.color = num;
    }

    private static int getSoundId(Resources resources, String str, String str2) {
        return resources.getIdentifier(str2, "raw", str);
    }

    private static String highPrioritySettingName(String str) {
        return "PROFILE." + str + "." + HIGH_PRIORITY;
    }

    private static String colorSettingName(String str) {
        return "PROFILE." + str + "." + COLOR;
    }

    Profile saveInSharedPreferences(SharedPreferences.Editor editor) {
        editor.putBoolean(highPrioritySettingName(this.id), this.highPriority);
        if (this.color != null) {
            editor.putInt(colorSettingName(this.id), this.color.intValue());
        } else {
            editor.remove(colorSettingName(this.id));
        }
        return this;
    }

    Profile registerChannel(Context context) {
        String str;
        int soundId;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                NotificationChannel notificationChannel = new NotificationChannel(this.id, this.name, this.highPriority ? 4 : 3);
                notificationChannel.setDescription(this.description != null ? this.description : "");
                Resources resources = context.getResources();
                String packageName = context.getPackageName();
                int soundId2 = getSoundId(resources, packageName);
                if (soundId2 == 0 && !DEFAULT_ID.equals(this.id)) {
                    str = DEFAULT_ID;
                    soundId = getSoundId(resources, packageName, DEFAULT_ID);
                } else {
                    str = this.id;
                    soundId = soundId2;
                }
                if (soundId != 0) {
                    notificationChannel.setSound(Uri.parse(getSoundUri(packageName, str, soundId)), new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
                }
                ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(notificationChannel);
            } catch (Throwable th) {
                Log.w(Profile.class.getName(), "Unable to register notification channel " + this.id + ": " + th.getMessage());
            }
        }
        return this;
    }

    int getSmallIcon(Context context, Resources resources, String str) {
        int identifier;
        if (Build.VERSION.SDK_INT >= 21) {
            identifier = resources.getIdentifier(this.id + "_android5plus", "drawable", str);
        } else {
            identifier = 0;
        }
        if (identifier == 0) {
            identifier = resources.getIdentifier(this.id, "drawable", str);
        }
        return (identifier == 0 && DEFAULT_ID.equals(this.id)) ? Build.VERSION.SDK_INT >= 21 ? android.R.drawable.ic_popup_reminder : context.getApplicationInfo().icon : identifier;
    }

    int getLargeIcon(Resources resources, String str) {
        return resources.getIdentifier(this.id + "_large", "drawable", str);
    }

    int getSoundId(Resources resources, String str) {
        return getSoundId(resources, str, this.id);
    }

    static String getSoundUri(String str, String str2, int i) {
        if (i == 0) {
            return null;
        }
        return "android.resource://" + str + "/raw/" + str2;
    }
}
