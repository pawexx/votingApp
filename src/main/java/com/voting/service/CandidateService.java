package com.voting.service;

import com.voting.database.model.CandidateEntity;
import com.voting.database.repository.CandidateRepository;
import com.voting.exception.CandidateAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<CandidateEntity> getCandidates() {
        return candidateRepository.findAll();
    }

    public void createCandidate(String userName) throws CandidateAlreadyExistsException {
        if (candidateRepository.findByName(userName).isPresent()) {
            throw new CandidateAlreadyExistsException("Candidate already exists " + userName);
        }

        CandidateEntity candidate = CandidateEntity.builder()
                .name(userName)
                .build();
        candidateRepository.save(candidate);
    }
}
