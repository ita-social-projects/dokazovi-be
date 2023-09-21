package com.softserveinc.dokazovi.events;

import com.softserveinc.dokazovi.dto.post.DeletePostDTO;
import org.springframework.context.ApplicationEvent;

public class PostDeleteEvent extends ApplicationEvent {

    private final DeletePostDTO deletePostDTO;

    /**
     * Create a new PostDeleteEvent.
     *
     * @param source the object on which the event initially occurred or with which the event is associated (never
     *               {@code null})
     */
    public PostDeleteEvent(Object source, DeletePostDTO deletePostDTO) {
        super(source);
        this.deletePostDTO = deletePostDTO;
    }

    public DeletePostDTO getPostTitleDTO() {
        return deletePostDTO;
    }
}
