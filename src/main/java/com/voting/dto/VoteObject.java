package com.voting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteObject {
    private String candidateName;
    private String voterName;
}
