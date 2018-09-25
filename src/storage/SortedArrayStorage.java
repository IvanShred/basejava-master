package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void saveResume(Resume r, int index) {
        int indexForSave = -1 - index;
        System.arraycopy(storage, indexForSave, storage, indexForSave + 1, size - indexForSave);
        storage[indexForSave] = r;
    }

    public void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
