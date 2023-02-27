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
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Component
@Aspect
@RequiredArgsConstructor
public class PostLogger {

    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @AfterReturning("execution(* com.softserveinc.dokazovi.service.impl.PostServiceImpl.saveFromUser("
            + "com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO,"
            + "com.softserveinc.dokazovi.security.UserPrincipal))")
    public void saveNewPost(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        PostSaveFromUserDTO postSaveFromUserDTO = getPostSaveFromUserDTOFromArray(arguments);
        UserPrincipal userPrincipal = getUserPrincipalFromArray(arguments);
        makeEntryInLogs(postSaveFromUserDTO.getTitle(), userPrincipal, "Створено матеріал");
    }

    @Around("execution(* com.softserveinc.dokazovi.service.impl.PostServiceImpl.updatePostById("
            + "com.softserveinc.dokazovi.security.UserPrincipal,"
            + "com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO))")
    public Boolean updatePost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] arguments = proceedingJoinPoint.getArgs();
        Integer postId = getPostIdFromArray(arguments);
        String postEntityBeforeExecutingStatus = postRepository.getOne(postId).getStatus().name();
        final Boolean joinPoint = (Boolean) proceedingJoinPoint.proceed();
        UserPrincipal userPrincipal = getUserPrincipalFromArray(arguments);
        PostSaveFromUserDTO postSaveFromUserDTO = getPostSaveFromUserDTOFromArray(arguments);
        String postEntityChangedStatus = PostStatus.values()[postSaveFromUserDTO.getPostStatus()].name();
        String changes;
        if (postEntityBeforeExecutingStatus.equals(postEntityChangedStatus)) {
            changes = "Оновлено матеріал";
        } else {
            switch (postEntityChangedStatus) {
                case "ARCHIVED":
                    changes = "Заархівовано";
                    break;
                case "MODERATION_FIRST_SIGN":
                    changes = "Відправлено на модерацію";
                    break;
                case "NEEDS_EDITING":
                    changes = "Повернуто автору на редагування";
                    break;
                case "PLANNED":
                    changes = "Заплановано публікацію";
                    break;
                case "PUBLISHED":
                    changes = "Опубліковано";
                    break;
                default:
                    changes = "N/A";
            }
        }
        makeEntryInLogs(postSaveFromUserDTO.getTitle(), userPrincipal, changes);
        return joinPoint;
    }

    @AfterReturning("execution(* com.softserveinc.dokazovi.service.impl.PostServiceImpl.removePostById("
            + "com.softserveinc.dokazovi.security.UserPrincipal,"
            + "Integer, boolean))")
    public void deletePost(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        UserPrincipal userPrincipal = getUserPrincipalFromArray(arguments);
        Integer postId = getPostIdFromArray(arguments);
        boolean flag = getBooleanFromArray(arguments);
        if (!flag) {
            return;
        }
        PostEntity postEntity = postRepository.getOne(postId);
        makeEntryInLogs(postEntity.getTitle(), userPrincipal, "Матеріал видалено");
    }

    private void makeEntryInLogs(String title, UserPrincipal userPrincipal, String changes) {
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getEmail()).get();
        LogEntity log = LogEntity.builder()
                .title(title)
                .changes(changes)
                .nameOfChanger(userEntity.getLastName() + " " + userEntity.getFirstName())
                .build();
        logRepository.save(log);
    }

    private static boolean getBooleanFromArray(Object[] arguments) {
        return (boolean) Arrays.stream(arguments)
                .filter(obj -> obj instanceof Boolean)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Unable find argument"));
    }

    private static Integer getPostIdFromArray(Object[] arguments) {
        return (Integer) Arrays.stream(arguments)
                .filter(obj -> obj instanceof Integer)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Unable find argument"));
    }

    private static UserPrincipal getUserPrincipalFromArray(Object[] arguments) {
        return (UserPrincipal) Arrays.stream(arguments)
                .filter(obj -> obj instanceof UserPrincipal)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Unable find argument"));
    }

    private static PostSaveFromUserDTO getPostSaveFromUserDTOFromArray(Object[] arguments) {
        return (PostSaveFromUserDTO) Arrays.stream(arguments)
                .filter(obj -> obj instanceof PostSaveFromUserDTO)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Unable find argument"));
    }
}