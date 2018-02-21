package my.sqlite.base;

import my.sqlite.config.SqliteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础操作
 *
 * @author 欧阳洁
 * @create 2017-09-29 17:48
 **/
public class SqliteHelper {
    private static final String DB_PATH = SqliteUtils.getClassRootPath(SqliteConfig.DB_PATH);
    private static final String TEST_DB_PATH = SqliteUtils.getClassRootPath(SqliteConfig.TEST_DB_PATH);

    static {//加载驱动器
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入
     *
     * @param sql
     * @return
     */
    public static int insert(String sql) {
        return execute(sql);
    }

    /**
     * 修改
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        return execute(sql);
    }

    /**
     * 删除
     *
     * @param sql
     * @return
     */
    public static int delete(String sql) {
        return execute(sql);
    }

    /**
     * 插入，带参数
     *
     * @param sql
     * @return
     */
    public static int insert(String sql, List<Object> param) {
        return execute(sql, param);
    }

    /**
     * 修改，带参数
     *
     * @param sql
     * @return
     */
    public static int update(String sql, List<Object> param) {
        return execute(sql, param);
    }

    /**
     * 删除，带参数
     *
     * @param sql
     * @return
     */
    public static int delete(String sql, List<Object> param) {
        return execute(sql, param);
    }

    /**
     * 创建
     *
     * @param sql
     * @return
     */
    public static int create(String sql) {
        return execute(sql);
    }

    private static String getDBUrl(String dbPath) {
        String JDBC = "jdbc:sqlite:/" + dbPath;
        if (SqliteUtils.isWindows()) {
            dbPath = dbPath.toLowerCase();
            JDBC = "jdbc:sqlite:/" + dbPath;
        }
        return JDBC;
    }

    /**
     * 查询语句执行，返回list格式的json字符串
     *
     * @param sql
     * @return
     */
    public static String query(String sql, Map<String, String> columnMap) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(getDBUrl(DB_PATH));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(300); // set timeout to 30 sec.
            System.out.println("执行查询语句==> " + sql);
            ResultSet rs = statement.executeQuery(sql);

            return getDataJson(rs, columnMap);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询语句执行，返回list格式的json字符串，带参数
     *
     * @param sql
     * @return
     */
    public static String query(String sql, List<Object> param, Map<String, String> columnMap) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(getDBUrl(DB_PATH));
            System.out.println("执行查询语句==> " + sql);
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setQueryTimeout(300);
            if (SqliteUtils.isNotEmpty(param)) {
                int count = 1;
                for (Object o : param) {
                    prep.setObject(count++, o);
                }
            }

            ResultSet rs = prep.executeQuery();

            return getDataJson(rs, columnMap);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 非查询语句执行，带参数的
     *
     * @param sql
     * @return
     */
    public static int execute(String sql, List<Object> param) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(getDBUrl(DB_PATH));
            System.out.println("执行非查询语句==> " + sql);
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setQueryTimeout(30);
            if (SqliteUtils.isNotEmpty(param)) {
                int count = 1;
                for (Object o : param) {
                    prep.setObject(count++, o);
                }
            }
            int result = prep.executeUpdate();
            System.out.println("执行非查询语句影响行数==> " + result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 非查询语句执行，无参数
     *
     * @param sql
     * @return
     */
    public static int execute(String sql) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(getDBUrl(DB_PATH));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            System.out.println("执行非查询语句==> " + sql);
            int result = statement.executeUpdate(sql);
            System.out.println("执行非查询语句影响行数==> " + result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据结果集返回数据json
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static String getDataJson(ResultSet rs, Map<String, String> columnMap) throws SQLException {
        String[] nameArr = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        int rows = 1;
        while (rs.next()) {
            if (rows++ == 1) {
                nameArr = getNameArr(rs);// 获取列名
            }

            Map<String, Object> one = new LinkedHashMap<String, Object>();
            for (int i = 0; i < nameArr.length; i++) {
                String nameKey = null == columnMap ? nameArr[i] : columnMap.get(nameArr[i]);
                nameKey = null == nameKey ? nameArr[i] : nameKey;
                one.put(nameKey, rs.getObject(i + 1));
            }
            result.add(one);
        }
        String dataStr = SqliteUtils.getJsonList(result);
        System.out.println("执行查询语句结果==> " + dataStr);
        return dataStr;
    }

    /**
     * 根据结果集返回列集合
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static String[] getNameArr(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        String[] nameArr = new String[count];
        for (int i = 0; i < count; i++) {
            nameArr[i] = rsmd.getColumnName(i + 1);
            nameArr[i] = null == nameArr[i] ? "null" : nameArr[i].toLowerCase();
        }
        return nameArr;
    }


    /*————————————————测试sqlite————————————————*/
    public static void test() throws ClassNotFoundException {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection(getDBUrl(TEST_DB_PATH));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate("create table if not exists person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            String[] name = new String[count];
            for (int i = 0; i < count; i++) {
                name[i] = rsmd.getColumnName(i + 1);
            }
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

            while (rs.next()) {
                Map<String, Object> one = new LinkedHashMap<String, Object>();

                for (int i = 0; i < name.length; i++) {
                    Object value = rs.getObject(i + 1);
                    System.out.println(name[i] + ":" + value);
                    one.put(name[i], value);
                }
                result.add(one);
            }
            String dataRes = SqliteUtils.getJsonList(result);
            System.out.println(dataRes);

            PreparedStatement prep = connection.prepareStatement(
                    "insert into person values (?, ?)");
            prep.setObject(1, 5);
            prep.setObject(2, "asdfasdfas");
            prep.execute();
            prep = connection.prepareStatement(
                    "select * from person where id=?");
            prep.setObject(1, 5);
            rs = prep.executeQuery();
            while (rs.next()) {
                System.out.println("id = " + rs.getString("id"));
                System.out.println("name = " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*————————————————测试sqlite————————————————*/
}
