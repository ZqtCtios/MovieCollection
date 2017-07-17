package workBean;
import entity.User;
import javax.ejb.EJB;
import facade.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SignUp {
    private String uid;
    private String password;
    private String repsword;
    private String name;
    private String msg;
    private User u;
    @EJB
    private UserFacade uf;

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRepsword() {
        return repsword;
    }

    public void setRepsword(String repsword) {
        this.repsword = repsword;
    }

    public String getName() {
        return name;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public void test()
    {
        if (password.equals(repsword)) 
        {
            u=uf.find(uid);
            if (u!=null&&u.getUid().equals(uid))
            {
                 msg="用户已经被注册"; 
                 
            }    
            else
            {
                msg="注册成功";
                u=new User();
                u.setName(name);
                u.setPassword(password);
                u.setUid(uid);
                uf.create(u);
            }
        }else
        {
            msg="密码不一致";
        }
    }
}

