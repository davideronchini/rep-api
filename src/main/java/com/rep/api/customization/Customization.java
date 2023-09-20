package com.rep.api.customization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.user.User;
import com.rep.api.widget_type.WidgetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"user", "widgetType1", "widgetType2", "widgetType3", "widgetType4"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customizations")
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "widget_type_1_id")
    private Long widgetType1Id;

    @Column(name = "widget_type_2_id")
    private Long widgetType2Id;

    @Column(name = "widget_type_3_id")
    private Long widgetType3Id;

    @Column(name = "widget_type_4_id")
    private Long widgetType4Id;

    @OneToOne()
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "widget_type_1_id", insertable = false, updatable = false)
    private WidgetType widgetType1;

    @ManyToOne
    @JoinColumn(name = "widget_type_2_id", insertable = false, updatable = false)
    private WidgetType widgetType2;

    @ManyToOne
    @JoinColumn(name = "widget_type_3_id", insertable = false, updatable = false)
    private WidgetType widgetType3;

    @ManyToOne
    @JoinColumn(name = "widget_type_4_id", insertable = false, updatable = false)
    private WidgetType widgetType4;
}
