/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ctios
 */
@Entity
@Table(name = "collect")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collect.findAll", query = "SELECT c FROM Collect c")
    , @NamedQuery(name = "Collect.findByUid", query = "SELECT c FROM Collect c WHERE c.collectPK.uid = :uid")
    , @NamedQuery(name = "Collect.findByMid", query = "SELECT c FROM Collect c WHERE c.collectPK.mid = :mid")})
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CollectPK collectPK;

    public Collect() {
    }

    public Collect(CollectPK collectPK) {
        this.collectPK = collectPK;
    }

    public Collect(String uid, int mid) {
        this.collectPK = new CollectPK(uid, mid);
    }

    public CollectPK getCollectPK() {
        return collectPK;
    }

    public void setCollectPK(CollectPK collectPK) {
        this.collectPK = collectPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collectPK != null ? collectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collect)) {
            return false;
        }
        Collect other = (Collect) object;
        if ((this.collectPK == null && other.collectPK != null) || (this.collectPK != null && !this.collectPK.equals(other.collectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Collect[ collectPK=" + collectPK + " ]";
    }
    
}
