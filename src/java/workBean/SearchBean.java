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
public class SearchBean {
    private ArrayList<MoviePlus> m;
    String name; 
    @EJB
    SearchFacade sf;
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
    public void work()
    {
        String cmds = "python E:\\PythonWorkSpace\\豆瓣\\search.py "+name;
        try {
            Process p = Runtime.getRuntime().exec(cmds);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
        }
        m=new ArrayList<>();
        int num=1;
        for (int i=0;i<4;i++)
        {
            MoviePlus x=new MoviePlus();
            x.s=sf.find(i);
             if (x.s==null)
                break;
              sf.remove(x.s);
           int sum=mf.count();
            boolean yes=false;
            for (int j=1;j<=sum;j++)
            {
                x.m=mf.find(j);
                if(x.m.getName().equals(x.s.getName()))
                {
                    x.s.setMid(x.m.getMid());
                    yes=true;
                    break;
                }
            }
            if (!yes)
            {
                x.s.setMid(sum+num);
                num++;
            }
            
           
            x.isCollected=testCollect(x.s.getMid());
            m.add(x);
           
            
        }
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
        int mid=movie.s.getMid();
        int sum=mf.count();
        if (mid>sum)
        {
            mid=sum+1;
            movie.s.setMid(mid);
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
            Movie xm=new Movie(movie.s.getMid());
            xm.setName(movie.s.getName());
            xm.setCt(0);
            xm.setMsg(movie.s.getMsg());
            xm.setPage(movie.s.getPage());
            xm.setPf(movie.s.getPf());
            xm.setPic(movie.s.getPic());
            xm.setRank(0);
      
                mf.create(xm);
            }
          
            
        }
        movie.isCollected=!movie.isCollected;
    }

 }

