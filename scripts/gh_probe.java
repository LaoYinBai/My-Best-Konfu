import ghidra.app.script.GhidraScript;
import ghidra.app.decompiler.*;
import ghidra.program.model.listing.*;
import ghidra.program.model.address.*;
import ghidra.program.model.symbol.*;

public class gh_probe extends GhidraScript {
    void probe(long addr) {
        Address a = toAddr(addr);
        ReferenceIterator ri = currentProgram.getReferenceManager().getReferencesTo(a);
        int c = 0;
        while (ri.hasNext() && c < 10) {
            Reference r = ri.next();
            Address from = r.getFromAddress();
            Function f = getFunctionContaining(from);
            println("REF->0x" + Long.toHexString(addr) + "  from 0x" + from.toString() +
                    "  type=" + r.getReferenceType() +
                    "  func=" + (f != null ? f.getName() + "@0x" + f.getEntryPoint().toString() : "?"));
            c++;
        }
        if (c == 0) println("REF->0x" + Long.toHexString(addr) + "  (无引用)");
    }
    public void run() throws Exception {
        FunctionManager fm = currentProgram.getFunctionManager();
        println("函数总数: " + fm.getFunctionCount());
        SymbolTable st = currentProgram.getSymbolTable();
        int sc = 0; SymbolIterator si = st.getAllSymbols(true);
        while (si.hasNext()) { si.next(); sc++; }
        println("符号总数: " + sc);
        // 特征地址探测
        long[] probes = {0x7fb48L, 0x4f7fbeL, 0x4e80f4L, 0x4e810cL, 0x257ddL, 0x2582bL, 0x282dddL};
        for (long p : probes) probe(p);
        // 找包含 0x4D5A 立即数的指令 (MZ检查)
        AddressSetView mem = currentProgram.getMemory().getLoadedAndInitializedAddressSet();
        InstructionIterator ii = currentProgram.getListing().getInstructions(mem, true);
        int mz = 0;
        StringBuilder sb = new StringBuilder();
        while (ii.hasNext() && mz < 20) {
            Instruction i = ii.next();
            for (int k = 0; k < i.getNumOperands(); k++) {
                if (i.getScalar(k) != null && i.getScalar(k).getUnsignedValue() == 0x4D5A) {
                    Function f = getFunctionContaining(i.getAddress());
                    sb.append(String.format("  0x%s %s  func=%s%n", i.getAddress(), i,
                        f != null ? f.getName() : "?"));
                    mz++;
                    break;
                }
            }
        }
        println("0x4D5A立即数指令(" + mz + "):");
        println(sb.toString());
    }
}
