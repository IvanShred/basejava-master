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
    protected void updateResume(Resume r) {
        int index = getIndex(r.getUuid());
        storage.set(index, r);
    }

    @Override
    protected void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage.get(index);
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExistElement(String uuid) {
        return getIndex(uuid) != -1;
    }
}
