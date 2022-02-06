package login;
import users.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginDao {

    public synchronized void readFile( Map<String,String> mapInFile){
        try{
            File toRead=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\users.txt");
            FileInputStream fis=new FileInputStream(toRead);

            Scanner sc=new Scanner(fis);

            String currentLine;
            while(sc.hasNextLine()){
                currentLine=sc.nextLine();
                StringTokenizer st=new StringTokenizer(currentLine,":",false);
                mapInFile.put(st.nextToken(),st.nextToken(";"));
            }
            fis.close();


        }catch(Exception e){}
    }

     public  boolean validate(User user) throws ClassNotFoundException, IOException {
         boolean status = false;// пользователь не зарегистрирован

         String name = user.getUsername();
         String password = user.getPassword();

         String res = new String(":"+name + ":"+password);

         Map<String,String> mapInFile=new HashMap<String,String>();

         Integer id = null;
         readFile(mapInFile);//ЗАпишем из файла

         for (Map.Entry<String, String> book: mapInFile.entrySet()) {
             if (book.getValue().equals(res)) {
                 id = Integer.parseInt(book.getKey());
             }
         }
     /*    File a=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\check2.txt");// Запишем найденного пользователя
         FileOutputStream fos1=new FileOutputStream(a);
         PrintWriter pw1=new PrintWriter(fos1, true);
         for (Map.Entry<String, String> book: mapInFile.entrySet()) {
             if(book.getValue().equals(res)){
                 id =Integer.parseInt(book.getKey());
                 pw1.println("key: " + book.getKey() + " value: " + book.getValue()+"\n" + "ID:" + id);
             }
             else {
                 pw1.println("loX");
             }

         }

         pw1.flush();
         pw1.close();
         fos1.close();*/
    //-----------------------------------------------------------------------------------------------------------------------------------------------
         user.setID(id);
    //-----------------------------------------------------Запишем список который у нас вышел----------------------------------------------------------------
        /* try{
             File fileTwo=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\usersTable.txt");
             FileOutputStream fos=new FileOutputStream(fileTwo);
             PrintWriter pw=new PrintWriter(fos, true);

             for(Map.Entry<String,String> m :mapInFile.entrySet()){
                 pw.println(m.getKey()+"="+m.getValue());
             }

             pw.flush();
             pw.close();
             fos.close();
         }catch(Exception e){}*/

         if (id != null){
            status = true;
         }
         return status;
     }


}
