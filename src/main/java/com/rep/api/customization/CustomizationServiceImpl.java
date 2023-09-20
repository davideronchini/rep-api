package com.rep.api.customization;

import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import com.rep.api.widget_type.WidgetType;
import com.rep.api.widget_type.WidgetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomizationServiceImpl implements CustomizationService {

    private final UserRepository userRepository;

    private final CustomizationRepository customizationRepository;

    private final WidgetTypeRepository widgetTypeRepository;

    @Override
    public List<Customization> findAll() {
        return customizationRepository.findAll();
    }

    @Override
    public Optional<Customization> findByUserId(Long userId) {
        return customizationRepository.findByUserId(userId);
    }

    @Override
    public void save(Customization customization) {
        User user = userRepository.findById(customization.getUserId()).orElse(null);
        WidgetType widgetType1 = widgetTypeRepository.findById(customization.getWidgetType1Id()).orElse(null);
        WidgetType widgetType2 = widgetTypeRepository.findById(customization.getWidgetType2Id()).orElse(null);
        WidgetType widgetType3 = widgetTypeRepository.findById(customization.getWidgetType3Id()).orElse(null);
        WidgetType widgetType4 = widgetTypeRepository.findById(customization.getWidgetType4Id()).orElse(null);

        if (user != null && widgetType1 != null && widgetType2 != null && widgetType3 != null && widgetType4 != null) {
            customization.setUser(user);
            customization.setWidgetType1(widgetType1);
            customization.setWidgetType2(widgetType2);
            customization.setWidgetType3(widgetType3);
            customization.setWidgetType4(widgetType4);

            customizationRepository.save(customization);
        }
    }
}