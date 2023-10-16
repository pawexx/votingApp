package com.voting.controller;

import com.voting.dto.CandidateDto;
import com.voting.dto.VoterDto;
import com.voting.exception.CandidateAlreadyExistsException;
import com.voting.exception.VoterAlreadyExistsException;
import com.voting.maping.CandidateMapper;
import com.voting.maping.VoterMapper;
import com.voting.service.CandidateService;
import com.voting.service.VoterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final CandidateService candidateService;
    private final VoterService voterService;
    private final VoterMapper voterMapper;
    private final CandidateMapper candidateMapper;

    @GetMapping("/voters")
    public ResponseEntity<List<VoterDto>> getVoters() {
        return ResponseEntity.ok(voterService.getVoters().stream()
                .map(voterMapper::toVoterDto)
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/create-voter/{name}")
    public ResponseEntity<String> createVoter(@PathVariable("name") String name) {
        try {
            voterService.createVoter(name);
        } catch (VoterAlreadyExistsException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<CandidateDto>> getCandidates() {
        return ResponseEntity.ok(candidateService.getCandidates().stream()
                .map(candidateMapper::toCandidateDto)
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/create-candidate/{name}")
    public ResponseEntity<String> createCandidate(@PathVariable("name") String name) {
        try {
            candidateService.createCandidate(name);
        } catch (CandidateAlreadyExistsException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
