package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        Resume[] sortedStorage = getAll();
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if( sortedStorage[j].compareTo(sortedStorage[j+1]) > 0) {
                    Resume tmp = sortedStorage[j];
                    sortedStorage[j] = sortedStorage[j + 1];
                    sortedStorage[j + 1] = tmp;
                }
            }
        }
        return Arrays.binarySearch(sortedStorage, 0, size, searchKey);
    }
}
