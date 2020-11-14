package com.fh.service;

import com.fh.vo.GameTimeVo;
import com.fh.common.ServerResponse;
import com.fh.model.GameTime;
import com.fh.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface GameTimeService {
    ServerResponse queryList(GameTimeVo gameTimeVo);

    ServerResponse saveOrUpdate(GameTime gameTime);

    ServerResponse deleteGame(Integer id);

    ServerResponse login(User user, HttpServletRequest request);

    void importExcel(MultipartFile file);
}
