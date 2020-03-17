package com.orgfitech.jsf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum ConnectionManager {

	INSTANCE;
	
	
    private DataSource ds = null;
    private Lock connectionLock = new ReentrantLock();

    ConnectionManager() {
      try {
         final Context initCtx = new InitialContext();
         final Context envCtx = (Context) initCtx.lookup("java:comp/env/jdbc/ocm");
         ds = (DataSource) envCtx.lookup("jdbc/ocm");
      } catch (NamingException e) {
         e.printStackTrace();
      }
    }

   public Connection getConnection() throws SQLException {
      if(ds == null) return null;

      Connection conn = null;
      connectionLock.lock();
      try {
          conn = ds.getConnection();
      } finally {
          connectionLock.unlock();
      }

      return conn;
   }
}
