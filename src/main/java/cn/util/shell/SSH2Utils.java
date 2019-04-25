package cn.util.shell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
//import ch.ethz.ssh2.SCPInputStream;
//import ch.ethz.ssh2.SCPOutputStream;

import java.io.*;

public class SSH2Utils {
    //远端服务器IP地址
    private String serverIp;

    //远端服务器登录用户名
    private String userName;

    //远端服务器登录密码
    private String password;

    //远端服务器端口
    private int port;

    //远端服务器的基准目录
    private String baseDir;

    //连接对象
    private Connection connection;

    public SSH2Utils(String serverIp, String userName, String password, int port, String baseDir) throws IOException {
        this.serverIp = serverIp;
        this.userName = userName;
        this.password = password;
        this.baseDir = baseDir;
        this.connection = ShellOperateUtils.getConnection(userName, password, serverIp, port);
    }

    public void putFile(String localPathFile) {
        try {
            SCPClient scpClient = connection.createSCPClient();
            scpClient.put(localPathFile, baseDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downLoadFile(String localPathFile, String remoteFileName) {
        try {
            SCPClient scpClient = connection.createSCPClient();
            scpClient.get(baseDir + remoteFileName, localPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer doShell(String exeCMD, String split) {
        try {
            Session session = ShellOperateUtils.getSession(connection);
            InputStream inputStream = ShellOperateUtils.getInputStream(session, exeCMD);
            return ShellOperateUtils.doShell(inputStream, split);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
