package cn.util.shell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *  zxq
 */
public class ShellOperateUtils {

    /**
     * 获取cmd 连接流
     * @param session
     * @param cmd
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(Session session, String cmd) throws IOException {

        InputStream stdout = null;

        if (session!=null) {
// 执行cmd
            session.execCommand(cmd);
            stdout = new StreamGobbler(session.getStdout());
        }
        return stdout;
    }

    /**
     * 获取session
     * @param conn
     * @return
     * @throws IOException
     */
    public static Session getSession(Connection conn) throws IOException {

        Session sess = null;
        if (conn != null) {
            sess = conn.openSession();
        }
        return sess;
    }

    /**
     * 获取连接
     * @param username
     * @param password
     * @param host
     * @param port
     * @return
     * @throws IOException
     */
    public static Connection getConnection(String username, String password, String host, int port) throws IOException {
        Connection conn = new Connection(host, port);
        conn.connect(); // make sure the connection is opened
        boolean isAuthenticated = conn.authenticateWithPassword(username,password);
        if (isAuthenticated==true) {
            return conn;
        }
        return null;
    }

    /**
     *处理shell 处理stdout流对应的数据
      * @param stdout 流
     * @param split 获取的每行的信息 - 为分割符
     * @return
     * @throws Exception
     */
    public static StringBuffer doShell(InputStream stdout, String split) throws Exception{
        StringBuffer stringBuffer = new StringBuffer();
        if (stdout != null) {
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stdout));
            String line ="";
            while (line != null) {
                line = br.readLine();
//                System.out.println(line);
                stringBuffer.append(line + split);
            }
        }
        return stringBuffer;
    }
    //关闭流
    public static void closeStream(InputStream inputStream) {
        if (inputStream!=null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭会话
    public static void closeSession(Session session) {
        if (session!=null) {
            session.close();
        }
    }
    //关闭连接
    public static void closeConnection(Connection connection) {
        if (connection!=null) {
            connection.close();
        }
    }
}
