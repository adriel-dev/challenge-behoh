package com.behoh.challenge.domain.participation.port.out;

import com.behoh.challenge.domain.participation.model.Participation;

public interface SaveParticipationPort {
    Participation saveParticipation(Participation participation);
    Participation updateParticipation(Participation participation);
}
