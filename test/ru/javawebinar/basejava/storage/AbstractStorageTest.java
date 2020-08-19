package ru.javawebinar.basejava.storage;

import org.junit.Before;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorageTest {
    protected Storage storage;
    public static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);
    public static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);
    public static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);
    public static final String NEW_UUID = "new_uuid";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

}
