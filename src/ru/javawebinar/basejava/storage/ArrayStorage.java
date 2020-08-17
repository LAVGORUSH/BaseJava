package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.AlreadyExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        checkOverflow();
        checkExist(r.getUuid());
        storage[size++] = r;
    }

    public void update(Resume r) {
        checkNotExist(r.getUuid());
        storage[getIndex(r.getUuid())] = r;
    }

    public Resume get(String uuid) {
        return checkNotExist(uuid) ? null : storage[getIndex(uuid)];
    }

    public void delete(String uuid) {
        checkNotExist(uuid);
        int index = getIndex(uuid);
        System.arraycopy(storage, index + 1, storage, index, size - index + 1);
        storage[size--] = null;
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkExist(String uuid) {
        int index = getIndex(uuid);
        try {
            if (index != -1) {
                throw new AlreadyExistStorageException("Resume with uuid = " + uuid + " is already exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private boolean checkNotExist(String uuid) {
        int index = getIndex(uuid);
        try {
            if (index == -1) {
                throw new NotExistStorageException("Resume with uuid = " + uuid + " is not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private boolean checkOverflow() {
        try {
            if (size > storage.length) {
                throw new StorageException("Storage overflow");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }
}
