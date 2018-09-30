package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract void clear();

    @Override
    public void update(Resume r) {
        Object index = getIndexElement(r.getUuid());
        updateResume(r, index);
    }

    @Override
    public void save(Resume r) {
        Object index = getIndexElementForSave(r.getUuid());
        saveResume(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getIndexElement(uuid);
        return getResume(uuid, index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndexElement(uuid);
        deleteResume(uuid, index);
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected Object getIndexElement(String uuid) {
        Object indexElement = getIndex(uuid);
        if (isExistElement(uuid)) {
            return indexElement;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected Object getIndexElementForSave(String uuid){
        Object indexElement = getIndex(uuid);
        if (isExistElement(uuid)) {
            throw new ExistStorageException(uuid);
        } else {
            return indexElement;
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExistElement(String uuid);

    protected abstract void updateResume(Resume r, Object index);

    protected abstract void saveResume(Resume r, Object index);

    protected abstract Resume getResume(String uuid, Object index);

    protected abstract void deleteResume(String uuid, Object index);
}
