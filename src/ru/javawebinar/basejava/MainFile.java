package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.DirectoryIsEmptyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recursionSearch("C:\\Users\\kukhtaIA\\IdeaProjects\\basejava\\src");
    }

    public static void recursionSearch(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files != null) {
                    if (files[i].isDirectory()) {
                        System.out.println(files[i].getAbsolutePath());
                        recursionSearch(files[i].getPath());
                    } else {
                        for (File f : files) {
                            System.out.println(f.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            throw new DirectoryIsEmptyException("directory " + file.getAbsolutePath() + " is empty");
        }
    }
}
