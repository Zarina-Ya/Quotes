package users;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

//добавить пользователя в файл
public class UserDao {


    public synchronized void registerEmployee(User employee) throws IOException {

        File file = new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\users.txt");
        FileWriter writer = new FileWriter(file, true);
        String packString = new String(employee.getID() + ":" + employee.getUsername() + ":" +employee.getPassword() + ";");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(packString + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();

      /*  mapUsers.put(employee.getID(), employee);

        try{
            File fileTwo=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\users.txt");
            FileOutputStream fos=new FileOutputStream(fileTwo);
            PrintWriter pw=new PrintWriter(fos, true);

            for(Map.Entry<Integer,User> m :mapUsers.entrySet()){
                pw.println(m.getKey()+"="+m.getValue());
            }

            pw.flush();
            pw.close();
            fos.close();
        }catch(Exception e){}*/
    }

    public synchronized void checkFile( Map<String,String> mapInFile){
        try{
            File toRead=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\users.txt");
            FileInputStream fis=new FileInputStream(toRead);

            Scanner sc=new Scanner(fis);

            String currentLine;
            while(sc.hasNextLine()){
                currentLine=sc.nextLine();
                //now tokenize the currentLine:
                StringTokenizer st=new StringTokenizer(currentLine,":",false);
                //put tokens ot currentLine in map
                mapInFile.put(st.nextToken(),st.nextToken(";"));
            }
            fis.close();


        }catch(Exception e){}
    }

    public boolean validate(User user) throws ClassNotFoundException, IOException {
        boolean status = false;// пользователь не зарегистрирован
        String name = user.getUsername();
        String password = user.getPassword();

        String res = new String(":"+name + ":"+password);

        Map<String,String> mapInFile=new HashMap<String,String>();

        checkFile(mapInFile);//ЗАпишем из файла

        for (Map.Entry<String, String> book: mapInFile.entrySet()) {
            if (book.getValue().equals(res)) {
               status = true;
            }
        }

        return status;
    }



}
