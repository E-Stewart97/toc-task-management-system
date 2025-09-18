package com.se_devops.tocsystem.service;

import com.se_devops.tocsystem.model.TOC;
import com.se_devops.tocsystem.model.User;
import com.se_devops.tocsystem.repository.TOCRepository;
import com.se_devops.tocsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TOCServiceTest {

    @Mock
    private TOCRepository tocRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TOCService tocService;
    private User owner;
    private TOC toc;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1);
        owner.setUsername("owner");

        toc = new TOC();
        toc.setId(100);
        toc.setTitle("Test TOC");
        toc.setUser(owner);
    }

    @Test
    void findByIdAndUsername_ThrowsAccessDenied_WhenUserIsNotOwner() {
        when(tocRepository.findById(100)).thenReturn(Optional.of(toc));
        assertThrows(AccessDeniedException.class, () -> {
            tocService.findByIdAndUsername(100, "otherUser");
        }, "Should throw AccessDeniedException for non-owner");
    }

    @Test
    void deleteByIdAsAdmin_DeletesSuccessfully() {
        int tocId = 100;
        when(tocRepository.existsById(tocId)).thenReturn(true);
        doNothing().when(tocRepository).deleteById(tocId);

        tocService.deleteByIdAsAdmin(tocId);

        verify(tocRepository, times(1)).deleteById(tocId);
    }
}