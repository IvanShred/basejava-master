package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object index) {
        storage.set((int)index, r);
    }

    @Override
    protected void saveResume(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected Resume getResume( Object index) {
        return storage.get((int)index);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove((int)index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExistElement(Object index) {
        return index != null;
    }
}
