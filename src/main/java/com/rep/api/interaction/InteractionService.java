package com.rep.api.interaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InteractionService {

    List<Interaction> findAll();

    Set<Optional<Interaction>> findAllVisible();

    void save(Interaction interaction);
}
