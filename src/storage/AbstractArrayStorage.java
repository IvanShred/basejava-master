package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume r) {
        int index = getIndex(r.getUuid());
        storage[index] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveResume(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExistElement(String uuid) {
        if (getIndex(uuid) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);

}
