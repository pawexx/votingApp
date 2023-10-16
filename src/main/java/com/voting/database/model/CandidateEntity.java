package com.voting.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "candidate")
    private List<VoterEntity> voters;

    public void addVoter(VoterEntity voter) {
        voters.add(voter);
    }
    public int getVotersSize() {
        return voters != null ? voters.size() : 0;
    }
}
