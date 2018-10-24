package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.strategy.Context;
import ru.javawebinar.basejava.strategy.ObjectStreamStrategy;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    private Context context = new Context();

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        context.setStrategy(new ObjectStreamStrategy());
        context.executeWrite(r, os);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        context.setStrategy(new ObjectStreamStrategy());
        return context.executeRead(is);
    }
}
