package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        try {
            HashSet<Animal> result = new HashSet<>();
            File dir = new File(URLDecoder.decode(pathToAnimals, "UTF-8"));
            File[] content = dir.listFiles();

            for (File f : content) {
                if (f.getName().endsWith(".class")) {
                    myLoader loader = new myLoader();
                    Class clazz = loader.findClass(f.getAbsolutePath());
                    Constructor[] constructor = clazz.getConstructors();

                    for (Constructor c : constructor) {
                        if (c.getModifiers() == Modifier.PUBLIC && c.getParameterCount() == 0 && Animal.class.isAssignableFrom(clazz)) {
                            c.setAccessible(true);
                            result.add((Animal) c.newInstance());
                        }
                    }
                }
            }
            return result;
        }
            catch (Exception e) {
            return null;
        }
    }

    public static class myLoader extends ClassLoader {
        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] buffer = new byte[0];
            try {
                buffer = Files.readAllBytes(Paths.get(name));
            }
            catch (Exception e) {
            }
            Class<?> clazz = defineClass(null, buffer, 0, buffer.length);
            return clazz;
        }
    }
}
