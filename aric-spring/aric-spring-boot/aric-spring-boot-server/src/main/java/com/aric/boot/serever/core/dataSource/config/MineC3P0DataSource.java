package com.aric.boot.serever.core.dataSource.config;

import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.naming.Referenceable;
import javax.sql.DataSource;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * c3p0连接池
 */
@ConfigurationProperties(prefix = "system.datasource.c3p0")
public class MineC3P0DataSource extends AbstractPoolBackedDataSource implements PooledDataSource, Serializable, Referenceable {
    static final MLogger logger;
    static final Set TO_STRING_IGNORE_PROPS;
    transient DriverManagerDataSource dmds;
    transient WrapperConnectionPoolDataSource wcpds;
    private static final long serialVersionUID = 1L;
    private static final short VERSION = 1;

    public MineC3P0DataSource() {
        this(true);
    }

    public MineC3P0DataSource(boolean autoregister) {
        super(autoregister);
        this.dmds = new DriverManagerDataSource();
        this.wcpds = new WrapperConnectionPoolDataSource();
        this.wcpds.setNestedDataSource(this.dmds);

        try {
            this.setConnectionPoolDataSource(this.wcpds);
        } catch (PropertyVetoException var3) {
            logger.log(MLevel.WARNING, "Hunh??? This can\'t happen. We haven\'t set up any listeners to veto the property change yet!", var3);
            throw new RuntimeException("Hunh??? This can\'t happen. We haven\'t set up any listeners to veto the property change yet! " + var3);
        }

        this.setUpPropertyEvents();
    }

