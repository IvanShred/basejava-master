/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++){
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
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
                size--;
                for (int k = i; k < storage.length - 1; k++) {
                    storage[k] = storage[k + 1];
                }
                storage[storage.length - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageNew = new Resume[size];
        for(int i = 0;  i < size;  i++) {
            storageNew[i] = storage[i];
        }
        return storageNew;
    }

    int size() {
        return size;
    }
}
