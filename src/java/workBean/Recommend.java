/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workBean;
import facade.*;
import entity.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Ctios
 */
@ManagedBean
@SessionScoped
public class Recommend {
    private ArrayList<MoviePlus> m;
    String name; 
    @EJB
    New1Facade sf;
       @EJB
    CollectFacade cf;
    @EJB
    UidFacade uf;
    @EJB
    MovieFacade mf;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public ArrayList<MoviePlus> getM() {
        return m;
    }

    public void setM(ArrayList<MoviePlus> m) {
        this.m = m;
    }
    public String work()
    {
        if (m!=null)
        {
            m.forEach((x)->{
                x.isCollected=testCollect(x.n.getMid());
            });
            return "recommended";
        }
        m=new ArrayList<>();
        int num=1;
        for (int i=0;i<10;i++)
        {
            MoviePlus x=new MoviePlus();
            x.n=sf.find(i);
             if (x.n==null)
                break;
           int sum=mf.count();
            boolean yes=false;
            for (int j=1;j<=sum;j++)
            {
                x.m=mf.find(j);
                if(x.m.getName().equals(x.n.getName()))
                {
                    x.n.setMid(x.m.getMid());
                    yes=true;
                    break;
                }
            }
            if (!yes)
            {
                x.n.setMid(sum*2+num);
                num++;
            }
            
           
            x.isCollected=testCollect(x.n.getMid());
            m.add(x);
           
            
        }
        return "recommended";
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

    public void collect(MoviePlus movie)
    {
        String uid=getU();
        int mid=movie.n.getMid();
        int sum=mf.count();
        if (mid>sum)
        {
            mid=sum+1;
            movie.n.setMid(mid);
        }
        if (movie.isCollected)
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
            if (mid>sum)
            {
                Movie xm=new Movie(movie.n.getMid());
            xm.setName(movie.n.getName());
            xm.setCt(0);
            xm.setMsg(movie.n.getMsg());
            xm.setPage(movie.n.getPage());
            xm.setPf(movie.n.getPf());
            xm.setPic(movie.n.getPic());
            xm.setRank(0);
      
                mf.create(xm);  
            }
    
        }
        movie.isCollected=!movie.isCollected;
    }

 }

