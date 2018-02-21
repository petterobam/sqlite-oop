import my.sqlite.base.SqliteHelper;
import my.sqlite.entity.TestTable;
import my.sqlite.service.TestTableService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.my.utils.MyDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * sqlite测试
 *
 * @author 欧阳洁
 * @create 2017-09-29 18:10
 **/
public class SqliteTest {
    protected static final Logger logger = LoggerFactory.getLogger(SqliteTest.class);

    @Test
    public void test1() throws ClassNotFoundException {
        SqliteHelper.test();
    }

    @Test
    public void test2() {
        TestTableService sqliteService = new TestTableService();//没有使用spring注入，暂时自己构建
        TestTable entity = new TestTable();
        entity.setName("test1");
        entity.setAuthor("petter");
        entity.setArticle("article1");
        entity.setCreateTime(MyDate.getStringDate());
        sqliteService.insert(entity);
        entity.setName("title2");
        entity.setAuthor("bob");
        entity.setArticle("article2");
        entity.setCreateTime(MyDate.getStringDate());
        sqliteService.insert(entity);

        TestTable queryEntity = new TestTable();
        sqliteService.query(queryEntity);
        queryEntity.setAuthor("petter");
        sqliteService.query(queryEntity);
        queryEntity.setName("test");
        sqliteService.query(queryEntity);
        queryEntity.setId(1);
        sqliteService.query(queryEntity);
    }

    @Test
    public void test3() {
        TestTableService sqliteService = new TestTableService();//没有使用spring注入，暂时自己构建
        List<TestTable> list = sqliteService.getByName("test");
    }

    @Test
    public void test4() {
        TestTableService sqliteService = new TestTableService();//没有使用spring注入，暂时自己构建
        List<TestTable> list = sqliteService.getByNameOrId("title", 1);
    }
}
