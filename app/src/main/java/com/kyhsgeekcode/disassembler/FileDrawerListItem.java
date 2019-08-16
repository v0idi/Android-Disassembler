package com.kyhsgeekcode.disassembler;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileDrawerListItem {
    String caption;

    public enum DrawerItemType {
        FOLDER,
        ZIP,
        APK,
        NORMAL,
        BINARY,
        PE,
        PE_IL,
        DEX
    }

    ;
    DrawerItemType type;

    public FileDrawerListItem(File file) {
        caption = file.getName();
        if (file.isDirectory()) {
            type = DrawerItemType.FOLDER;
        } else {
            String lower = caption.toLowerCase();
            if (lower.endsWith(".zip"))
                type = DrawerItemType.ZIP;
            else if (lower.endsWith(".apk"))
                type = DrawerItemType.APK;
            else if (lower.endsWith("Assembly-CSharp.dll"))
                type = DrawerItemType.PE_IL;
            else if (lower.endsWith(".exe") || lower.endsWith(".sys") || lower.endsWith(".dll"))
                type = DrawerItemType.PE;
            else if (lower.endsWith(".so") || lower.endsWith(".elf") || lower.endsWith(".o") || lower.endsWith(".bin")
                    || lower.endsWith(".axf") || lower.endsWith(".prx") || lower.endsWith(".puff") || lower.endsWith(".ko") || lower.endsWith(".mod"))
                type = DrawerItemType.BINARY;
            else if (lower.endsWith(".dex"))
                type = DrawerItemType.DEX;
            else
                type = DrawerItemType.NORMAL;
        }
    }

    public boolean IsExpandable() {
        return expandables.contains(type);
    }

    private static final Set<DrawerItemType> expandables = new HashSet<>();

    static {
        expandables.add(DrawerItemType.APK);
        expandables.add(DrawerItemType.ZIP);
        expandables.add(DrawerItemType.FOLDER);
    }
}