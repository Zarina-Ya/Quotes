package quotes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class QuotesGen {

    public synchronized String generation(){
        String res = new String();

        Map<Integer,String> mapInFile=new HashMap<Integer,String>();
        try{
            File toRead=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\quotes.txt");

            FileInputStream fis=new FileInputStream(toRead);

            Scanner sc=new Scanner(fis);

            String currentLine;
            int count =1;
            while(sc.hasNextLine()){
                currentLine=sc.nextLine();
                //now tokenize the currentLine:
                StringTokenizer st=new StringTokenizer(currentLine,":",false);
                //put tokens ot currentLine in map
                mapInFile.put(count,st.nextToken(";"));
                count++;
            }
            fis.close();


        }catch(Exception e){}

        Integer id = getRandomNumberInts(1,mapInFile.size());
        res = mapInFile.get(id).trim();

        try{
            File fileTwo=new File("C:\\tom\\apache-tomcat-9.0.56\\webapps\\Quotes\\src\\open.txt");
            FileOutputStream fos=new FileOutputStream(fileTwo);
            PrintWriter pw=new PrintWriter(fos, true);

           /* for(Map.Entry<Integer,String> m :mapInFile.entrySet()){
                pw.println(m.getKey()+"="+m.getValue());
            }*/
         pw.println(res);

            pw.flush();
            pw.close();
            fos.close();
        }catch(Exception e){}




        return res;
    }

    public static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

}
