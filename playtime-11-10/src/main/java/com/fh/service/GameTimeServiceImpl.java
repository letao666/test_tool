package com.fh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.vo.GameTimeVo;
import com.fh.common.ServerResponse;
import com.fh.common.SystemConst;
import com.fh.mapper.GameTimeMapper;
import com.fh.mapper.UserMapper;
import com.fh.model.GameTime;
import com.fh.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameTimeServiceImpl implements GameTimeService {
    @Resource
    private GameTimeMapper gameTimeMapper;

    @Resource
    private UserMapper userMapper;

    //查询
    @Override
    public ServerResponse queryList(GameTimeVo gameTimeVo) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy");
        QueryWrapper<GameTime> queryWrapper = new QueryWrapper<>();
        List<GameTime> gameList = gameTimeMapper.selectList(queryWrapper);
        for (int i = 0; i < gameList.size(); i++) {
            GameTime gameTime = gameList.get(i);
            String str = gameTime.getCard().substring(6, 10);
            String format = sim.format(new Date());
            gameTime.setAge(Integer.valueOf(format)-Integer.valueOf(str));
            gameTimeMapper.updateById(gameTime);
        }
        QueryWrapper<GameTime> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(gameTimeVo.getUserName())){
            wrapper.like("userName",gameTimeVo.getUserName());
        }

        if(gameTimeVo.getMinAge() != null){
            wrapper.gt("age",gameTimeVo.getMinAge());
        }
        if(gameTimeVo.getMaxAge() != null){
            wrapper.lt("age",gameTimeVo.getMaxAge());
        }

        List<GameTime> gameTimeList = gameTimeMapper.selectList(wrapper);

        return ServerResponse.success(gameTimeList);
    }

    //新增+修改
    @Override
    public ServerResponse saveOrUpdate(GameTime gameTime) {
        if(gameTime.getId() == null){
            gameTimeMapper.insert(gameTime);
        }else{
            gameTimeMapper.updateById(gameTime);
        }
        return ServerResponse.success();
    }

    //删除
    @Override
    public ServerResponse deleteGame(Integer id) {
        gameTimeMapper.deleteById(id);
        return ServerResponse.success();
    }

    //登陆
    @Override
    public ServerResponse login(User user, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getName());
        User userDB = userMapper.selectOne(queryWrapper);
        if(userDB == null){
            return ServerResponse.success(SystemConst.USER_IS_NULL);
        }
        if(!user.getPassword().equals(userDB.getPassword())){
            return ServerResponse.error(SystemConst.PASSWORD_IS_ERROR);
        }else{
            //登陆成功,将用户信息放到session中
            request.getSession().setAttribute(SystemConst.SESSION_USER,userDB);
        }
        return ServerResponse.success();
    }

    //导入excel
    @Override
    public void importExcel(MultipartFile file) {

        try {
            Workbook wb = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            //获取最后一行的下标
            int rowNum = sheet.getLastRowNum();
            List<GameTime> gameList = new ArrayList<>();
            for (int i = 1; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0);
                String gameName = cell.getStringCellValue();//获取游戏名称
                cell = row.getCell(1);
                String beginTime = cell.getStringCellValue();
                cell = row.getCell(2);
                String endTime = cell.getStringCellValue();
                cell = row.getCell(3);
                String userName = cell.getStringCellValue();
                cell = row.getCell(4);
                String card = cell.getStringCellValue();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                GameTime gameTime = new GameTime();
                gameTime.setGameName(gameName);
                gameTime.setBeginTime(sim.parse(beginTime));
                gameTime.setEndTime(sim.parse(endTime));
                gameTime.setCard(card);
                gameTime.setUserName(userName);

                gameList.add(gameTime);
                gameTimeMapper.addBatchGame(gameList);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
