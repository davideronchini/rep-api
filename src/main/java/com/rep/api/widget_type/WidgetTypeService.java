package com.rep.api.widget_type;

import java.util.List;
import java.util.Set;

public interface WidgetTypeService {

    List<WidgetType> findAll();

    Set<WidgetType> findByValue(int value);

    void save(WidgetType widgetType);
}