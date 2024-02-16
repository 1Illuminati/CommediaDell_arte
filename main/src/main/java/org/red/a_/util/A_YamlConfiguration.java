package org.red.a_.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;

import java.io.*;

/**
 * try catch 두르기 귀찮을때 사용 잘 안사용함
 */
public class A_YamlConfiguration extends YamlConfiguration {
    public void save(@NotNull File file)  {
        try {
            boolean result = file.delete();
            CommediaDell_arte.sendLog(result);
            super.save(file);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void load(@NotNull File file) {
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            if (exception instanceof FileNotFoundException) {
                CommediaDell_arte.sendLog(file.getPath() + " is Not Found");
                return;
            }
            throw new RuntimeException(exception);
        }
    }

    public void loadFileNotFoundError(@NotNull File file) {
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
