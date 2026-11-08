import ghidra.app.script.GhidraScript;
import ghidra.app.cmd.function.CreateFunctionCmd;
import ghidra.app.decompiler.*;
import ghidra.program.model.listing.*;
import ghidra.program.model.address.*;
import ghidra.program.model.symbol.*;

public class gh_sym extends GhidraScript {
    public void run() throws Exception {
        SymbolTable st = currentProgram.getSymbolTable();
        FunctionManager fm = currentProgram.getFunctionManager();
        DecompInterface ifc = new DecompInterface();
        ifc.openProgram(currentProgram);

        String[] wants = {"open_from_data", "mono_image_open", "image_open", "mono_image_load"};
        SymbolIterator si = st.getAllSymbols(true);
        java.util.List<Address> hits = new java.util.ArrayList<Address>();
        while (si.hasNext()) {
            Symbol s = si.next();
            String n = s.getName().toLowerCase();
            for (String w : wants) {
                if (n.contains(w)) {
                    hits.add(s.getAddress());
                    println("SYM: " + s.getName() + " @ " + s.getAddress() + " (func? " + (fm.getFunctionAt(s.getAddress()) != null) + ")");
                    break;
                }
            }
        }
        println("命中符号数: " + hits.size());
        for (Address a : hits) {
            Function f = fm.getFunctionAt(a);
            if (f == null) {
                CreateFunctionCmd cmd = new CreateFunctionCmd(a);
                cmd.applyTo(currentProgram, monitor);
                f = fm.getFunctionAt(a);
            }
            if (f != null) {
                DecompileResults r = ifc.decompileFunction(f, 240, monitor);
                println("==== " + f.getName() + " @ " + a + " ====");
                println(r.decompileCompleted() ? r.getDecompiledFunction().getC() : "FAIL " + r.getErrorMessage());
            } else {
                println("无法在 " + a + " 创建函数");
            }
        }
    }
}
