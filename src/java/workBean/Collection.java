/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workBean;

import entity.Collect;
import entity.CollectPK;
import entity.Movie;
import entity.Search;
import entity.Uid;
import facade.CollectFacade;
import facade.MovieFacade;
import facade.UidFacade;
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
public class Collection {
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
        m=new ArrayList<>();
        int sum=mf.count();
        for (Integer i=1;i<=sum;i++)
        {
            MoviePlus x=new MoviePlus();
            x.m = mf.find(i);
            x.isCollected=testCollect(x.m.getMid());
            if (x.isCollected)
            m.add(x);
        }
        return "collection.xhtml";
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
        int mid=movie.m.getMid();
        CollectPK c=new CollectPK(uid,mid);
        Collect x=cf.find(c);
        cf.remove(x);
        m.remove(movie);
    }
}
