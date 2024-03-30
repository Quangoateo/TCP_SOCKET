package Exercise2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Finding_LCM{
   public int gcd(int x,int y)
   {
     if (y == 0) {
        return x;
    } else {
        return (gcd(y, x%y));
    }
   }
   public int lcm(int x ,int y)
   {
       return (x*y)/ gcd(x,y);
   }
   public int lcm(int x, int y,int z)
   {
       int lcm_xy = lcm(x,y);
       return lcm(lcm_xy,z);
   }
}

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(1235)) {
            Socket con = ss.accept();
            System.out.println(" Successfully connecting....");
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            int number1 = in.readInt();
            int number2 = in.readInt();

            //caculate the LCM Of a and b back to the client
            Finding_LCM obj = new Finding_LCM();
            int LCM = obj.lcm(number2, number2);
            out.writeInt(LCM);
            con.close();
            ss.close();
        } catch (Exception e) {

        }
    }
}
