package so;

import db.DBBroker;
import domm.AbstractDomainObject;
import java.sql.SQLException;

// Template Method Pattern !!!  
// svaka sistemska operacija ima validate() i execute(), 
// templateExecute() ih pozove u nizu i handluje se za commit/rollback.

public abstract class AbstractSO {
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
