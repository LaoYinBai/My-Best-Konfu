package com.facebook.stetho.dumpapp.plugins;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SharedPreferencesDumperPlugin implements DumperPlugin {
    private static final String NAME = "prefs";
    private static final String XML_SUFFIX = ".xml";
    private final Context mAppContext;

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return NAME;
    }

    public SharedPreferencesDumperPlugin(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public void dump(DumperContext dumperContext) throws DumpUsageException {
        PrintStream stdout = dumperContext.getStdout();
        List<String> argsAsList = dumperContext.getArgsAsList();
        String strRemove = argsAsList.isEmpty() ? "" : argsAsList.remove(0);
        if (strRemove.equals("print")) {
            doPrint(stdout, argsAsList);
        } else if (strRemove.equals("write")) {
            doWrite(argsAsList);
        } else {
            doUsage(stdout);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void doWrite(List<String> list) throws DumpUsageException {
        Iterator<String> it = list.iterator();
        String strNextArg = nextArg(it, "Expected <path>");
        String strNextArg2 = nextArg(it, "Expected <key>");
        Type typeOf = Type.of(nextArg(it, "Expected <type>"));
        if (typeOf == null) {
            throw new DumpUsageException(Type.appendNamesList(new StringBuilder("Usage: prefs write <path> <key> <type> <value>, where type is one of: "), ", ").toString());
        }
        SharedPreferences.Editor editorEdit = getSharedPreferences(strNextArg).edit();
        switch (typeOf) {
            case BOOLEAN:
                editorEdit.putBoolean(strNextArg2, Boolean.valueOf(nextArgValue(it)).booleanValue());
                break;
            case INT:
                editorEdit.putInt(strNextArg2, Integer.valueOf(nextArgValue(it)).intValue());
                break;
            case LONG:
                editorEdit.putLong(strNextArg2, Long.valueOf(nextArgValue(it)).longValue());
                break;
            case FLOAT:
                editorEdit.putFloat(strNextArg2, Float.valueOf(nextArgValue(it)).floatValue());
                break;
            case STRING:
                editorEdit.putString(strNextArg2, nextArgValue(it));
                break;
            case SET:
                putStringSet(editorEdit, strNextArg2, it);
                break;
        }
        editorEdit.commit();
    }

    @Nonnull
    private static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (!it.hasNext()) {
            throw new DumpUsageException(str);
        }
        return it.next();
    }

    @Nonnull
    private static String nextArgValue(Iterator<String> it) throws DumpUsageException {
        return nextArg(it, "Expected <value>");
    }

    @TargetApi(11)
    private static void putStringSet(SharedPreferences.Editor editor, String str, Iterator<String> it) {
        HashSet hashSet = new HashSet();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        editor.putStringSet(str, hashSet);
    }

    private void doPrint(PrintStream printStream, List<String> list) {
        printRecursive(printStream, this.mAppContext.getApplicationInfo().dataDir + "/shared_prefs", "", list.isEmpty() ? "" : list.get(0), list.size() > 1 ? list.get(1) : "");
    }

    private void printRecursive(PrintStream printStream, String str, String str2, String str3, String str4) {
        String[] list;
        File file = new File(str, str2);
        if (file.isFile()) {
            if (str2.endsWith(XML_SUFFIX)) {
                printFile(printStream, str2.substring(0, str2.length() - XML_SUFFIX.length()), str4);
                return;
            }
            return;
        }
        if (!file.isDirectory() || (list = file.list()) == null) {
            return;
        }
        for (int i = 0; i < list.length; i++) {
            String str5 = TextUtils.isEmpty(str2) ? list[i] : str2 + File.separator + list[i];
            if (str5.startsWith(str3)) {
                printRecursive(printStream, str, str5, str3, str4);
            }
        }
    }

    private void printFile(PrintStream printStream, String str, String str2) {
        printStream.println(str + ":");
        for (Map.Entry<String, ?> entry : getSharedPreferences(str).getAll().entrySet()) {
            if (entry.getKey().startsWith(str2)) {
                printStream.println("  " + entry.getKey() + " = " + entry.getValue());
            }
        }
    }

    private void doUsage(PrintStream printStream) {
        printStream.println("Usage: dumpapp prefs <command> [command-options]");
        printStream.println("Usage: dumpapp prefs print [pathPrefix [keyPrefix]]");
        StringBuilder sb = new StringBuilder("       dumpapp prefs ");
        sb.append("write <path> <key> <");
        StringBuilder sbAppendNamesList = Type.appendNamesList(sb, "|");
        sbAppendNamesList.append("> <value>");
        printStream.println(sbAppendNamesList);
        printStream.println();
        printStream.println("dumpapp prefs print: Print all matching values from the shared preferences");
        printStream.println();
        printStream.println("dumpapp prefs write: Writes a value to the shared preferences");
    }

    private SharedPreferences getSharedPreferences(String str) {
        return this.mAppContext.getSharedPreferences(str, 4);
    }

    private enum Type {
        BOOLEAN("boolean"),
        INT(IAdInterListener.AdProdType.PRODUCT_INTERSTITIAL),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        SET("set");

        private final String name;

        Type(String str) {
            this.name = str;
        }

        @Nullable
        public static Type of(String str) {
            for (Type type : values()) {
                if (type.name.equals(str)) {
                    return type;
                }
            }
            return null;
        }

        public static StringBuilder appendNamesList(StringBuilder sb, String str) {
            boolean z = true;
            for (Type type : values()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str);
                }
                sb.append(type.name);
            }
            return sb;
        }
    }
}
