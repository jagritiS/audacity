package edu.smude.services;

import edu.smude.utils.DataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class BaseService {

    protected DataSource dataSource;
    protected QueryRunner queryRunner;

    public BaseService() {
        this.dataSource = DataSourceFactory.getDataSource();
        this.queryRunner = new QueryRunner(this.dataSource);
    }
    
}
