package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void saveResume(Resume r) {
        int index = -1 - getIndex(r.getUuid());
        for (int i = index; i < size; i++) {
            storage[i + 1] = storage[i];
        }
        storage[index] = r;
        size++;
    }

    public void deleteResume(String uuid) {
        for (int i = getIndex(uuid); i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
