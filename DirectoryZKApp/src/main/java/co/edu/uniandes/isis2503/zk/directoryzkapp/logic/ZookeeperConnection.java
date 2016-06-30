/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.logic;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
public class ZookeeperConnection implements Closeable {

    
    public static final String ZK_HOST = "localhost";
    public static final String PORT = "2181";
    public static final String ZOOKEEPER_SERVER = ZK_HOST + ":" + PORT;

    private static ZookeeperConnection connection = null;
    private CuratorFramework client;
    private ExponentialBackoffRetry retryPolicy;
    private int sleepTime;
    private int retries;

    private ZookeeperConnection() {
        try {
            sleepTime = 1000;
            retries = 3;
            retryPolicy = new ExponentialBackoffRetry(sleepTime, retries);
            client = CuratorFrameworkFactory.newClient(ZOOKEEPER_SERVER, retryPolicy);
            client.start();
            System.out.println("STATUS CONNECTION: "+statusConnection());
        } catch (Exception e) {
            Logger.getLogger(ZookeeperConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static CuratorFramework getZooKeeperClient() {
        if (connection == null) {
            connection = new ZookeeperConnection();
        }
        return connection.getClient();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }
    
    public String statusConnection(){
        return client.getState().name();
    }

    @Override
    public void close() throws IOException {
        client.close();
    }

}
