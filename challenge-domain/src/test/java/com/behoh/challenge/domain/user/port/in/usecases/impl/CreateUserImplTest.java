package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.out.SaveUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateUserImplTest {

    @Mock
    private SaveUserPort saveUserPort;

    @InjectMocks
    private CreateUserImpl createUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCallSaveUserWithCorrectUser() {
        // given
        var user = new User(null, "user");

        // when
        createUser.create(user);

        // then
        verify(saveUserPort, times(1)).saveUser(user);
    }

}