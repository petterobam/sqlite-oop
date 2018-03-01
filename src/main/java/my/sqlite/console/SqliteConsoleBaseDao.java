package my.sqlite.console;

import my.sqlite.utils.SqliteHelper;
import my.sqlite.utils.SqliteUtils;

/**
 * 控制台返回的基本Dao
 *
 * @author 欧阳洁
 */
public class SqliteConsoleBaseDao {
    /**
     * 执行控制台语句
     *
     * @param sql
     * @return
     */
    public SqliteConsoleBaseEntity excute(String sql) {
        if (SqliteUtils.isBlank(sql)) {
            return null;
        }
        String sqlLower = sql.toLowerCase().trim();
        if (sqlLower.startsWith("insert ") || sqlLower.startsWith("delete ")
                || sqlLower.startsWith("update ") || sqlLower.startsWith("create ")) {
            return this.sqliteHelper.executeForConsole(sql);
        } else {
            return this.sqliteHelper.queryForConsole(sql);
        }
    }

    private SqliteHelper sqliteHelper;

    public SqliteConsoleBaseDao(String dbPath, boolean absolute) {
        this.sqliteHelper = new SqliteHelper(dbPath, absolute);
    }

    public SqliteConsoleBaseDao(String dbPath) {
        this.sqliteHelper = new SqliteHelper(dbPath);
    }

    public SqliteHelper getSqliteHelper() {
        return sqliteHelper;
    }

    public void setSqliteHelper(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }
}
