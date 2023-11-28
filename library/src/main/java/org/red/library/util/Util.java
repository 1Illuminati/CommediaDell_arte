package org.red.library.util;

import java.util.ArrayList;
import java.util.List;

public final class Util {
    private Util() {
        throw new UnsupportedOperationException();
    }

    public static List<String> removeStringNotStartWith(List<String> list, String string) {
        if (list == null) return null;
        List<String> copyList = new ArrayList<>(list);
        list.stream().filter(s -> !s.startsWith(string)).forEach(copyList::remove);
        return copyList;
    }
}
