import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size , null);
        size = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.toString())== -1 && size < storage.length) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume already present or array full");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index!= -1) {
            return storage[index];
        } else {
            System.out.println("Resume not present");
        }
        return null;
    }

    public void update(Resume resume){
        int index = getIndex(resume.toString());
        if (index!= -1) {
                storage[index] = resume;
        } else {
            System.out.println("Resume not present");
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid)!= -1) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else {
            System.out.println("Resume not present");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid){
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
