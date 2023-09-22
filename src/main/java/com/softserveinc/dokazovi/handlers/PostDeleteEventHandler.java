package com.softserveinc.dokazovi.handlers;

import com.softserveinc.dokazovi.events.PostDeleteEvent;
import com.softserveinc.dokazovi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDeleteEventHandler implements ApplicationListener<PostDeleteEvent> {

    private final LogService logService;

    @Async
    @Override
    public void onApplicationEvent(PostDeleteEvent event) {
        if (event.getPostTitleDTO().getTitle().isEmpty() || event.getPostTitleDTO().getTitle().isBlank()) {
            logService.makeEntryInLogs("The title is blank", event.getPostTitleDTO().getUserPrincipal(),
                    "Матеріал видалено", event.getPostTitleDTO().getPostId());
        } else {
            logService.makeEntryInLogs(event.getPostTitleDTO().getTitle(), event.getPostTitleDTO().getUserPrincipal(),
                    "Матеріал видалено", event.getPostTitleDTO().getPostId());
        }
    }
}
