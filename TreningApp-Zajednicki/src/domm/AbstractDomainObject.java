package domm;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// apstraktna klasa za sve domenske objekte koji se cuvaju u bazi

public abstract class AbstractDomainObject implements Serializable {

    public abstract String tableName();

    public abstract String alias();

    public abstract String join();

    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;

    public abstract String columnsForInsert();

    public abstract String requirement();

    public abstract String valuesForInsert();

    public abstract String valuesForUpdate();

    public abstract String requirementForSelect(Object o);
}