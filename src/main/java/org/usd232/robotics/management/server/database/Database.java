package org.usd232.robotics.management.server.database;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.server.database.migrations.DatabaseMigrator;

/**
 * The main class representing the database
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class Database
{
    private static final Logger     LOG   = LogManager.getLogger();
    /**
     * The connection to the database
     * 
     * @since 1.0
     */
    private static final Connection DB;
    /**
     * The mutex for transactions
     * 
     * @since 1.0
     */
    private static final Semaphore  MUTEX = new Semaphore(1);
    static
    {
        Connection tmp;
        try
        {
            tmp = ConnectionConfig.getConnection();
        }
        catch (SQLException ex)
        {
            LOG.catching(ex);
            tmp = null;
        }
        DB = tmp;
        try
        {
            DatabaseMigrator.runMigrations();
        }
        catch (Exception ex)
        {
            LOG.catching(ex);
        }
    }

    public static void startTransaction(String... tables) throws SQLException
    {
        try
        {
            MUTEX.acquire();
        }
        catch (InterruptedException ex)
        {
            throw new SQLException("Thread was interrupted while waiting for transaction to start", ex);
        }
        setAutoCommit(false);
        String[] tmp = new String[tables.length];
        for (int i = 0; i < tmp.length; ++i)
        {
            tmp[i] = String.format("`%s` WRITE", tables[i]);
        }
        nativeSQL(String.format("LOCK TABLES %s `users` WRITE", String.join(", ", tmp)));
    }

    public static void commitTransaction() throws SQLException
    {
        commit();
        nativeSQL("UNLOCK TABLES");
        setAutoCommit(true);
        MUTEX.release();
    }

    public static void rollbackTransaction() throws SQLException
    {
        rollback();
        nativeSQL("UNLOCK TABLES");
        setAutoCommit(true);
        MUTEX.release();
    }

    public static void abort(Executor executor) throws SQLException
    {
        DB.abort(executor);
    }

    public static void clearWarnings() throws SQLException
    {
        DB.clearWarnings();
    }

    public static void close() throws SQLException
    {
        DB.close();
    }

    public static void commit() throws SQLException
    {
        DB.commit();
    }

    public static Array createArrayOf(String typeName, Object[] elements) throws SQLException
    {
        return DB.createArrayOf(typeName, elements);
    }

    public static Blob createBlob() throws SQLException
    {
        return DB.createBlob();
    }

    public static Clob createClob() throws SQLException
    {
        return DB.createClob();
    }

    public static NClob createNClob() throws SQLException
    {
        return DB.createNClob();
    }

    public static SQLXML createSQLXML() throws SQLException
    {
        return DB.createSQLXML();
    }

    public static Statement createStatement() throws SQLException
    {
        return DB.createStatement();
    }

    public static Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                    throws SQLException
    {
        return DB.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public static Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
    {
        return DB.createStatement(resultSetType, resultSetConcurrency);
    }

    public static Struct createStruct(String typeName, Object[] attributes) throws SQLException
    {
        return DB.createStruct(typeName, attributes);
    }

    public static boolean getAutoCommit() throws SQLException
    {
        return DB.getAutoCommit();
    }

    public static String getCatalog() throws SQLException
    {
        return DB.getCatalog();
    }

    public static Properties getClientInfo() throws SQLException
    {
        return DB.getClientInfo();
    }

    public static String getClientInfo(String name) throws SQLException
    {
        return DB.getClientInfo(name);
    }

    public static int getHoldability() throws SQLException
    {
        return DB.getHoldability();
    }

    public static DatabaseMetaData getMetaData() throws SQLException
    {
        return DB.getMetaData();
    }

    public static int getNetworkTimeout() throws SQLException
    {
        return DB.getNetworkTimeout();
    }

    public static String getSchema() throws SQLException
    {
        return DB.getSchema();
    }

    public static int getTransactionIsolation() throws SQLException
    {
        return DB.getTransactionIsolation();
    }

    public static Map<String, Class<?>> getTypeMap() throws SQLException
    {
        return DB.getTypeMap();
    }

    public static SQLWarning getWarnings() throws SQLException
    {
        return DB.getWarnings();
    }

    public static boolean isClosed() throws SQLException
    {
        return DB.isClosed();
    }

    public static boolean isReadOnly() throws SQLException
    {
        return DB.isReadOnly();
    }

    public static boolean isValid(int timeout) throws SQLException
    {
        return DB.isValid(timeout);
    }

    public static boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return DB.isWrapperFor(iface);
    }

    public static String nativeSQL(String sql) throws SQLException
    {
        return DB.nativeSQL(sql);
    }

    public static CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                    int resultSetHoldability) throws SQLException
    {
        return DB.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public static CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
                    throws SQLException
    {
        return DB.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    public static CallableStatement prepareCall(String sql) throws SQLException
    {
        return DB.prepareCall(sql);
    }

    public static PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                    int resultSetHoldability) throws SQLException
    {
        return DB.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public static PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                    throws SQLException
    {
        return DB.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public static PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
    {
        return DB.prepareStatement(sql, autoGeneratedKeys);
    }

    public static PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
    {
        return DB.prepareStatement(sql, columnIndexes);
    }

    public static PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
    {
        return DB.prepareStatement(sql, columnNames);
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException
    {
        return DB.prepareStatement(sql);
    }

    public static void releaseSavepoint(Savepoint savepoint) throws SQLException
    {
        DB.releaseSavepoint(savepoint);
    }

    public static void rollback() throws SQLException
    {
        DB.rollback();
    }

    public static void rollback(Savepoint savepoint) throws SQLException
    {
        DB.rollback(savepoint);
    }

    public static void setAutoCommit(boolean autoCommit) throws SQLException
    {
        DB.setAutoCommit(autoCommit);
    }

    public static void setCatalog(String catalog) throws SQLException
    {
        DB.setCatalog(catalog);
    }

    public static void setClientInfo(Properties properties) throws SQLClientInfoException
    {
        DB.setClientInfo(properties);
    }

    public static void setClientInfo(String name, String value) throws SQLClientInfoException
    {
        DB.setClientInfo(name, value);
    }

    public static void setHoldability(int holdability) throws SQLException
    {
        DB.setHoldability(holdability);
    }

    public static void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException
    {
        DB.setNetworkTimeout(executor, milliseconds);
    }

    public static void setReadOnly(boolean readOnly) throws SQLException
    {
        DB.setReadOnly(readOnly);
    }

    public static Savepoint setSavepoint() throws SQLException
    {
        return DB.setSavepoint();
    }

    public static Savepoint setSavepoint(String name) throws SQLException
    {
        return DB.setSavepoint(name);
    }

    public static void setSchema(String schema) throws SQLException
    {
        DB.setSchema(schema);
    }

    public static void setTransactionIsolation(int level) throws SQLException
    {
        DB.setTransactionIsolation(level);
    }

    public static void setTypeMap(Map<String, Class<?>> map) throws SQLException
    {
        DB.setTypeMap(map);
    }

    public static <T> T unwrap(Class<T> iface) throws SQLException
    {
        return DB.unwrap(iface);
    }
}
