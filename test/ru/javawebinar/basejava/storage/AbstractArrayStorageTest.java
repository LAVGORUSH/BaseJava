package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        Assert.assertEquals(r, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        Resume r = new Resume(NEW_UUID);
        storage.save(r);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(r, storage.get(NEW_UUID));
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        for (int i = 0; i < ArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume("testUuid" + i));
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        Resume r = new Resume(UUID_1);
        storage.save(r);
    }


    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        Resume[] resumes = {RESUME_1, RESUME_3};
        Assert.assertEquals(resumes,storage.getAll());

    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }


    @Test
    public void getAll() {
        Resume[] resumes = {RESUME_1,RESUME_2,RESUME_3};
        Assert.assertEquals(resumes,storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1,storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}