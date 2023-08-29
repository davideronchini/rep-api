package com.rep.api.interaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;

    @Override
    public void createInteraction(Interaction interaction) {
        boolean interactionExists = interactionRepository.findByParams(
                interaction.getUser1().getId(),
                interaction.getUser2().getId(),
                interaction.getDate()
        ).isPresent();

        if (!interactionExists) interactionRepository.save(interaction);
    }
}
