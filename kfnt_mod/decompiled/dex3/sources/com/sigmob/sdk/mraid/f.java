package com.sigmob.sdk.mraid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.token.TokenConstants;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f {
    private static final String[] a = {"yyyy-MM-dd'T'HH:mm:ssZZZZZ", "yyyy-MM-dd'T'HH:mmZZZZZ"};

    interface a {
    }

    private String a(int i) {
        switch (i) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                throw new IllegalArgumentException("invalid day of week " + i);
        }
    }

    private Date a(String str) {
        Date date;
        Date date2 = null;
        for (String str2 : a) {
            try {
                date = new SimpleDateFormat(str2, Locale.US).parse(str);
            } catch (ParseException unused) {
            }
            if (date != null) {
                return date;
            }
            date2 = date;
        }
        return date2;
    }

    private Map<String, Object> a(Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (!map.containsKey(TokenConstants.ERROR_MSG) || !map.containsKey(PointCategory.START)) {
            throw new IllegalArgumentException("Missing start and description fields");
        }
        map2.put(DBDefinition.TITLE, map.get(TokenConstants.ERROR_MSG));
        if (!map.containsKey(PointCategory.START) || map.get(PointCategory.START) == null) {
            throw new IllegalArgumentException("Invalid calendar event: start is null.");
        }
        Date dateA = a(map.get(PointCategory.START));
        if (dateA == null) {
            throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
        }
        map2.put("beginTime", Long.valueOf(dateA.getTime()));
        if (map.containsKey("end") && map.get("end") != null) {
            Date dateA2 = a(map.get("end"));
            if (dateA2 == null) {
                throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
            }
            map2.put("endTime", Long.valueOf(dateA2.getTime()));
        }
        if (map.containsKey(MsgConstant.KEY_LOCATION_PARAMS)) {
            map2.put("eventLocation", map.get(MsgConstant.KEY_LOCATION_PARAMS));
        }
        if (map.containsKey("summary")) {
            map2.put(TokenConstants.ERROR_MSG, map.get("summary"));
        }
        if (map.containsKey("transparency")) {
            map2.put("availability", Integer.valueOf(map.get("transparency").equals("transparent") ? 1 : 0));
        }
        map2.put("rrule", b(map));
        return map2;
    }

    private String b(int i) {
        if (i == 0 || i < -31 || i > 31) {
            throw new IllegalArgumentException("invalid day of month " + i);
        }
        return "" + i;
    }

    private String b(String str) throws NumberFormatException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[7];
        String[] strArrSplit = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        for (String str2 : strArrSplit) {
            int i = Integer.parseInt(str2);
            if (i == 7) {
                i = 0;
            }
            if (!zArr[i]) {
                sb.append(a(i) + Constants.ACCEPT_TIME_SEPARATOR_SP);
                zArr[i] = true;
            }
        }
        if (strArrSplit.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String b(Map<String, String> map) throws NumberFormatException {
        String strC;
        StringBuilder sb;
        String str;
        String string;
        StringBuilder sb2 = new StringBuilder();
        if (map.containsKey("frequency")) {
            String str2 = map.get("frequency");
            int i = map.containsKey("interval") ? Integer.parseInt(map.get("interval")) : -1;
            if ("daily".equals(str2)) {
                sb2.append("FREQ=DAILY;");
                if (i != -1) {
                    string = "INTERVAL=" + i + ";";
                    sb2.append(string);
                }
            } else {
                if ("weekly".equals(str2)) {
                    sb2.append("FREQ=WEEKLY;");
                    if (i != -1) {
                        sb2.append("INTERVAL=" + i + ";");
                    }
                    if (map.containsKey("daysInWeek")) {
                        strC = b(map.get("daysInWeek"));
                        if (strC == null) {
                            throw new IllegalArgumentException("invalid ");
                        }
                        sb = new StringBuilder();
                        str = "BYDAY=";
                        sb.append(str);
                        sb.append(strC);
                        sb.append(";");
                        string = sb.toString();
                    }
                } else {
                    if (!"monthly".equals(str2)) {
                        throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
                    }
                    sb2.append("FREQ=MONTHLY;");
                    if (i != -1) {
                        sb2.append("INTERVAL=" + i + ";");
                    }
                    if (map.containsKey("daysInMonth")) {
                        strC = c(map.get("daysInMonth"));
                        if (strC == null) {
                            throw new IllegalArgumentException();
                        }
                        sb = new StringBuilder();
                        str = "BYMONTHDAY=";
                        sb.append(str);
                        sb.append(strC);
                        sb.append(";");
                        string = sb.toString();
                    }
                }
                sb2.append(string);
            }
        }
        return sb2.toString();
    }

    private String c(String str) throws NumberFormatException {
        StringBuilder sb = new StringBuilder();
        boolean[] zArr = new boolean[63];
        String[] strArrSplit = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        for (String str2 : strArrSplit) {
            int i = Integer.parseInt(str2);
            int i2 = i + 31;
            if (!zArr[i2]) {
                sb.append(b(i) + Constants.ACCEPT_TIME_SEPARATOR_SP);
                zArr[i2] = true;
            }
        }
        if (strArrSplit.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static boolean c(Context context) {
        return false;
    }

    static boolean d(Context context) {
        return false;
    }

    void a(Context context, String str, a aVar) throws b {
        if (c(context)) {
            return;
        }
        SigmobLog.e("Error downloading file - the device does not have an SD card mounted, or the Android permission is not granted.");
        throw new b("Error downloading file  - the device does not have an SD card mounted, or the Android permission is not granted.");
    }

    void a(Context context, Map<String, String> map) throws b {
        if (!d(context)) {
            SigmobLog.e("unsupported action createCalendarEvent for devices pre-ICS");
            throw new b("Action is unsupported on this device (need Android version Ice Cream Sandwich or above)");
        }
        try {
            Map<String, Object> mapA = a(map);
            Intent type = new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.item/event");
            for (String str : mapA.keySet()) {
                Object obj = mapA.get(str);
                if (obj instanceof Long) {
                    type.putExtra(str, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    type.putExtra(str, ((Integer) obj).intValue());
                } else {
                    type.putExtra(str, (String) obj);
                }
            }
            type.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(type);
        } catch (ActivityNotFoundException unused) {
            SigmobLog.e("no calendar app installed");
            throw new b("Action is unsupported on this device - no calendar app installed");
        } catch (IllegalArgumentException e) {
            SigmobLog.e("create calendar: invalid parameters " + e.getMessage());
            throw new b(e);
        } catch (Exception e2) {
            SigmobLog.e("could not create calendar event");
            throw new b(e2);
        }
    }

    boolean a(Activity activity, View view) {
        return false;
    }

    boolean a(Context context) {
        return false;
    }

    boolean b(Context context) {
        return false;
    }
}
