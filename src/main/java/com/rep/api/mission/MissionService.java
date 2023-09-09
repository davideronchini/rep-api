package com.rep.api.mission;

public interface MissionService {

    void save(Mission mission);

    void addUser(Long missionId, Long userId);
}
