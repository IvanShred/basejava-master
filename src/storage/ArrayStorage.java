package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void saveResume(Resume r) {
        storage[size] = r;
        size++;
    }

    public void deleteResume(String uuid) {
        storage[getIndex(uuid)] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
