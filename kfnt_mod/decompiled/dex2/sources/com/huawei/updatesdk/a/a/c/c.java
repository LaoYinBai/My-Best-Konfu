package com.huawei.updatesdk.a.a.c;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final Map<String, String> a = new HashMap();

    static {
        a.put("android.hardware.audio.output", "1");
        a.put("android.hardware.bluetooth", "2");
        a.put("android.hardware.bluetooth_le", PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
        a.put("android.hardware.camera", PushConstants.PUSH_TYPE_WITHDRAW_NOTIFICATION);
        a.put("android.hardware.camera.any", CampaignEx.CLICKMODE_ON);
        a.put("android.hardware.camera.autofocus", "6");
        a.put("android.hardware.camera.capability.manual_post_processing", "7");
        a.put("android.hardware.camera.flash", "8");
        a.put("android.hardware.camera.front", "9");
        a.put("android.hardware.consumerir", "A");
        a.put("android.hardware.faketouch", "B");
        a.put("android.hardware.fingerprint", "C");
        a.put("android.hardware.location", "D");
        a.put("android.hardware.location.gps", "E");
        a.put("android.hardware.location.network", "F");
        a.put("android.hardware.microphone", "G");
        a.put("android.hardware.nfc", "H");
        a.put("android.hardware.nfc.any", "I");
        a.put("android.hardware.nfc.hce", "J");
        a.put("android.hardware.nfc.hcef", "K");
        a.put("android.hardware.opengles.aep", "L");
        a.put("android.hardware.ram.normal", "M");
        a.put("android.hardware.screen.landscape", "N");
        a.put("android.hardware.screen.portrait", "O");
        a.put("android.hardware.sensor.accelerometer", "P");
        a.put("android.hardware.sensor.barometer", "Q");
        a.put("android.hardware.sensor.compass", "R");
        a.put("android.hardware.sensor.gyroscope", "S");
        a.put("android.hardware.sensor.light", "T");
        a.put("android.hardware.sensor.proximity", "U");
        a.put("android.hardware.sensor.stepcounter", "V");
        a.put("android.hardware.sensor.stepdetector", "W");
        a.put("android.hardware.telephony", "X");
        a.put("android.hardware.telephony.gsm", "Y");
        a.put("android.hardware.telephony.ims", "Z");
        a.put("android.hardware.touchscreen", "a");
        a.put("android.hardware.touchscreen.multitouch", com.huawei.updatesdk.service.d.a.b.a);
        a.put("android.hardware.touchscreen.multitouch.distinct", "c");
        a.put("android.hardware.touchscreen.multitouch.jazzhand", "d");
        a.put("android.hardware.usb.accessory", "e");
        a.put("android.hardware.usb.host", "f");
        a.put("android.hardware.vulkan.compute", "g");
        a.put("android.hardware.vulkan.level", "h");
        a.put("android.hardware.vulkan.version", "i");
        a.put("android.hardware.wifi", "j");
        a.put("android.hardware.wifi.direct", CampaignEx.JSON_KEY_AD_K);
        a.put("android.hardware.wifi.passpoint", NotifyType.LIGHTS);
        a.put("android.software.activities_on_secondary_displays", "m");
        a.put("android.software.app_widgets", "n");
        a.put("android.software.autofill", "o");
        a.put("android.software.backup", TtmlNode.TAG_P);
        a.put("android.software.cant_save_state", CampaignEx.JSON_KEY_AD_Q);
        a.put("android.software.companion_device_setup", CampaignEx.JSON_KEY_AD_R);
        a.put("android.software.connectionservice", NotifyType.SOUND);
        a.put("android.software.cts", "t");
        a.put("android.software.device_admin", "u");
        a.put("android.software.file_based_encryption", NotifyType.VIBRATE);
        a.put("android.software.freeform_window_management", "w");
        a.put("android.software.home_screen", "x");
        a.put("android.software.input_methods", "y");
        a.put("android.software.ipsec_tunnels", "z");
        a.put("android.software.live_wallpaper", "00");
        a.put("android.software.managed_users", HiAnalyticsConstant.KeyAndValue.NUMBER_01);
        a.put("android.software.midi", "02");
        a.put("android.software.picture_in_picture", "03");
        a.put("android.software.print", "04");
        a.put("android.software.secure_lock_screen", "05");
        a.put("android.software.securely_removes_users", "06");
        a.put("android.software.verified_boot", "07");
        a.put("android.software.voice_recognizers", "08");
        a.put("android.software.webview", "09");
        a.put("android.sofware.nfc.beam", "0A");
        a.put("com.huawei.software.features.china", "0B");
        a.put("com.huawei.software.features.full", "0C");
        a.put("com.huawei.system.feature", "0D");
        a.put("com.nxp.mifare", "0E");
        a.put("huawei.android.hardware.stylus", "0F");
        a.put("android.hardware.audio.low_latency", "0G");
        a.put("android.hardware.camera.capability.raw", "0H");
        a.put("android.hardware.camera.external", "0I");
        a.put("android.hardware.telephony.cdma", "0J");
        a.put("android.software.file_based_encryption", "0K");
        a.put("android.software.sip", "0L");
        a.put("android.software.sip.voip", "0M");
        a.put("android.software.vr.mode", "0N");
        a.put("cn.google.services", "0O");
        a.put("com.google.android.feature.services_updater", "0P");
        a.put("com.google.android.feature.ZERO_TOUCH", "0Q");
        a.put("com.huawei.software.features.oversea", "0R");
    }

    public static String a(String str) {
        return a.containsKey(str) ? a.get(str) : str;
    }
}
