package com.w.volunteer.dataManage;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.w.volunteer.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class DataManage {

    private static String name = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://localhost:3306/caricature?useUnicode=true&characterEncoding=utf-8&useSSL=false&SSLMode=Required", username = "root", password = "wuchongyang";


    public static void main(String[] args) {
        try {
            String url = "https://www.redbz.com/";
            StringBuffer response = connect(url);
            String[] split = response.toString().split("<a href=\"/comic");
            List<Map<Object, Object>> pd = new ArrayList<>();
            for (int i = 1; i < split.length; i++) {
                String s = split[i];
                Map<Object, Object> pageData = new HashMap<Object, Object>();
                pageData.put("url", "/comic" + split[i].substring(0, split[i].indexOf("\" title")));
                pageData.put("name", split[i].substring(14, split[i].indexOf("\">")));
                pageData.put("pickage", s.substring(s.indexOf("/d/file"), s.indexOf("\" alt")));
                pageData.put("intro", s.substring(s.indexOf("subtitle") + 10, s.indexOf("bottom") - 38));
                pd.add(pageData);
            }
            System.out.println(1);
            processData(pd);
            System.out.println("完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步数据
     *
     * @return
     * @throws Exception
     */
    private static void processData(List<Map<Object, Object>> list) {
        if (CollectionUtil.isEmpty(list)) {
            System.out.println("数据为空！");
        }
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(8, 8);

        try {
            // 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建Statement对象
            Statement statement = connection.createStatement();
            // 执行SQL语句
            String sql = "INSERT INTO `caricature` (`name`,`code`,`introduction`, `url`, `homePage`,`createTime`,`createUser`) VALUES (?,?,?,?,?,now(),?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (Map<Object, Object> objectObjectMap : list) {
                preparedStatement.setString(1, objectObjectMap.get("name").toString());
                preparedStatement.setString(2, Long.toString(snowflakeIdWorker.nextId()));
                preparedStatement.setString(3, objectObjectMap.get("intro").toString());
                preparedStatement.setString(4, objectObjectMap.get("url").toString());
                preparedStatement.setString(5, objectObjectMap.get("pickage").toString());
                preparedStatement.setString(6, "W");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
        } finally {

        }
    }

    private static StringBuffer connect(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_4_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.2 Mobile/15E148 Safari/604.1");
        int responseCode = conn.getResponseCode();
        System.out.println("GET request sent, response code is " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

}
