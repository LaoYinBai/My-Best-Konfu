package com.ss.union.game.sdk.feedback.d;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String a = "feedback_show";
    public static final String b = "feedback_mainpage_show";
    public static final String c = "feedback_record_show";
    public static final String d = "feedback_click";
    public static final String e = "submit_feedback_click";
    public static final String f = "submit_message_click";
    public static final String g = "feedback_result";
    public static final String h = "feedback_success";
    public static final String i = "feedback_fail";
    public static final String j = "message_success";
    public static final String k = "message_fail";
    public static final String l = "feedback_error";
    private static final String m = "ohayoo_sdk_feedback";

    public static void a(Map<String, String> map) {
        b(map);
    }

    private static void b(Map<String, String> map) {
        PageStater.onEvent(m, map);
    }
}
