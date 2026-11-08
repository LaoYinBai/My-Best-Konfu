package com.google.android.gms.internal.measurement;

import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzln {
    static String zza(zzll zzllVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzllVar, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzml.zza(zzjd.zzm((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzjd) {
            sb.append(": \"");
            sb.append(zzml.zza((zzjd) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzke) {
            sb.append(" {");
            zzd((zzke) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, DomainCampaignEx.LOOPBACK_KEY, entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    private static void zzd(zzll zzllVar, StringBuilder sb, int i) throws SecurityException {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzllVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strConcat = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1, strSubstring.length() - 4)));
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(strConcat), zzke.zzbH(method2, zzllVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strConcat2 = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1, strSubstring.length() - 3)));
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(strConcat2), zzke.zzbH(method3, zzllVar, new Object[0]));
                }
            }
            if (((Method) map2.get("set".concat(String.valueOf(strSubstring)))) != null && (!strSubstring.endsWith("Bytes") || !map.containsKey("get".concat(String.valueOf(strSubstring.substring(0, strSubstring.length() - 5)))))) {
                String strConcat3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1)));
                Method method4 = (Method) map.get("get".concat(String.valueOf(strSubstring)));
                Method method5 = (Method) map.get("has".concat(String.valueOf(strSubstring)));
                if (method4 != null) {
                    Object objZzbH = zzke.zzbH(method4, zzllVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzbH instanceof Boolean) {
                            if (((Boolean) objZzbH).booleanValue()) {
                                zzb(sb, i, zzc(strConcat3), objZzbH);
                            }
                        } else if (objZzbH instanceof Integer) {
                            if (((Integer) objZzbH).intValue() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbH);
                            }
                        } else if (objZzbH instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) objZzbH).floatValue()) != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbH);
                            }
                        } else if (!(objZzbH instanceof Double)) {
                            if (objZzbH instanceof String) {
                                zEquals = objZzbH.equals("");
                            } else if (objZzbH instanceof zzjd) {
                                zEquals = objZzbH.equals(zzjd.zzb);
                            } else if (objZzbH instanceof zzll) {
                                if (objZzbH != ((zzll) objZzbH).zzbO()) {
                                    zzb(sb, i, zzc(strConcat3), objZzbH);
                                }
                            } else if (!(objZzbH instanceof Enum) || ((Enum) objZzbH).ordinal() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbH);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzc(strConcat3), objZzbH);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) objZzbH).doubleValue()) != 0) {
                            zzb(sb, i, zzc(strConcat3), objZzbH);
                        }
                    } else if (((Boolean) zzke.zzbH(method5, zzllVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(strConcat3), objZzbH);
                    }
                }
            }
        }
        if (zzllVar instanceof zzkb) {
            zzjv zzjvVar = ((zzkb) zzllVar).zza;
            throw null;
        }
        zzmo zzmoVar = ((zzke) zzllVar).zzc;
        if (zzmoVar != null) {
            zzmoVar.zzg(sb, i);
        }
    }
}
