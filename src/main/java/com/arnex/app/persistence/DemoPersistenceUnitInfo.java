package com.arnex.app.persistence;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

public class DemoPersistenceUnitInfo implements PersistenceUnitInfo {

    @Override
    public void addTransformer(ClassTransformer arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransformer'");
    }

    @Override
    public boolean excludeUnlistedClasses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excludeUnlistedClasses'");
    }

    @Override
    public ClassLoader getClassLoader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClassLoader'");
    }

    @Override
    public List<URL> getJarFileUrls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJarFileUrls'");
    }

    @Override
    public DataSource getJtaDataSource() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJtaDataSource'");
    }

    @Override
    public List<String> getManagedClassNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManagedClassNames'");
    }

    @Override
    public List<String> getMappingFileNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMappingFileNames'");
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNewTempClassLoader'");
    }

    @Override
    public DataSource getNonJtaDataSource() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/library");
            dataSource.setUser("root");
            dataSource.setPassword("mariadb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public String getPersistenceUnitName() {
        return "library_persistence_unit";
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersistenceUnitRootUrl'");
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersistenceXMLSchemaVersion'");
    }

    @Override
    public Properties getProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProperties'");
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSharedCacheMode'");
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public ValidationMode getValidationMode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValidationMode'");
    }
    
}
