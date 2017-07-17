/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workBean;
import entity.*;
import java.util.*;
import javax.ejb.EJB;
import facade.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ctios
 */
@ManagedBean
@SessionScoped
public class ManagerBean {
    private List<Movie> m;
    private List<Collect> c;
    private List<User> u;
     private int choice=0;
     private boolean yes;
    @EJB
    MovieFacade mf;
    @EJB
    CollectFacade cf;
    @EJB
    UserFacade uf;
        public void setM(List<Movie> m) {
        this.m = m;
    }
   
    public List<Movie> getM() {
        return m;
    }

    public void setU(List<User> u) {
        this.u = u;
    }

    public List<User> getU() {
        return u;
    }

    public void setC(List<Collect> c) {
        this.c = c;
    }

    public List<Collect> getC() {
        return c;
    }
    public void setChoice(int choice)
    {
        this.choice=choice;
    }
    public int getChoice()
    {
        return choice;
    }
    public int getCH()
    {
        return choice;
    }
    public void work()
    {
        m=(List<Movie>) mf.findAll();
        u=(List<User>) uf.findAll();
        c=(List<Collect>) cf.findAll();
        yes=true;
    }
    public boolean getYes()
    {
        return yes;
    }
    public void setYes(boolean yes)
    {
        this.yes=yes;
    }
    public void flushM()
    {
        m.forEach((x) -> {
            mf.edit(x);
        });

     
    }
    public void flushU()
    {


        u.forEach((x) -> {
            uf.edit(x);
        });

     
    }
    public void flushC()
    {
        List<Collect> cc;
        cc = (List<Collect>)cf.findAll();
        cc.forEach((x) -> {
           cf.remove(x);
        });
        c.forEach((x) -> {
            cf.create(x);
        });
       
        
     
    }
    public void delectM(Movie m)
    {
        mf.remove(m);
    }
    public void delectC(Collect c)
    {
        cf.remove(c);
    }
    public void delectU(User u)
    {
        uf.remove(u);
    }
}
