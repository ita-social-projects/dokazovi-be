package com.softserveinc.dokazovi.aop;

import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTitleDTO;
import com.softserveinc.dokazovi.entity.LogEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.handlers.PostDeleteEventHandler;
import com.softserveinc.dokazovi.repositories.LogRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
    @Mock
    private PostDeleteEventHandler postDeleteEventHandler;
    @InjectMocks
    private PostLogger postLogger;
    @Captor
    private ArgumentCaptor<LogEntity> logEntityArgumentCaptor;

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
                .status(PostStatus.DRAFT)
                .title(" ").build();
    }

    @Test
    void saveNewPost() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{postSaveFromUserDTO, userPrincipal};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        postLogger.saveNewPost(mock);

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "Створено матеріал");
    }

    @Test
    void updatePostSameStatus() throws Throwable {
        ProceedingJoinPoint mock = Mockito.mock(ProceedingJoinPoint.class);

        Object[] args = new Object[]{userPrincipal, postSaveFromUserDTO};
        when(mock.getArgs()).thenReturn(args);
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        postLogger.updatePost(mock);

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "Оновлено матеріал");
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

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "Заархівовано");
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

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "Відправлено на модерацію");
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

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(
                logEntityArgumentCaptor.getValue().getChanges(),
                "Повернуто автору на редагування");
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

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "Опубліковано");
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

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getChanges(), "N/A");
    }

    @Test
    void deletePostBlankTitle() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{userPrincipal, 2, true};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);

        when(postDeleteEventHandler.getPostTitleDTO()).thenReturn(PostTitleDTO.builder().title(" ").build());

        postLogger.deletePost(mock);

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getTitle(), "The title is blank");
    }

    @Test
    void deletePostEmptyTitle() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{userPrincipal, 2, true};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);

        when(postDeleteEventHandler.getPostTitleDTO()).thenReturn(PostTitleDTO.builder().title("").build());

        postLogger.deletePost(mock);

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getTitle(), "The title is blank");
    }

    @Test
    void deletePostNotBlankTitle() {
        JoinPoint mock = Mockito.mock(JoinPoint.class);

        Object[] args = new Object[]{userPrincipal, 2, true};
        when(mock.getArgs()).thenReturn(args);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(postRepository.getOne(anyInt())).thenReturn(postEntity);

        when(postDeleteEventHandler.getPostTitleDTO()).thenReturn(
                PostTitleDTO.builder().title("The title is not blank").build());

        postLogger.deletePost(mock);

        verify(logRepository).save(logEntityArgumentCaptor.capture());
        Assertions.assertEquals(logEntityArgumentCaptor.getValue().getTitle(), "The title is not blank");
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