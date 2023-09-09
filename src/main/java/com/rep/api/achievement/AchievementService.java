package com.rep.api.achievement;

public interface AchievementService {

    void save(Achievement achievement);

    void addUser(Long achievementId, Long userId);
}
