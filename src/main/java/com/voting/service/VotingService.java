package com.voting.service;

import com.voting.database.model.CandidateEntity;
import com.voting.database.model.VoterEntity;
import com.voting.database.repository.CandidateRepository;
import com.voting.database.repository.VoterRepository;
import com.voting.dto.VoteObject;
import com.voting.exception.UserNotExistsException;
import com.voting.exception.VoterAlreadyVotedException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotingService {
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    @Transactional
    public void vote(VoteObject voteObject) throws VoterAlreadyVotedException, UserNotExistsException {
        VoterEntity voter = voterRepository.findByName(voteObject.getVoterName())
                .orElseThrow(() -> new UserNotExistsException("Voter does not exists: "  + voteObject.getVoterName()));

        if (voter.getCandidate() != null) {
            throw new VoterAlreadyVotedException("Voter " + voter.getName() + " already voted!");
        }

        CandidateEntity candidate = candidateRepository.findByName(voteObject.getCandidateName())
                .orElseThrow(() -> new UserNotExistsException("Candidate does not exists: " + voteObject.getCandidateName()));

        voter.setCandidate(candidate);
        candidate.addVoter(voter);
    }
}
