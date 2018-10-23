package ru.javawebinar.basejava.strategy;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeWrite (Resume r, OutputStream os) {
        try {
            strategy.doWrite(r, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeRead (InputStream is) {
        try {
            strategy.doRead(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
