package com.jason.base.log.appender;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jason.base.common.utils.PropertiesUtil;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.ErrorCode;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * log4j2 常规日志数据库链接获取工厂
 * @author jason558han
 * @date 2020年02月21日
 */
public class ConnectionFactory extends JDBCAppender {

	/* Druid数据源 */
    private DruidDataSource dataSource;
    private static ConnectionFactory connectionFactory;
    /* 数据库配置 */
    private static Properties druidPro;

    public ConnectionFactory() {
        super();
    }

    @Override
    protected void closeConnection(Connection con) {
        try {
            /* 如果数据库连接对象不为空和没有被关闭的话，关闭数据库连接 */
            if (con != null && !con.isClosed())
                con.close();
        } catch (SQLException e) {
            errorHandler.error("Error closing MyJDBCAppender.closeConnection() 's connection", e, ErrorCode.GENERIC_FAILURE);
        }
    }
 
    @Override
    protected Connection getConnection() throws SQLException {
    	if (druidPro == null) {
    		druidPro = new PropertiesUtil("/log4j2Druid.properties").getProps();
    	}
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(druidPro);
        } catch (Exception e) {
            e.printStackTrace();
            /* Druid数据库源对象产生失败后，取消初始化 */
            try {
                uninitialize();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return dataSource.getConnection();
    }
 
    /* 取消初始化 */
    public void uninitialize() {
        try {
            if (dataSource != null)
                dataSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
    }

    public static Connection getDataSourceConnection() throws SQLException {
        if (connectionFactory==null){
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory.getConnection();
    }
}