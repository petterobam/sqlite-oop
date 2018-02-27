package my.sqlite.console;

import my.sqlite.utils.SqliteHelper;
import my.sqlite.utils.SqliteUtils;

public class SqliteConsoleBaseService {
    /**
     * 执行控制台语句
     *
     * @param sql
     * @return
     */
    public SqliteConsoleBaseEntity excute(String sql) {
        return this.baseDao.excute(sql);
    }
    private SqliteConsoleBaseDao baseDao;

    public SqliteConsoleBaseService(String dbPath, boolean absolute) {
        this.baseDao = new SqliteConsoleBaseDao(dbPath, absolute);
    }

    public SqliteConsoleBaseService(String dbPath) {
        this.baseDao = new SqliteConsoleBaseDao(dbPath);
    }

    public SqliteConsoleBaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(SqliteConsoleBaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
