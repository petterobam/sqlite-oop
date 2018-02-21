package my.sqlite.config;

import my.sqlite.base.SqliteUtils;

/**
 * sqlite的一些静态配置
 */
public class SqliteConfig {
    /**
     * 程序数据库
     */
    public static final String DB_PATH = "database/sqlite.db";
    public static final String DB_PATH1 = "database/sqlite1.db";
    public static final String DB_PATH2 = "database/sqlite2.db";
    public static final String DB_PATH3 = "database/sqlite3.db";
    /**
     * 测试数据库
     */
    public static final String TEST_DB_PATH = SqliteUtils.getClassRootPath("database/test.db");


}
