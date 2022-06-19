package workus.core;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class TableNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Identifier prefixedTableName = new Identifier("WU_" + name.getText().toUpperCase(), name.isQuoted());
        return super.toPhysicalTableName(prefixedTableName, context);
    }
}
