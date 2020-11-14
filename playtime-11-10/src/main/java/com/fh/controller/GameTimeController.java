package com.fh.controller;

import com.fh.annotatio.Ignore;
import com.fh.vo.GameTimeVo;
import com.fh.common.ServerResponse;
import com.fh.model.GameTime;
import com.fh.model.User;
import com.fh.service.GameTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("game")
public class GameTimeController {

    public static void main(String[] args) {
        System.out.println("3333");
    }



    @Autowired
    private GameTimeService gameTimeService;

    //查询+条件查询
    @Ignore
    @PostMapping("queryList")
    public ServerResponse queryList(GameTimeVo gameTimeVo){
        return gameTimeService.queryList(gameTimeVo);
    }

    //新增+修改
    @Ignore
    @PostMapping("saveOrUpdate")
    public ServerResponse saveOrUpdate(GameTime gameTime){
        return gameTimeService.saveOrUpdate(gameTime);
    }

    //删除
    @Ignore
    @PostMapping("deleteGame")
    public ServerResponse deleteGame(Integer id){
        return gameTimeService.deleteGame(id);
    }

    //登陆
    @Ignore
    @PostMapping("login")
    public ServerResponse login(User user, HttpServletRequest request){
        return gameTimeService.login(user,request);
    }

    //导入excel
    @Ignore
    @RequestMapping("importExcel")
    public void importExcel(MultipartFile file) {
        gameTimeService.importExcel(file);
    }
}
