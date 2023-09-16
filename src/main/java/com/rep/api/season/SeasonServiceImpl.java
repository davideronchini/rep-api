package com.rep.api.season;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    @Override
    public List<Season> findAll() {
        return seasonRepository.findAll();
    }

    @Override
    public void save() {
        Season lastSeason = seasonRepository.findLastSeason().orElse(null);

        if (lastSeason == null || lastSeason.getEndDate().isBefore(LocalDateTime.now())) {
            Season newSeason = new Season();
            newSeason.setSeasonNumber(lastSeason != null ? lastSeason.getSeasonNumber() + 1 : 1);
            newSeason.setStartDate(lastSeason != null ? lastSeason.getEndDate().plusDays(1).withHour(0).withMinute(0).withSecond(0) : LocalDateTime.now().withHour(0).withMinute(0).withSecond(0));
            newSeason.setEndDate(newSeason.getStartDate().plusDays(30));

            seasonRepository.save(newSeason);
        }
    }
}
