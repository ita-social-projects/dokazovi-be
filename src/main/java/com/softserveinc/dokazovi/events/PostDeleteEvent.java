package com.softserveinc.dokazovi.events;

import com.softserveinc.dokazovi.dto.post.PostTitleDTO;
import org.springframework.context.ApplicationEvent;

public class PostDeleteEvent extends ApplicationEvent {

    private final PostTitleDTO postTitleDTO;
    /**
     * Create a new PostDeleteEvent.
     *
     * @param source the object on which the event initially occurred or with which the event is associated (never
     *               {@code null})
     */
    public PostDeleteEvent(Object source, PostTitleDTO postTitleDTO) {
        super(source);
        this.postTitleDTO = postTitleDTO;
    }

    public PostTitleDTO getPostTitleDTO() {
        return postTitleDTO;
    }
}
