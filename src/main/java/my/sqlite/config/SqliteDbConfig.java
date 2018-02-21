package my.sqlite.config;

import my.sqlite.base.SqliteSql;
import my.sqlite.base.SqliteUtils;

public enum SqliteDbConfig {
    /**
     * 程序数据库
     */
    DB_PATH1(SqliteUtils.getClassRootPath("database/sqlite1.db")),
    DB_PATH2(SqliteUtils.getClassRootPath("database/sqlite2.db")),
    DB_PATH3(SqliteUtils.getClassRootPath("database/sqlite3.db")),
    /**
     * 测试数据库
     */
    TEST_DB_PATH(SqliteUtils.getClassRootPath("database/test.db"));
    /**
     * 路径
     */
    private String path;
    private SqliteDbConfig(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
