package com.rep.api.interaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractionDTO {

    private LocalDateTime date;

    private boolean isVisible = true;

    private boolean isSpecial = false;

    private Long user1Id;

    private Long user2Id;

    private Long emojiId;

    private Long seasonId;
}
