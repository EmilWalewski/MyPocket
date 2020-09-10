package com.mypocket.storeManagement.utilities;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

import java.sql.Types;

public class CustomMySQLDialect extends MySQL5Dialect {

    public CustomMySQLDialect() {
        super();
        registerHibernateType(Types.OTHER, ReceiptDate.class.getName());
    }
}
