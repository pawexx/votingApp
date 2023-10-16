package com.voting.maping;

import com.voting.database.model.VoterEntity;
import com.voting.dto.VoterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoterMapper {

    @Mapping(expression = "java(null == voterEntity.getCandidate() ? false : true)", target = "voted")
    VoterDto toVoterDto(VoterEntity voterEntity);

}
