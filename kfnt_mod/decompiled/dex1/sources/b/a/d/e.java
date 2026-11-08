package b.a.d;

import androidx.appcompat.widget.ActivityChooserView;
import anet.channel.request.Request;
import b.ac;
import b.ae;
import b.m;
import b.n;
import b.u;
import b.v;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f821a = "([^ \"=]*)";

    /* renamed from: b, reason: collision with root package name */
    private static final String f822b = "\"([^\"]*)\"";

    /* renamed from: c, reason: collision with root package name */
    private static final Pattern f823c = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    private e() {
    }

    public static long a(ae aeVar) {
        return a(aeVar.g());
    }

    public static long a(u uVar) {
        return a(uVar.a("Content-Length"));
    }

    private static long a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static boolean a(ae aeVar, u uVar, ac acVar) {
        for (String str : e(aeVar)) {
            if (!b.a.c.a(uVar.c(str), acVar.b(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(ae aeVar) {
        return b(aeVar.g());
    }

    public static boolean b(u uVar) {
        return c(uVar).contains("*");
    }

    private static Set<String> e(ae aeVar) {
        return c(aeVar.g());
    }

    public static Set<String> c(u uVar) {
        Set<String> setEmptySet = Collections.emptySet();
        int iA = uVar.a();
        Set<String> treeSet = setEmptySet;
        for (int i = 0; i < iA; i++) {
            if ("Vary".equalsIgnoreCase(uVar.a(i))) {
                String strB = uVar.b(i);
                if (treeSet.isEmpty()) {
                    treeSet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : strB.split(IWeiboService.Scope.EMPTY_SCOPE)) {
                    treeSet.add(str.trim());
                }
            }
        }
        return treeSet;
    }

    public static u c(ae aeVar) {
        return a(aeVar.k().a().c(), aeVar.g());
    }

    public static u a(u uVar, u uVar2) {
        Set<String> setC = c(uVar2);
        if (setC.isEmpty()) {
            return new u.a().a();
        }
        u.a aVar = new u.a();
        int iA = uVar.a();
        for (int i = 0; i < iA; i++) {
            String strA = uVar.a(i);
            if (setC.contains(strA)) {
                aVar.a(strA, uVar.b(i));
            }
        }
        return aVar.a();
    }

    public static List<b.h> a(u uVar, String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : uVar.c(str)) {
            int iIndexOf = str2.indexOf(32);
            if (iIndexOf != -1) {
                String strSubstring = str2.substring(0, iIndexOf);
                Matcher matcher = f823c.matcher(str2);
                String strGroup = null;
                String strGroup2 = null;
                while (matcher.find(iIndexOf)) {
                    if (str2.regionMatches(true, matcher.start(1), "realm", 0, 5)) {
                        strGroup = matcher.group(3);
                    } else if (str2.regionMatches(true, matcher.start(1), "charset", 0, 7)) {
                        strGroup2 = matcher.group(3);
                    }
                    if (strGroup != null && strGroup2 != null) {
                        break;
                    }
                    iIndexOf = matcher.end();
                }
                if (strGroup != null) {
                    b.h hVar = new b.h(strSubstring, strGroup);
                    if (strGroup2 != null) {
                        if (strGroup2.equalsIgnoreCase("UTF-8")) {
                            hVar = hVar.a(b.a.c.e);
                        }
                    }
                    arrayList.add(hVar);
                }
            }
        }
        return arrayList;
    }

    public static void a(n nVar, v vVar, u uVar) {
        if (nVar == n.f1261a) {
            return;
        }
        List<m> listA = m.a(vVar, uVar);
        if (listA.isEmpty()) {
            return;
        }
        nVar.a(vVar, listA);
    }

    public static boolean d(ae aeVar) {
        if (aeVar.a().b().equals(Request.Method.HEAD)) {
            return false;
        }
        int iC = aeVar.c();
        return (((iC >= 100 && iC < 200) || iC == 204 || iC == 304) && a(aeVar) == -1 && !"chunked".equalsIgnoreCase(aeVar.b("Transfer-Encoding"))) ? false : true;
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    public static int b(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
