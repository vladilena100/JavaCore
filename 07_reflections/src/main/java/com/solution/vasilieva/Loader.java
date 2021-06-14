package com.solution.vasilieva;

import com.nixsolutions.ppp.reflection.PathClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс имплементирует и реализует методы интерфейса PathClassLoader
 *
 * @author Vasilieva Vladilena
 * @since 13.06.21
 */

public class Loader extends ClassLoader implements PathClassLoader {

    private static final Logger LOG = LogManager.getLogger(Loader.class);

    private Path path;

    @Override
    public Path getPath() {
        return null;
    }

    @Override
    public void setPath(Path path) {

    }

    final String rasshirenie = ".class";
    final char razdilitel = '.';
    final int razmer = 2048;
    final int start = 0;

    /**
     * Метод загружает класс с заданным именем из указанной в path директории.
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.endsWith(rasshirenie)) {
            name = name.substring(0, name.lastIndexOf(razdilitel));
        }
        name = name.replace('.', File.separatorChar)
                .concat(rasshirenie);
        String nameWithRasshirenie = path.toString() + File.separatorChar + name;
        try(FileInputStream input = new FileInputStream(nameWithRasshirenie)) {
            ByteArrayOutputStream res = new ByteArrayOutputStream();
            byte[] buffer = new byte[razmer];
            int value;
            while ((value = input.read(buffer)) != 1) {
                res.write(buffer, start, value);
            }
            return defineClass(null, res.toByteArray(), start, res.size());
        } catch (IOException e) {
            LOG.error(e);
            throw new ClassNotFoundException(name, e);
        }
    }
}
