package storage;

import ru.javawebinar.basejava.storage.MapUuidStorage;

import static org.junit.Assert.*;

public class MapUuidStorageTest extends AbstractStorageTest{
    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }
}