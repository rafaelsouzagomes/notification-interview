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

import com.interview.notification.model.Category;
import com.interview.notification.repositories.CategoryRepository;

class ValidatorCategoryTest {

    @InjectMocks
    private ValidatorCategory validatorCategory;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateEntity_CategoryExists() {
        Long idCategory = 1L;
        Category category = new Category();

        when(categoryRepository.findById(idCategory)).thenReturn(Optional.of(category));

        assertDoesNotThrow(() -> validatorCategory.validateEntity(idCategory));
    }

    @Test
    void testValidateEntity_CategoryDoesNotExist() {
        Long idCategory = 1L;

        when(categoryRepository.findById(idCategory)).thenReturn(Optional.empty());

        assertThrows(ValidationNotificationGlobalException.class, () -> validatorCategory.validateEntity(idCategory));
    }
}
