package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.GameTime;

import java.util.List;

public interface GameTimeMapper extends BaseMapper<GameTime> {
    void addBatchGame(List<GameTime> gameList);
}
