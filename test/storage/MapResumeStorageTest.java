package storage;

import ru.javawebinar.basejava.storage.MapResumeStorage;

import static org.junit.Assert.*;

public class MapResumeStorageTest extends AbstractStorageTest{
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }
}