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

    public void executeWrite(Resume r, OutputStream os) throws IOException {
        strategy.doWrite(r, os);
    }

    public Resume executeRead(InputStream is) throws IOException {
            return strategy.doRead(is);
    }
}
