package com.epam.service;

import com.epam.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Mock
    private User user;

    @Test
    void getUser() {
        MockitoAnnotations.initMocks(this);
        UserServiceImpl userService = new UserServiceImpl();
        when(user.getUsername()).thenReturn("John");
        User actualUser = userService.getUser(user.getUsername());
        User expectedUser = userService.getUser("John");
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }
}