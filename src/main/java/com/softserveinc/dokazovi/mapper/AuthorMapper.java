package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.author.AuthorResponseDTO;
import com.softserveinc.dokazovi.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "socialNetworks", source = "authorEntity.profile.socialNetworks")
    @Mapping(target = "cityId", source = "authorEntity.city.id")
    @Mapping(target = "avatar", source = "authorEntity.profile.avatar")
    @Mapping(target = "regionId", source = "authorEntity.city.region.id")
    AuthorResponseDTO toAuthorResponseDTO(AuthorEntity authorEntity);

}
