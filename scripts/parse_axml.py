"""Minimal binary AndroidManifest.xml (AXML) parser: prints package, versionName, versionCode, app label, and string pool."""
import struct
import sys

def parse(path):
    with open(path, 'rb') as f:
        data = f.read()
    pos = 8  # skip RES_XML_TYPE header (type, headerSize, size)
    string_pool = []
    while pos < len(data):
        chunk_type, header_size, chunk_size = struct.unpack_from('<HHI', data, pos)
        if chunk_type == 0x0001:  # RES_STRING_POOL_TYPE
            string_count, style_offset = struct.unpack_from('<II', data, pos + 8)
            flags, strings_start = struct.unpack_from('<II', data, pos + 16)
            is_utf8 = bool(flags & (1 << 8))
            string_offsets = struct.unpack_from('<%dI' % string_count, data, pos + 28)
            base = pos + strings_start
            for off in string_offsets:
                so = base + off
                if is_utf8:
                    # u16len (ignored), u8len, bytes
                    n = 1
                    u8len = data[so + 1]
                    if u8len & 0x80:
                        u8len = ((u8len & 0x7F) << 8) | data[so + 2]
                        n = 2
                    s = data[so + 1 + n: so + 1 + n + u8len].decode('utf-8', 'replace')
                else:
                    u16len = struct.unpack_from('<H', data, so)[0]
                    n = 1
                    if u16len & 0x8000:
                        u16len = ((u16len & 0x7FFF) << 16) | struct.unpack_from('<H', data, so + 2)[0]
                        n = 2
                    s = data[so + 2 * n: so + 2 * n + 2 * u16len].decode('utf-16-le', 'replace')
                string_pool.append(s)
        elif chunk_type == 0x0180:  # RES_XML_RESOURCE_MAP
            pass
        elif chunk_type == 0x0100:  # RES_XML_START_NAMESPACE_TYPE
            pass
        elif chunk_type == 0x0102:  # RES_XML_START_ELEMENT_TYPE
            name_idx, = struct.unpack_from('<I', data, pos + 20)
            attr_start, attr_size, attr_count = struct.unpack_from('<HHH', data, pos + 24)
            attrs = []
            for i in range(attr_count):
                a_off = pos + 16 + attr_start + i * attr_size
                ns, aname, raw_value_idx, atype_field, adata = struct.unpack_from('<IIIIi', data, a_off)
                a_type = (atype_field >> 24) & 0xFF
                val = adata
                if a_type == 0x03 and raw_value_idx != 0xFFFFFFFF:  # string
                    val = string_pool[adata] if 0 <= adata < len(string_pool) else adata
                elif a_type in (0x10, 0x11):  # int / hex
                    val = adata
                elif a_type == 0x12:  # bool
                    val = bool(adata)
                attrs.append((aname, a_type, val))
            elem = string_pool[name_idx] if name_idx < len(string_pool) else '?'
            print('element <{}>'.format(elem))
            for aname, atype, val in attrs:
                n = string_pool[aname] if aname < len(string_pool) else '?'
                print('    {} = {!r}'.format(n, val))
        pos += chunk_size
    return string_pool

if __name__ == '__main__':
    pool = parse(sys.argv[1])
    print('--- string pool ({}) ---'.format(len(pool)))
    for s in pool:
        print(repr(s))
