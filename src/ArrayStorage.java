/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++){
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if(storage[i].toString().equals(uuid)){
                storage[i] = null;
                for (int k = i; k < storage.length - 1; k++) {
                    storage[k] = storage[k + 1];
                }
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
        for(int i = 0;  i < storage.length;  i++) {
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
