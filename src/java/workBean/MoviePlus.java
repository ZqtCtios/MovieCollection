/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workBean;

import entity.*;

/**
 *
 * @author Ctios
 */
public class MoviePlus {
    public Movie m;
    public Search s;
    public New1 n;
    public boolean isCollected;
    public MoviePlus()
    {
        m=new Movie();
        s=new Search();
        n=new New1();
        isCollected=true;
    }
    public New1 getN()
    {
        return n;
    }
    public void setN(New1 n)
    {
        this.n=n;
    }
    public Search getS()
    {
        return s;
    }
    public void setS(Search s)
    {
        this.s=s;
    }
        public Movie getM()
    {
        return m;
    }
    public void setM(Movie m)
    {
        this.m=m;
    }
    public boolean getIsCollected()
    {
        return isCollected;
    }
    public void setIsCollected(boolean isCollected)
    {
        this.isCollected=isCollected;
    }
}
