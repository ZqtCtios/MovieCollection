package workBean;
import entity.*;
import javax.ejb.EJB;
import facade.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
@ManagedBean(name="login")
@SessionScoped
public class Login {
    private String uid;
    private String password;
    private String name;
    private String msg;
    private User u;
    @EJB
    private UserFacade uf;
    @EJB
    UidFacade udf;
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserFacade getUser() {
        return uf;
    }

    public void setUser(UserFacade uf) {
        this.uf = uf;
    }
    public String getMsg()
    {
        return msg;
    }
    public void setMsg(String msg)
    {
        this.msg=msg;
    }
    public String test()
    {
        u=uf.find(uid);
        if (u!=null&&password.equals(u.getPassword()))
        {
            if(uid.equals("admin"))
                return "manager";
            this.msg="登录成功";
            this.name=u.getName();
            Uid u=udf.find(1);
            udf.remove(u);
            u=new Uid();
            u.setId(1);
            u.setUid(uid);
            udf.create(u);
            
            return "recommended";
        }
        this.msg="登录失败 用户名或密码错误";
        return "";   
    }
    public String Gname()
    {
        return name;
    }
}