package com.rep.api.season;

import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seasonNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;
}
