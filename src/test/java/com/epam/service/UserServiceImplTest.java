package com.epam.service;

import com.epam.entity.User;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Test
    //simple unit test with mockito framework
    void getUser() {
        UserServiceImpl userService = new UserServiceImpl();
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("John");
        User user1 = userService.getUser(user.getUsername());
        System.out.println(user.getPassword());
        assertEquals(user1.getUsername(), "John");
        verify(user, atLeastOnce()).getPassword();
    }
}