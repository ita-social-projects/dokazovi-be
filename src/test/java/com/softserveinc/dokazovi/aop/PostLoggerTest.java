package com.softserveinc.dokazovi.aop;

import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.LogEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.repositories.LogRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostLoggerTest {

    @Mock
    private LogRepository logRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostLogger postLogger;

    private PostSaveFromUserDTO postSaveFromUserDTO;
    private UserPrincipal userPrincipal;
    private UserEntity userEntity;
    private PostEntity postEntity;

    @BeforeEach
    void setUp() {
        postSaveFromUserDTO = PostSaveFromUserDTO.builder()
                .id(1)
                .postStatus(0)
                .title("testTitle").build();

        userPrincipal = UserPrincipal.builder()
                .email("test@mail.com").build();

        userEntity = UserEntity.builder()
                .firstName("testFirstName")
                .lastName("testLastName").build();

        postEntity = PostEntity.builder()
                .status(PostStatus.DRAFT).build();
    }

    @Test
    void saveNewPost() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{postSaveFromUserDTO, userPrincipal};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        postLogger.saveNewPost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostSameStatus() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostStatusArchived() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        postSaveFromUserDTO.setPostStatus(6);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostStatusModerationFirstSign() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        postSaveFromUserDTO.setPostStatus(2);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostStatusNeedsEditing() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        postSaveFromUserDTO.setPostStatus(1);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostStatusPublished() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        postSaveFromUserDTO.setPostStatus(5);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void updatePostStatusNA() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        postSaveFromUserDTO.setPostStatus(3);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void deletePost() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{userPrincipal, 1, true};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);

        postLogger.deletePost(mock);

        verify(logRepository).save(any(LogEntity.class));
    }

    @Test
    void archivePost() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{userPrincipal, 1, false};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);

        postLogger.deletePost(mock);

        verify(logRepository, never()).save(any(LogEntity.class));
    }
}