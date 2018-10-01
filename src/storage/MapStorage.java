package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r, Object index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get(index);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExistElement(Object index) {
        return storage.containsKey(index);
    }

}
