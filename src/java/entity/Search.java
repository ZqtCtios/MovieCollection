/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ctios
 */
@Entity
@Table(name = "search")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Search.findAll", query = "SELECT s FROM Search s")
    , @NamedQuery(name = "Search.findByMid", query = "SELECT s FROM Search s WHERE s.mid = :mid")
    , @NamedQuery(name = "Search.findByName", query = "SELECT s FROM Search s WHERE s.name = :name")
    , @NamedQuery(name = "Search.findByPf", query = "SELECT s FROM Search s WHERE s.pf = :pf")
    , @NamedQuery(name = "Search.findByPic", query = "SELECT s FROM Search s WHERE s.pic = :pic")
    , @NamedQuery(name = "Search.findByPage", query = "SELECT s FROM Search s WHERE s.page = :page")})
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mid")
    private Integer mid;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pf")
    private Float pf;
    @Lob
    @Size(max = 65535)
    @Column(name = "msg")
    private String msg;
    @Size(max = 150)
    @Column(name = "pic")
    private String pic;
    @Size(max = 200)
    @Column(name = "page")
    private String page;

    public Search() {
    }

    public Search(Integer mid) {
        this.mid = mid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPf() {
        return pf;
    }

    public void setPf(Float pf) {
        this.pf = pf;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Search)) {
            return false;
        }
        Search other = (Search) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Search[ mid=" + mid + " ]";
    }
    
}
