package storage;

import model.Resume;

public abstract class AbstractStorage implements Storage{

    @Override
    public abstract void clear();

    @Override
    public abstract void update(Resume r);

    @Override
    public abstract void save(Resume r);

    @Override
    public abstract Resume get(String uuid);

    @Override
    public abstract void delete(String uuid);

    @Override
    public abstract Object getAll();

    @Override
    public abstract int size();
}
