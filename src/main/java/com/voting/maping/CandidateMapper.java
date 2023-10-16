package com.voting.maping;

import com.voting.database.model.CandidateEntity;
import com.voting.dto.CandidateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    @Mapping(expression = "java(candidateEntity.getVotersSize())", target = "votes")
    CandidateDto toCandidateDto(CandidateEntity candidateEntity);

}
