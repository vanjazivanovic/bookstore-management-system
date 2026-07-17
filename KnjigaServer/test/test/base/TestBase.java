package test.base;

import db.DBBroker;
import org.junit.BeforeClass;

public abstract class TestBase {

    @BeforeClass
    public static void init() throws Exception {
        DBBroker.getInstance().getConnection();
    }
}