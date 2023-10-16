package com.voting.service;

import com.voting.database.model.VoterEntity;
import com.voting.database.repository.VoterRepository;
import com.voting.exception.VoterAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoterService {
    private final VoterRepository voterRepository;

    public List<VoterEntity> getVoters() {
        return voterRepository.findAll();
    }

    public void createVoter(String userName) throws VoterAlreadyExistsException {
        if (voterRepository.findByName(userName).isPresent()) {
            throw new VoterAlreadyExistsException("VoterAlreadyExists " + userName);
        }

        VoterEntity user = VoterEntity.builder()
                .name(userName)
                .build();
        voterRepository.save(user);
    }
}
