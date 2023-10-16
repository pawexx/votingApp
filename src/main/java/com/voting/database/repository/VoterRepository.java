package com.voting.database.repository;

import com.voting.database.model.VoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VoterRepository extends JpaRepository<VoterEntity, UUID> {
    Optional<VoterEntity> findByName(String name);
}
