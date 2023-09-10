package com.rep.api.customization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WidgetTypeServiceImpl implements WidgetTypeService {

    private final WidgetTypeRepository widgetTypeRepository;

    @Override
    public void save(WidgetType widgetType) {
        widgetTypeRepository.save(widgetType);
    }
}
