package com.voting.controller;

import com.voting.dto.VoteObject;
import com.voting.exception.UserNotExistsException;
import com.voting.exception.VoterAlreadyVotedException;
import com.voting.service.VotingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@Slf4j
public class VoteController {
    private final VotingService votingService;

    @PostMapping(value = "/vote")
    public ResponseEntity<String> vote(@RequestBody VoteObject voteObject) {
        try {
            votingService.vote(voteObject);
        } catch (UserNotExistsException | VoterAlreadyVotedException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
