package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract void clear();

    @Override
    public void update(Resume r) {
        if (isExistElement(r.getUuid())) {
            updateResume(r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if (!isExistElement(r.getUuid())) {
            saveResume(r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        if (isExistElement(uuid)) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        if (isExistElement(uuid)) {
            deleteResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract boolean isExistElement(String uuid);

    protected abstract void updateResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);
}
