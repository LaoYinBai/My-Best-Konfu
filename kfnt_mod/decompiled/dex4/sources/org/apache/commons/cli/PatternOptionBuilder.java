package org.apache.commons.cli;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class PatternOptionBuilder {
    public static final Class CLASS_VALUE;
    public static final Class DATE_VALUE;
    public static final Class EXISTING_FILE_VALUE;
    public static final Class FILES_VALUE;
    public static final Class FILE_VALUE;
    public static final Class NUMBER_VALUE;
    public static final Class OBJECT_VALUE;
    public static final Class STRING_VALUE;
    public static final Class URL_VALUE;
    static /* synthetic */ Class array$Ljava$io$File;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$io$FileInputStream;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$Number;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$net$URL;
    static /* synthetic */ Class class$java$util$Date;

    public static boolean isValueCode(char c) {
        return c == '@' || c == ':' || c == '%' || c == '+' || c == '#' || c == '<' || c == '>' || c == '*' || c == '/' || c == '!';
    }

    static {
        Class clsClass$;
        Class clsClass$2;
        Class clsClass$3;
        Class clsClass$4;
        Class clsClass$5;
        Class clsClass$6;
        Class clsClass$7;
        Class clsClass$8;
        Class clsClass$9;
        if (class$java$lang$String == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        } else {
            clsClass$ = class$java$lang$String;
        }
        STRING_VALUE = clsClass$;
        if (class$java$lang$Object == null) {
            clsClass$2 = class$("java.lang.Object");
            class$java$lang$Object = clsClass$2;
        } else {
            clsClass$2 = class$java$lang$Object;
        }
        OBJECT_VALUE = clsClass$2;
        if (class$java$lang$Number == null) {
            clsClass$3 = class$("java.lang.Number");
            class$java$lang$Number = clsClass$3;
        } else {
            clsClass$3 = class$java$lang$Number;
        }
        NUMBER_VALUE = clsClass$3;
        if (class$java$util$Date == null) {
            clsClass$4 = class$("java.util.Date");
            class$java$util$Date = clsClass$4;
        } else {
            clsClass$4 = class$java$util$Date;
        }
        DATE_VALUE = clsClass$4;
        if (class$java$lang$Class == null) {
            clsClass$5 = class$("java.lang.Class");
            class$java$lang$Class = clsClass$5;
        } else {
            clsClass$5 = class$java$lang$Class;
        }
        CLASS_VALUE = clsClass$5;
        if (class$java$io$FileInputStream == null) {
            clsClass$6 = class$("java.io.FileInputStream");
            class$java$io$FileInputStream = clsClass$6;
        } else {
            clsClass$6 = class$java$io$FileInputStream;
        }
        EXISTING_FILE_VALUE = clsClass$6;
        if (class$java$io$File == null) {
            clsClass$7 = class$("java.io.File");
            class$java$io$File = clsClass$7;
        } else {
            clsClass$7 = class$java$io$File;
        }
        FILE_VALUE = clsClass$7;
        if (array$Ljava$io$File == null) {
            clsClass$8 = class$("[Ljava.io.File;");
            array$Ljava$io$File = clsClass$8;
        } else {
            clsClass$8 = array$Ljava$io$File;
        }
        FILES_VALUE = clsClass$8;
        if (class$java$net$URL == null) {
            clsClass$9 = class$("java.net.URL");
            class$java$net$URL = clsClass$9;
        } else {
            clsClass$9 = class$java$net$URL;
        }
        URL_VALUE = clsClass$9;
    }

    static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static Object getValueClass(char c) {
        if (c == '#') {
            return DATE_VALUE;
        }
        if (c == '%') {
            return NUMBER_VALUE;
        }
        if (c == '/') {
            return URL_VALUE;
        }
        if (c == ':') {
            return STRING_VALUE;
        }
        if (c == '<') {
            return EXISTING_FILE_VALUE;
        }
        if (c == '>') {
            return FILE_VALUE;
        }
        if (c == '@') {
            return OBJECT_VALUE;
        }
        switch (c) {
            case '*':
                return FILES_VALUE;
            case '+':
                return CLASS_VALUE;
            default:
                return null;
        }
    }

    public static Options parsePattern(String str) {
        Options options = new Options();
        Object valueClass = null;
        int i = 0;
        char c = ' ';
        boolean z = false;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i);
            if (!isValueCode(cCharAt)) {
                if (c != ' ') {
                    OptionBuilder.hasArg(valueClass != null);
                    OptionBuilder.isRequired(z);
                    OptionBuilder.withType(valueClass);
                    options.addOption(OptionBuilder.create(c));
                    valueClass = null;
                    z = false;
                }
                c = cCharAt;
            } else if (cCharAt == '!') {
                z = true;
            } else {
                valueClass = getValueClass(cCharAt);
            }
            i++;
        }
        if (c != ' ') {
            OptionBuilder.hasArg(valueClass != null);
            OptionBuilder.isRequired(z);
            OptionBuilder.withType(valueClass);
            options.addOption(OptionBuilder.create(c));
        }
        return options;
    }
}
