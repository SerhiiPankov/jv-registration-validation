package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null
                || user.getLogin() == null
                || user.getAge() == null
                || user.getPassword() == null) {
            throw new RuntimeException("Can't register User with null parameters");
        }
        if (user.getAge() < MINIMAL_AGE) {
            throw new RuntimeException("Users should be greater than 18yo");
        }
        for (User users : Storage.people) {
            if (users.getLogin().equals(user.getLogin())) {
                throw new RuntimeException("User with same login already exists");
            }
        }
        if (user.getPassword().length() < MINIMAL_LENGTH_PASS) {
            throw new RuntimeException("Password should be 6 characters or more");
        }
        return storageDao.add(user);
    }
}

