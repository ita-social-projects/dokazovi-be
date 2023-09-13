package com.softserveinc.dokazovi.handlers;

import com.softserveinc.dokazovi.dto.post.PostTitleDTO;
import com.softserveinc.dokazovi.events.PostDeleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PostDeleteEventHandler implements ApplicationListener<PostDeleteEvent> {

    private PostTitleDTO postTitleDTO;

    @Override
    public void onApplicationEvent(PostDeleteEvent event) {
        this.postTitleDTO = event.getPostTitleDTO();
    }

    public PostTitleDTO getPostTitleDTO() {
        return postTitleDTO;
    }
}
