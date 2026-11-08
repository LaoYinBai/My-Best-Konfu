package com.facebook.stetho.dumpapp;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Dumper {
    private final Map<String, DumperPlugin> mDumperPlugins;
    private final GlobalOptions mGlobalOptions;
    private final CommandLineParser mParser;

    public Dumper(Iterable<DumperPlugin> iterable) {
        this(iterable, new GnuParser());
    }

    public Dumper(Iterable<DumperPlugin> iterable, CommandLineParser commandLineParser) {
        this.mDumperPlugins = generatePluginMap(iterable);
        this.mParser = commandLineParser;
        this.mGlobalOptions = new GlobalOptions();
    }

    private static Map<String, DumperPlugin> generatePluginMap(Iterable<DumperPlugin> iterable) {
        HashMap map = new HashMap();
        for (DumperPlugin dumperPlugin : iterable) {
            map.put(dumperPlugin.getName(), dumperPlugin);
        }
        return Collections.unmodifiableMap(map);
    }

    public int dump(InputStream inputStream, PrintStream printStream, PrintStream printStream2, String[] strArr) {
        try {
            return doDump(inputStream, printStream, printStream2, strArr);
        } catch (DumpException e) {
            printStream2.println(e.getMessage());
            return 1;
        } catch (DumpappOutputBrokenException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            e3.printStackTrace(printStream2);
            return 1;
        } catch (ParseException e4) {
            printStream2.println(e4.getMessage());
            dumpUsage(printStream2);
            return 1;
        }
    }

    private int doDump(InputStream inputStream, PrintStream printStream, PrintStream printStream2, String[] strArr) throws DumpException, ParseException {
        CommandLine commandLine = this.mParser.parse(this.mGlobalOptions.options, strArr, true);
        if (commandLine.hasOption(this.mGlobalOptions.optionHelp.getOpt())) {
            dumpUsage(printStream);
            return 0;
        }
        if (commandLine.hasOption(this.mGlobalOptions.optionListPlugins.getOpt())) {
            dumpAvailablePlugins(printStream);
            return 0;
        }
        if (!commandLine.getArgList().isEmpty()) {
            dumpPluginOutput(inputStream, printStream, printStream2, commandLine);
            return 0;
        }
        dumpUsage(printStream2);
        return 1;
    }

    private void dumpAvailablePlugins(PrintStream printStream) {
        ArrayList arrayList = new ArrayList();
        Iterator<DumperPlugin> it = this.mDumperPlugins.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        Collections.sort(arrayList);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            printStream.println((String) it2.next());
        }
    }

    private void dumpPluginOutput(InputStream inputStream, PrintStream printStream, PrintStream printStream2, CommandLine commandLine) throws DumpException {
        ArrayList arrayList = new ArrayList(commandLine.getArgList());
        if (arrayList.size() < 1) {
            throw new DumpException("Expected plugin argument");
        }
        String str = (String) arrayList.remove(0);
        DumperPlugin dumperPlugin = this.mDumperPlugins.get(str);
        if (dumperPlugin == null) {
            throw new DumpException("No plugin named '" + str + "'");
        }
        dumperPlugin.dump(new DumperContext(inputStream, printStream, printStream2, this.mParser, arrayList));
    }

    private void dumpUsage(PrintStream printStream) {
        HelpFormatter helpFormatter = new HelpFormatter();
        printStream.println("Usage: dumpapp [options] <plugin> [plugin-options]");
        PrintWriter printWriter = new PrintWriter(printStream);
        try {
            helpFormatter.printOptions(printWriter, helpFormatter.getWidth(), this.mGlobalOptions.options, helpFormatter.getLeftPadding(), helpFormatter.getDescPadding());
        } finally {
            printWriter.flush();
        }
    }
}
