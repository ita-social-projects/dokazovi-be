package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.mapper.PostTypeMapper;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import com.softserveinc.dokazovi.service.PostTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostTypeServiceImpl implements PostTypeService {

    private final PostTypeRepository postTypeRepository;
    private final PostTypeMapper postTypeMapper;

    @Override
    public List<PostTypeDTO> findAll() {
        return postTypeRepository.findAll()
                .stream()
                .map(postTypeMapper::toPostTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostTypeDTO> findAllPostTypesByUserId(Integer userId) {
        return postTypeRepository.findAllPostTypesByUserId(userId)
                .stream()
                .map(postTypeMapper::toPostTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostTypeDTO> findAllPostTypesByUserIdAndStatus(Integer userId, PostStatus postStatus) {
        if (postStatus == null) {
            return findAllPostTypesByUserId(userId);
        }
        return postTypeRepository.findAllPostTypesByUserIdAndStatus(userId, postStatus)
                .stream()
                .map(postTypeMapper::toPostTypeDTO)
                .collect(Collectors.toList());
    }
}
