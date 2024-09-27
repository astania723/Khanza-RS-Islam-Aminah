/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import AESsecurity.*;
import com.mysql.jdbc.jdbc2.optional.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author khanzasoft
 */
public class koneksiDBVANSLAB {
    private static Connection connection=null;
    private static final Properties prop = new Properties();  
    private static final MysqlDataSource dataSource=new MysqlDataSource();
    
    public static Connection condb(){ 
        if(connection == null){
            try{
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                dataSource.setURL("jdbc:mysql://"+EnkripsiAES.decrypt(prop.getProperty("HOSTVANSLAB"))+":"+EnkripsiAES.decrypt(prop.getProperty("PORTVANSLAB"))+"/"+EnkripsiAES.decrypt(prop.getProperty("DATABASEVANSLAB"))+"?zeroDateTimeBehavior=convertToNull&amp;autoReconnect=true");
                dataSource.setUser(EnkripsiAES.decrypt(prop.getProperty("USERVANSLAB")));
                dataSource.setPassword(EnkripsiAES.decrypt(prop.getProperty("PASVANSLAB")));
                connection=dataSource.getConnection();       
                System.out.println("  Koneksi Berhasil. Menyambungkan ke database bridging VANSLAB...!!!");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Koneksi ke server bridging VANSLAB terputus : "+e);
            }
        }
        return connection;        
    }
    public koneksiDBVANSLAB(){}
    
}