    private void setUpPropertyEvents() {
        VetoableChangeListener wcpdsConsistencyEnforcer = new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                String propName = evt.getPropertyName();
                Object val = evt.getNewValue();
                if("connectionPoolDataSource".equals(propName)) {
                    if(!(val instanceof WrapperConnectionPoolDataSource)) {
                        throw new PropertyVetoException("ComboPooledDataSource requires that its ConnectionPoolDataSource  be set at all times, and that it be a com.mchange.v2.c3p0.WrapperConnectionPoolDataSource. Bad: " + val, evt);
                    }

                    DataSource nested = ((WrapperConnectionPoolDataSource)val).getNestedDataSource();
                    if(!(nested instanceof DriverManagerDataSource)) {
                        throw new PropertyVetoException("ComboPooledDataSource requires that its unpooled DataSource  be set at all times, and that it be a com.mchange.v2.c3p0.DriverManagerDataSource. Bad: " + nested, evt);
                    }
                }

            }
        };
        this.addVetoableChangeListener(wcpdsConsistencyEnforcer);
        PropertyChangeListener wcpdsStateUpdater = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                MineC3P0DataSource.this.updateLocalVarsFromCpdsProp();
            }
        };
        this.addPropertyChangeListener(wcpdsStateUpdater);
    }

    private void updateLocalVarsFromCpdsProp() {
        this.wcpds = (WrapperConnectionPoolDataSource)this.getConnectionPoolDataSource();
        this.dmds = (DriverManagerDataSource)this.wcpds.getNestedDataSource();
    }

    public MineC3P0DataSource(String configName) {
        this();
        this.initializeNamedConfig(configName);
    }

    public String getDescription() {
        return this.dmds.getDescription();
    }

    public void setDescription(String description) {
        this.dmds.setDescription(description);
    }

    public String getDriverClass() {
        return this.dmds.getDriverClass();
    }

    public void setDriverClass(String driverClass) throws PropertyVetoException {
        this.dmds.setDriverClass(driverClass);
    }

    public String getJdbcUrl() {
        return this.dmds.getJdbcUrl();
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.dmds.setJdbcUrl(jdbcUrl);
        this.resetPoolManager(false);
    }

    public Properties getProperties() {
        return this.dmds.getProperties();
    }

    public void setProperties(Properties properties) {
        this.dmds.setProperties(properties);
        this.resetPoolManager(false);
    }

    public String getUser() {
        return this.dmds.getUser();
    }

    public void setUser(String user) {
        this.dmds.setUser(user);
        this.resetPoolManager(false);
    }

    public String getPassword() {
        return this.dmds.getPassword();
    }

    public void setPassword(String password) {
        this.dmds.setPassword(password);
        this.resetPoolManager(false);
    }

    public int getCheckoutTimeout() {
        return this.wcpds.getCheckoutTimeout();
    }

    public void setCheckoutTimeout(int checkoutTimeout) {
        this.wcpds.setCheckoutTimeout(checkoutTimeout);
        this.resetPoolManager(false);
    }

    public int getAcquireIncrement() {
        return this.wcpds.getAcquireIncrement();
    }

    public void setAcquireIncrement(int acquireIncrement) {
        this.wcpds.setAcquireIncrement(acquireIncrement);
        this.resetPoolManager(false);
    }

    public int getAcquireRetryAttempts() {
        return this.wcpds.getAcquireRetryAttempts();
    }

    public void setAcquireRetryAttempts(int acquireRetryAttempts) {
        this.wcpds.setAcquireRetryAttempts(acquireRetryAttempts);
        this.resetPoolManager(false);
    }

    public int getAcquireRetryDelay() {
        return this.wcpds.getAcquireRetryDelay();
    }

    public void setAcquireRetryDelay(int acquireRetryDelay) {
        this.wcpds.setAcquireRetryDelay(acquireRetryDelay);
        this.resetPoolManager(false);
    }

    public boolean isAutoCommitOnClose() {
        return this.wcpds.isAutoCommitOnClose();
    }

    public void setAutoCommitOnClose(boolean autoCommitOnClose) {
        this.wcpds.setAutoCommitOnClose(autoCommitOnClose);
        this.resetPoolManager(false);
    }

    public String getConnectionTesterClassName() {
        return this.wcpds.getConnectionTesterClassName();
    }

    public void setConnectionTesterClassName(String connectionTesterClassName) throws PropertyVetoException {
        this.wcpds.setConnectionTesterClassName(connectionTesterClassName);
        this.resetPoolManager(false);
    }

    public String getAutomaticTestTable() {
        return this.wcpds.getAutomaticTestTable();
    }

    public void setAutomaticTestTable(String automaticTestTable) {
        this.wcpds.setAutomaticTestTable(automaticTestTable);
        this.resetPoolManager(false);
    }

    public boolean isForceIgnoreUnresolvedTransactions() {
        return this.wcpds.isForceIgnoreUnresolvedTransactions();
    }

    public void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions) {
        this.wcpds.setForceIgnoreUnresolvedTransactions(forceIgnoreUnresolvedTransactions);
        this.resetPoolManager(false);
    }

    public int getIdleConnectionTestPeriod() {
        return this.wcpds.getIdleConnectionTestPeriod();
    }

    public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
        this.wcpds.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        this.resetPoolManager(false);
    }

    public int getInitialPoolSize() {
        return this.wcpds.getInitialPoolSize();
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.wcpds.setInitialPoolSize(initialPoolSize);
        this.resetPoolManager(false);
    }

    public int getMaxIdleTime() {
        return this.wcpds.getMaxIdleTime();
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.wcpds.setMaxIdleTime(maxIdleTime);
        this.resetPoolManager(false);
    }

    public int getMaxPoolSize() {
        return this.wcpds.getMaxPoolSize();
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.wcpds.setMaxPoolSize(maxPoolSize);
        this.resetPoolManager(false);
    }

    public int getMaxStatements() {
        return this.wcpds.getMaxStatements();
    }

    public void setMaxStatements(int maxStatements) {
        this.wcpds.setMaxStatements(maxStatements);
        this.resetPoolManager(false);
    }

    public int getMaxStatementsPerConnection() {
        return this.wcpds.getMaxStatementsPerConnection();
    }

    public void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
        this.wcpds.setMaxStatementsPerConnection(maxStatementsPerConnection);
        this.resetPoolManager(false);
    }

    public int getMinPoolSize() {
        return this.wcpds.getMinPoolSize();
    }

    public void setMinPoolSize(int minPoolSize) {
        this.wcpds.setMinPoolSize(minPoolSize);
        this.resetPoolManager(false);
    }

    public String getOverrideDefaultUser() {
        return this.wcpds.getOverrideDefaultUser();
    }

    public void setOverrideDefaultUser(String overrideDefaultUser) {
        this.wcpds.setOverrideDefaultUser(overrideDefaultUser);
        this.resetPoolManager(false);
    }

    public String getOverrideDefaultPassword() {
        return this.wcpds.getOverrideDefaultPassword();
    }

    public void setOverrideDefaultPassword(String overrideDefaultPassword) {
        this.wcpds.setOverrideDefaultPassword(overrideDefaultPassword);
        this.resetPoolManager(false);
    }

    public int getPropertyCycle() {
        return this.wcpds.getPropertyCycle();
    }

    public void setPropertyCycle(int propertyCycle) {
        this.wcpds.setPropertyCycle(propertyCycle);
        this.resetPoolManager(false);
    }

    public boolean isBreakAfterAcquireFailure() {
        return this.wcpds.isBreakAfterAcquireFailure();
    }

    public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
        this.wcpds.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
        this.resetPoolManager(false);
    }

    public boolean isTestConnectionOnCheckout() {
        return this.wcpds.isTestConnectionOnCheckout();
    }

    public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
        this.wcpds.setTestConnectionOnCheckout(testConnectionOnCheckout);
        this.resetPoolManager(false);
    }

    public boolean isTestConnectionOnCheckin() {
        return this.wcpds.isTestConnectionOnCheckin();
    }

    public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
        this.wcpds.setTestConnectionOnCheckin(testConnectionOnCheckin);
        this.resetPoolManager(false);
    }

    public boolean isUsesTraditionalReflectiveProxies() {
        return this.wcpds.isUsesTraditionalReflectiveProxies();
    }

    public void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
        this.wcpds.setUsesTraditionalReflectiveProxies(usesTraditionalReflectiveProxies);
        this.resetPoolManager(false);
    }

    public String getPreferredTestQuery() {
        return this.wcpds.getPreferredTestQuery();
    }

    public void setPreferredTestQuery(String preferredTestQuery) {
        this.wcpds.setPreferredTestQuery(preferredTestQuery);
        this.resetPoolManager(false);
    }

    public String getUserOverridesAsString() {
        return this.wcpds.getUserOverridesAsString();
    }

    public void setUserOverridesAsString(String userOverridesAsString) throws PropertyVetoException {
        this.wcpds.setUserOverridesAsString(userOverridesAsString);
        this.resetPoolManager(false);
    }

    public int getMaxAdministrativeTaskTime() {
        return this.wcpds.getMaxAdministrativeTaskTime();
    }

    public void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime) {
        this.wcpds.setMaxAdministrativeTaskTime(maxAdministrativeTaskTime);
        this.resetPoolManager(false);
    }

    public int getMaxIdleTimeExcessConnections() {
        return this.wcpds.getMaxIdleTimeExcessConnections();
    }

    public void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
        this.wcpds.setMaxIdleTimeExcessConnections(maxIdleTimeExcessConnections);
        this.resetPoolManager(false);
    }

    public int getMaxConnectionAge() {
        return this.wcpds.getMaxConnectionAge();
    }

    public void setMaxConnectionAge(int maxConnectionAge) {
        this.wcpds.setMaxConnectionAge(maxConnectionAge);
        this.resetPoolManager(false);
    }

    public String getConnectionCustomizerClassName() {
        return this.wcpds.getConnectionCustomizerClassName();
    }

    public void setConnectionCustomizerClassName(String connectionCustomizerClassName) {
        this.wcpds.setConnectionCustomizerClassName(connectionCustomizerClassName);
        this.resetPoolManager(false);
    }

    public int getUnreturnedConnectionTimeout() {
        return this.wcpds.getUnreturnedConnectionTimeout();
    }

    public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
        this.wcpds.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);
        this.resetPoolManager(false);
    }

    public boolean isDebugUnreturnedConnectionStackTraces() {
        return this.wcpds.isDebugUnreturnedConnectionStackTraces();
    }

    public void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces) {
        this.wcpds.setDebugUnreturnedConnectionStackTraces(debugUnreturnedConnectionStackTraces);
        this.resetPoolManager(false);
    }

    public String getFactoryClassLocation() {
        return super.getFactoryClassLocation();
    }

    public void setFactoryClassLocation(String factoryClassLocation) {
        this.dmds.setFactoryClassLocation(factoryClassLocation);
        this.wcpds.setFactoryClassLocation(factoryClassLocation);
        super.setFactoryClassLocation(factoryClassLocation);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(512);
        sb.append(this.getClass().getName());
        sb.append(" [ ");

        try {
            BeansUtils.appendPropNamesAndValues(sb, this, TO_STRING_IGNORE_PROPS);
        } catch (Exception var3) {
            sb.append(var3.toString());
        }

        sb.append(" ]");
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeShort(1);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        short version = ois.readShort();
        switch(version) {
            case 1:
                this.updateLocalVarsFromCpdsProp();
                this.setUpPropertyEvents();
                return;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

    static {
        logger = MLog.getLogger(MineC3P0DataSource.class);
        TO_STRING_IGNORE_PROPS = new HashSet(Arrays.asList(new String[]{"connection", "lastAcquisitionFailureDefaultUser", "lastCheckinFailureDefaultUser", "lastCheckoutFailureDefaultUser", "lastConnectionTestFailureDefaultUser", "lastIdleTestFailureDefaultUser", "logWriter", "loginTimeout", "numBusyConnections", "numBusyConnectionsAllUsers", "numBusyConnectionsDefaultUser", "numConnections", "numConnectionsAllUsers", "numConnectionsDefaultUser", "numFailedCheckinsDefaultUser", "numFailedCheckoutsDefaultUser", "numFailedIdleTestsDefaultUser", "numIdleConnections", "numIdleConnectionsAllUsers", "numIdleConnectionsDefaultUser", "numUnclosedOrphanedConnections", "numUnclosedOrphanedConnectionsAllUsers", "numUnclosedOrphanedConnectionsDefaultUser", "numUserPools", "effectivePropertyCycleDefaultUser", "startTimeMillisDefaultUser", "statementCacheNumCheckedOutDefaultUser", "statementCacheNumCheckedOutStatementsAllUsers", "statementCacheNumConnectionsWithCachedStatementsAllUsers", "statementCacheNumConnectionsWithCachedStatementsDefaultUser", "statementCacheNumStatementsAllUsers", "statementCacheNumStatementsDefaultUser", "threadPoolSize", "threadPoolNumActiveThreads", "threadPoolNumIdleThreads", "threadPoolNumTasksPending", "threadPoolStackTraces", "threadPoolStatus", "overrideDefaultUser", "overrideDefaultPassword", "password", "reference", "upTimeMillisDefaultUser", "user", "userOverridesAsString", "allUsers", "connectionPoolDataSource"}));
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
