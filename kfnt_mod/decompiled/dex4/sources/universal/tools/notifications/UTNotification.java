package universal.tools.notifications;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class UTNotification {
    final int badgeNumber;
    final List<Button> buttons;
    final int id;
    final int intervalSeconds;
    final String notificationProfile;
    final String provider;
    final String text;
    final String title;
    final long triggerAtSystemTimeMillis;
    final Map<String, String> userData;

    UTNotification(int i) {
        this.provider = null;
        this.triggerAtSystemTimeMillis = 0L;
        this.intervalSeconds = 0;
        this.title = null;
        this.text = null;
        this.id = i;
        this.userData = null;
        this.notificationProfile = null;
        this.badgeNumber = -1;
        this.buttons = null;
    }

    UTNotification(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.provider = jSONObject.has("provider") ? jSONObject.getString("provider") : null;
        this.triggerAtSystemTimeMillis = jSONObject.has("triggerAtSystemTimeMillis") ? jSONObject.getLong("triggerAtSystemTimeMillis") : 0L;
        this.intervalSeconds = jSONObject.has("intervalSeconds") ? jSONObject.getInt("intervalSeconds") : 0;
        this.title = jSONObject.has("title") ? jSONObject.getString("title") : null;
        this.text = jSONObject.has("text") ? jSONObject.getString("text") : null;
        this.id = jSONObject.has(AgooConstants.MESSAGE_ID) ? jSONObject.getInt(AgooConstants.MESSAGE_ID) : 0;
        this.userData = jSONObject.has("userData") ? userDataJsonToHashMap(jSONObject.getJSONObject("userData")) : null;
        this.notificationProfile = jSONObject.has("notificationProfile") ? jSONObject.getString("notificationProfile") : null;
        this.badgeNumber = jSONObject.has("badgeNumber") ? jSONObject.getInt("badgeNumber") : -1;
        this.buttons = jSONObject.has("buttons") ? buttonsJsonToListOfButtons(jSONObject.getJSONArray("buttons")) : null;
    }

    UTNotification(long j, int i, String str, String str2, int i2, String str3, String str4, int i3, String str5) throws JSONException {
        this(j, i, str, str2, i2, userDataJsonToHashMap(str3), str4, i3, str5);
    }

    private UTNotification(long j, int i, String str, String str2, int i2, Map<String, String> map, String str3, int i3, String str4) throws JSONException {
        this(null, j, i, str, str2, i2, map, str3, i3, buttonsJsonToListOfButtons(str4));
    }

    UTNotification(long j, int i, String str, String str2, int i2, Map<String, String> map, String str3, int i3, List<Button> list) {
        this(null, j, i, str, str2, i2, map, str3, i3, list);
    }

    UTNotification(String str, long j, int i, String str2, String str3, int i2, Map<String, String> map, String str4, int i3, List<Button> list) {
        this.provider = str;
        this.title = str2;
        this.text = str3;
        this.id = i2;
        this.userData = map;
        this.notificationProfile = str4;
        this.badgeNumber = i3;
        this.triggerAtSystemTimeMillis = j;
        this.intervalSeconds = i;
        this.buttons = list;
    }

    private static JSONObject userDataToJson(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    private static HashMap<String, String> userDataJsonToHashMap(String str) throws JSONException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return userDataJsonToHashMap(new JSONObject(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashMap<String, String> userDataJsonToHashMap(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject.getString(next));
        }
        return map;
    }

    private static List<Button> buttonsJsonToListOfButtons(String str) throws JSONException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return buttonsJsonToListOfButtons(new JSONArray(str));
    }

    private static List<Button> buttonsJsonToListOfButtons(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(new Button(jSONObject.getString("title"), jSONObject.has("userData") ? userDataJsonToHashMap(jSONObject.getJSONObject("userData")) : null));
        }
        return arrayList;
    }

    public String toString() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            if (isScheduled()) {
                jSONObject.put("triggerAtSystemTimeMillis", this.triggerAtSystemTimeMillis);
            }
            if (isRepeated()) {
                jSONObject.put("intervalSeconds", this.intervalSeconds);
            }
            if (isPush()) {
                jSONObject.put("provider", this.provider);
            }
            if (this.title != null) {
                jSONObject.put("title", this.title);
            }
            if (this.text != null) {
                jSONObject.put("text", this.text);
            }
            jSONObject.put(AgooConstants.MESSAGE_ID, this.id);
            if (this.userData != null && !this.userData.isEmpty()) {
                jSONObject.put("userData", userDataToJson(this.userData));
            }
            if (this.notificationProfile != null) {
                jSONObject.put("notificationProfile", this.notificationProfile);
            }
            jSONObject.put("badgeNumber", this.badgeNumber);
            if (hasButtons()) {
                jSONObject.put("buttons", buttonsJson());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    boolean isScheduled() {
        return this.triggerAtSystemTimeMillis > 0;
    }

    boolean isRepeated() {
        return this.intervalSeconds > 0;
    }

    private boolean isPush() {
        return !TextUtils.isEmpty(this.provider);
    }

    private boolean hasButtons() {
        return (this.buttons == null || this.buttons.isEmpty()) ? false : true;
    }

    private JSONArray buttonsJson() throws JSONException {
        if (!hasButtons()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Button button : this.buttons) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", button.title);
            if (button.userData != null) {
                jSONObject.put("userData", userDataToJson(button.userData));
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    static class Button {
        final String title;
        final Map<String, String> userData;

        Button(String str, Map<String, String> map) {
            this.title = str;
            this.userData = map;
        }

        Button(String str, JSONObject jSONObject) throws JSONException {
            this(str, UTNotification.userDataJsonToHashMap(jSONObject));
        }
    }
}
