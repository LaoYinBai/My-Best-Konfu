# @category kfnt
# 在 Ghidra 中定位 mono 图像加载函数并反编译, 找解密插入点
import ghidra.app.decompiler as dec

fm = currentProgram.getFunctionManager()
st = currentProgram.getSymbolTable()

# 1) 按名字找函数
targets = []
interesting = ['open_from_data', 'mono_image_open', 'decrypt', 'image_load', 'mono_image_load']
for f in fm.getFunctions(True):
    n = f.getName().lower()
    if any(k in n for k in interesting):
        targets.append(f)
print('按名字命中的函数: %d' % len(targets))
for t in targets[:40]:
    print('  %s @ %s' % (t.getName(), t.getEntryPoint()))

# 2) 反编译 open_from_data 系列
ifc = dec.DecompInterface()
ifc.openProgram(currentProgram)
for t in targets:
    if 'open_from_data' in t.getName().lower() or 'mono_image_open' in t.getName().lower():
        res = ifc.decompileFunction(t, 180, monitor)
        print('==== %s @ %s ====' % (t.getName(), t.getEntryPoint()))
        if res.decompileCompleted():
            print(res.getDecompiledFunction().getC())
        else:
            print('反编译失败: %s' % res.getErrorMessage())
