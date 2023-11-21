package com.w.volunteer.dataManage;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.w.volunteer.pojo.param.CaricatureParam;
import com.w.volunteer.util.SnowflakeIdWorker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class DataManageDetail {

    private static String name = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://localhost:3306/caricature?useUnicode=true&characterEncoding=utf-8&useSSL=false&SSLMode=Required", username = "root", password = "wuchongyang";


    public static void main(String[] args) {
        try {
            List<CaricatureParam> car = getCar();
            List<Map<Object, Object>> pageData = parseDetail(car);
            System.out.println(1);
            processData(pageData);
            System.out.println("完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<CaricatureParam> getCar() {
        ArrayList<CaricatureParam> caricatureParams = new ArrayList<>();
        try {
            // 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建Statement对象
            Statement statement = connection.createStatement();
            // 执行SQL语句
            String sql = "select `code`, `url` from `caricature`";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CaricatureParam caricatureParam = new CaricatureParam();
                caricatureParam.setCode(resultSet.getString("code"));
                caricatureParam.setUrl(resultSet.getString("url"));
                caricatureParams.add(caricatureParam);
            }
        } catch (Exception e) {
        } finally {

        }
        return caricatureParams;
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
            String sql = "INSERT INTO `comment` (`car_code`,`code`,`name`,`rank`,`url`,`createTime`,`createUser`) VALUES (?,?,?,?,?,now(),?);";
            String sql_detail = "INSERT INTO `contentPicture` (`comment_code`,`code`,`url`,`rank`,`createTime`,`createUser`) VALUES (?,?,?,?,now(),'W');";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql_detail);
            for (Map<Object, Object> objectObjectMap : list) {
                List<HashMap<Object, Object>> pages = (List<HashMap<Object, Object>>) objectObjectMap.get("pages");
                for (HashMap<Object, Object> page : pages) {
                    List<String> strings = (List<String>) page.get("strings");
                    preparedStatement.setString(1, objectObjectMap.get("code").toString());
                    preparedStatement.setString(2, page.get("code").toString());
                    preparedStatement.setString(3, page.get("name").toString());
                    preparedStatement.setInt(4, (Integer) page.get("rank"));
                    preparedStatement.setString(5, page.get("url").toString());
                    preparedStatement.setString(6, "W");
                    preparedStatement.addBatch();
                    int i = 1;
                    for (String string : strings) {
                        preparedStatement1.setString(1, page.get("code").toString());
                        preparedStatement1.setString(2, Long.toString(snowflakeIdWorker.nextId()));
                        preparedStatement1.setString(3, string);
                        preparedStatement1.setInt(4, i++);
                        preparedStatement1.addBatch();
                    }
                }
            }
            preparedStatement.executeBatch();
            preparedStatement1.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Map<Object, Object>> parseDetail(List<CaricatureParam> list) throws Exception {
        List<Map<Object, Object>> pdList = new ArrayList<>();
        try {
            SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(8, 8);
            for (CaricatureParam param : list) {
                Map<Object, Object> objectObjectHashMap = new HashMap<>();
                StringBuffer url = connect("https://www.redbz.com/" + param.getUrl());
                String s = url.toString();
                String s1 = s.substring(s.indexOf("\"data\":") + 7, s.indexOf("}]);"));
                JSONArray parse = (JSONArray) JSONArray.parse(s1);
                List<Map<Object, Object>> maps = new ArrayList<>();
                for (Object o : parse) {
                    JSONObject o1 = (JSONObject) o;
                    Map<Object, Object> pageData1 = new HashMap<Object, Object>();
                    pageData1.put("id", o1.get("comic_id"));
                    pageData1.put("idTwo", o1.get("id"));
                    pageData1.put("name", o1.get("chapter_name"));
                    pageData1.put("url", o1.get("chapter_cover"));
                    pageData1.put("rank", o1.get("chapter_order"));
                    pageData1.put("code", snowflakeIdWorker.nextId());
                    getUrl(pageData1);
                    maps.add(pageData1);
                }
                objectObjectHashMap.put("code", param.getCode());
                objectObjectHashMap.put("pages", maps);
                pdList.add(objectObjectHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdList;
    }

    private static void getUrl(Map<Object, Object> pd) throws Exception {
        List<String> strings = new ArrayList<>();
        String s = "https://www.redbz.com/view/" + pd.get("id") + "/" + pd.get("idTwo");
        StringBuffer connect = new StringBuffer("");
        connect = connect(s);
        String[] split = connect.substring(connect.indexOf("\"pages\":") + 10, connect.indexOf("\"],\"url\"")).toString().split("\",\"");
        strings.addAll(Arrays.asList(split));
        pd.put("strings", strings);
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
