package users;
public class User {
    private String username;
    private String password;
    private Integer ID;

    @Override
    public String toString() {
        return   username +":"+ password ;

    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//javac -cp C:\tom\apache-tomcat-9.0.56\lib\servlet-api.jar src/controller/*.java src/users/*.java src/login/*.java src/quotes/*.java src/servlet/*.java -d WEB-INF/classes