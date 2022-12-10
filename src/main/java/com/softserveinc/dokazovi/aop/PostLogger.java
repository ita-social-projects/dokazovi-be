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
        PostSaveFromUserDTO postSaveFromUserDTO = null;
        UserPrincipal userPrincipal = null;
        for (Object obj : arguments) {
            if (obj instanceof PostSaveFromUserDTO) {
                postSaveFromUserDTO = (PostSaveFromUserDTO) obj;
            }
            if (obj instanceof UserPrincipal) {
                userPrincipal = (UserPrincipal) obj;
            }
        }
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getEmail()).get();
        LogEntity log = LogEntity.builder()
                .title(postSaveFromUserDTO.getTitle())
                .changes("Створено матеріал")
                .nameOfChanger(userEntity.getLastName() + " " + userEntity.getFirstName())
                .build();

        logRepository.save(log);
    }

    @Around("execution(* com.softserveinc.dokazovi.service.impl.PostServiceImpl.updatePostById("
            + "com.softserveinc.dokazovi.security.UserPrincipal,"
            + "com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO))")
    public Boolean updatePost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] arguments = proceedingJoinPoint.getArgs();
        Integer postId = null;
        for (Object obj : arguments) {
            if (obj instanceof PostSaveFromUserDTO) {
                postId = ((PostSaveFromUserDTO) obj).getId();
            }
        }
        String postEntityBeforeExecutingStatus = postRepository.getOne(postId).getStatus().name();
        Boolean joinPoint = (Boolean) proceedingJoinPoint.proceed();
        UserPrincipal userPrincipal = null;
        PostSaveFromUserDTO postSaveFromUserDTO = null;
        for (Object obj : arguments) {
            if (obj instanceof PostSaveFromUserDTO) {
                postSaveFromUserDTO = (PostSaveFromUserDTO) obj;
            }
            if (obj instanceof UserPrincipal) {
                userPrincipal = (UserPrincipal) obj;
            }
        }
        String postEntityChangedStatus = PostStatus.values()[postSaveFromUserDTO.getPostStatus()].name();
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getEmail()).get();
        String changes = "N/A";
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
            }
        }
        LogEntity log = LogEntity.builder()
                .title(postSaveFromUserDTO.getTitle())
                .changes(changes)
                .nameOfChanger(userEntity.getLastName() + " " + userEntity.getFirstName())
                .build();

        logRepository.save(log);
        return joinPoint;
    }

    @AfterReturning("execution(* com.softserveinc.dokazovi.service.impl.PostServiceImpl.removePostById("
            + "com.softserveinc.dokazovi.security.UserPrincipal,"
            + "Integer, boolean))")
    public void deletePost(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        UserPrincipal userPrincipal = null;
        Integer postId = null;
        boolean flag = false;
        for (Object obj : arguments) {
            if (obj instanceof UserPrincipal) {
                userPrincipal = (UserPrincipal) obj;
            }
            if (obj instanceof Integer) {
                postId = (Integer) obj;
            }
            if (obj instanceof Boolean) {
                flag = (boolean) obj;
            }
        }
        if (!flag) {
            return;
        }
        PostEntity postEntity = postRepository.getOne(postId);
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getEmail()).get();
        LogEntity log = LogEntity.builder()
                .title(postEntity.getTitle())
                .changes("Матеріал видалено")
                .nameOfChanger(userEntity.getLastName() + " " + userEntity.getFirstName())
                .build();

        logRepository.save(log);
    }
}