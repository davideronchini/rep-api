package com.rep.api.customization;

import com.rep.api.user.User;
import com.rep.api.widget_type.WidgetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "widget_type_1_id")
    private Long widgetType1Id;

    @Column(name = "widget_type_2_id")
    private Long widgetType2Id;

    @Column(name = "widget_type_3_id")
    private Long widgetType3Id;

    @Column(name = "widget_type_4_id")
    private Long widgetType4Id;

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

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
