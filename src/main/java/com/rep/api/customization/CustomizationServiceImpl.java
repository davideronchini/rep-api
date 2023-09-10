package com.rep.api.customization;

import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomizationServiceImpl implements CustomizationService {

    private final UserRepository userRepository;

    private final CustomizationRepository customizationRepository;

    private final WidgetTypeRepository widgetTypeRepository;

    @Override
    public void save(Long userId, Customization customization) {
        User user = userRepository.findById(userId).orElse(null);
        WidgetType widgetType1 = widgetTypeRepository.findById(customization.getWidgetType1Id()).orElse(null);
        WidgetType widgetType2 = widgetTypeRepository.findById(customization.getWidgetType2Id()).orElse(null);
        WidgetType widgetType3 = widgetTypeRepository.findById(customization.getWidgetType3Id()).orElse(null);
        WidgetType widgetType4 = widgetTypeRepository.findById(customization.getWidgetType4Id()).orElse(null);

        if (user != null && widgetType1 != null && widgetType2 != null && widgetType3 != null && widgetType4 != null) {
            Customization foundCustomization = user.getCustomization();

            foundCustomization.setWidgetType1(widgetType1);
            foundCustomization.setWidgetType2(widgetType2);
            foundCustomization.setWidgetType3(widgetType3);
            foundCustomization.setWidgetType4(widgetType4);

            customizationRepository.save(foundCustomization);
        }
    }
}
