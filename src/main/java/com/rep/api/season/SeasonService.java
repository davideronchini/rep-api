package com.rep.api.season;

import java.util.List;

public interface SeasonService {

    List<Season> findAll();

    void save();
}
