/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workBean;
import facade.*;
import entity.*;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ctios
 */
@ManagedBean
@SessionScoped

public class ShowMovie {
    private ArrayList<MoviePlus> m;
    @EJB
    MovieFacade mf;
       @EJB
    CollectFacade cf;
    @EJB
    UidFacade uf;
    public ArrayList<MoviePlus>getM()
    {
        return m;
    }
    public void setM(ArrayList<MoviePlus> m)
    {
       this.m=m;
    }
    public String work()
    {
        m=new ArrayList<>();;
        for (Integer i=1;i<=100;i++)
        {
            MoviePlus x=new MoviePlus();
            x.m = mf.find(i);
            x.isCollected=testCollect(x.m.getMid());
            if (x.m==null)
                break;
            m.add(x);
        }
        return "top.xhtml";
    }
     public String getU()
    {
        Uid u=uf.find(1);
        return u.getUid();
    }
        public boolean testCollect(int mid)
    {
        String uid=getU();
        CollectPK c=new CollectPK(uid,mid);
        Collect x=cf.find(c);
        return x!=null;
    }

    public void collect(MoviePlus m)
    {
        String uid=getU();
        int mid=m.m.getMid();
 
        if (m.isCollected)
        {
            CollectPK c=new CollectPK(uid,mid);
            Collect x=cf.find(c);
            cf.remove(x);
        }
        else
        {
                    CollectPK c=new CollectPK(uid,mid);
                    Collect x=new Collect(c);
                    cf.create(x);
        }
               m.isCollected=!m.isCollected;
    }

}

