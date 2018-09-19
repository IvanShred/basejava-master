/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int indexNull = 0;
        int i = 0;
        while (i < storage.length){
            if (storage[i] == null) {
                indexNull = i;
                break;
            }
            i++;
        }
        storage[indexNull] = r;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].toString().equals(uuid)) {
                    resume = storage[i];
                    break;
                }
            }
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if(storage[i].toString().equals(uuid)){
                storage[i] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int lengthStorageWithoutNull = 0;
        for (int i = 0; i < storage.length; i++) {
            if(storage[i] != null){
                lengthStorageWithoutNull++;
            }
        }
        Resume[] storageNew = new Resume[lengthStorageWithoutNull];
        int j = 0;
        for(int i = 0;  i < storage.length;  i++)
        {
            if (storage[i] != null) {
                storageNew[j] = storage[i];
                j++;
            }
        }
        return storageNew;
    }

    int size() {
        Resume[] storageWithOutNull = getAll();
        return storageWithOutNull.length;
    }
}
