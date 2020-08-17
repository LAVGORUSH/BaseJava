package ru.javawebinar.basejava.exception;

public class AlreadyExistStorageException extends StorageException {
    public AlreadyExistStorageException(String message) {
        super(message);
    }
}
