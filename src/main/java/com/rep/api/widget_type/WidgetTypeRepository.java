package com.rep.api.widget_type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WidgetTypeRepository extends JpaRepository<WidgetType, Long> {

    Set<WidgetType> findByValue(int value);
}
