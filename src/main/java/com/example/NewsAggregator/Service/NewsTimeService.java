package com.example.NewsAggregator.Service;

import java.util.List;

public interface NewsTimeService {
    void saveAll(Integer startingId, Integer lastId);

    void deleteAllByNewsIds(Integer val);
}
