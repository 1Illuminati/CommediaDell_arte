package org.red.a_.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * 걍 플러그인 폴더까지 적기 귀찮을때 사용
 */
public class A_File extends File {
    public A_File(@NotNull String pathname) {
        super("plugins/CommediaDell_arte/" + pathname);
    }
}
