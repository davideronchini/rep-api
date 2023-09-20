package com.rep.api.customization;

import java.util.List;
import java.util.Optional;

public interface CustomizationService {

    List<Customization> findAll();

    Optional<Customization> findByUserId(Long userId);

    void save(Customization customization);
}