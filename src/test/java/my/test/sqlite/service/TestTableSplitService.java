package my.test.sqlite.service;


import my.sqlite.base.SqliteBaseService;
import my.test.sqlite.entity.TestTableSplit;
import my.test.sqlite.dao.TestTableSplitDao;

/**
 * Sqlite[test_table]的service
 *
 * @author 欧阳洁
 * @create 2017-09-30 15:16
 **/
public class TestTableSplitService extends SqliteBaseService<TestTableSplit, TestTableSplitDao> {
    public TestTableSplitService() {// 必须要对应实现父类的构造方法
        super(TestTableSplitDao.class);// 对应的Dao类
    }
}
