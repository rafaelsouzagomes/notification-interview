package com.interview.notification.validations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.UserRepository;

class ValidatorUserCostumerTest {

    @InjectMocks
    private ValidatorUserCostumer validatorUserCostumer;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateEntity_UserExists() {
        Long idUser = 1L;
        UserCustomer userCustomer = new UserCustomer();

        when(userRepository.findById(idUser)).thenReturn(Optional.of(userCustomer));

        assertDoesNotThrow(() -> validatorUserCostumer.validateEntity(idUser));
    }

    @Test
    void testValidateEntity_UserDoesNotExist() {
        Long idUser = 1L;

        when(userRepository.findById(idUser)).thenReturn(Optional.empty());

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorUserCostumer.validateEntity(idUser));
    }

    @Test
    void testValidateEmail_NullEmail() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setEmail(null);

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorUserCostumer.validateEmail(user));
    }

    @Test
    void testValidateEmail_ValidEmail() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setEmail("test@example.com");

        assertDoesNotThrow(() -> validatorUserCostumer.validateEmail(user));
    }

    @Test
    void testValidateEmailAppActive_NullEmail() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setEmail(null);

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorUserCostumer.validateEmailAppActive(user));
    }

    @Test
    void testValidateEmailAppActive_ValidEmail() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setEmail("test@example.com");

        assertDoesNotThrow(() -> validatorUserCostumer.validateEmailAppActive(user));
    }

    @Test
    void testValidatePhoneNumber_BlankPhoneNumber() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setPhoneNumber(" ");

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorUserCostumer.validatePhoneNumber(user));
    }

    @Test
    void testValidatePhoneNumber_ValidPhoneNumber() {
        UserCustomer user = new UserCustomer();
        user.setIdUser(1L);
        user.setPhoneNumber("123456789");

        assertDoesNotThrow(() -> validatorUserCostumer.validatePhoneNumber(user));
    }
}
