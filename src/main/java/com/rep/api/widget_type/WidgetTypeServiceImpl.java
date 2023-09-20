package com.rep.api.widget_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WidgetTypeServiceImpl implements WidgetTypeService {

    private final WidgetTypeRepository widgetTypeRepository;

    @Override
    public List<WidgetType> findAll() {
        return widgetTypeRepository.findAll();
    }

    @Override
    public Set<WidgetType> findByValue(int value) {
        return widgetTypeRepository.findByValue(value);
    }

    @Override
    public void save(WidgetType widgetType) {
        if (widgetTypeRepository.findByValue(widgetType.getValue()).isEmpty()) {
            widgetTypeRepository.save(widgetType);
        }
    }
}