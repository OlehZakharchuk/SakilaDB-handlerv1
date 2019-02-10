/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack.managed_beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mypack.HibernateUtil;
import mypack.pojos.Film;
import mypack.pojos.Language;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zakhar
 */
@Named(value = "beanFilm")
@SessionScoped
public class BeanFilm implements Serializable {

    private String titleF;
    private String rentalRate;
    private String replacCost;
    private String idLang;

    private Language l ;
    Session session = null;

    public BeanFilm() {
    }

    public void addMovie() {
       

        if (titleF != null &&rentalRate!=null && rentalRate!=null ) {
            getLang();
            Film f = new Film(l, titleF, Byte.valueOf("0"), BigDecimal.valueOf(Double.valueOf(rentalRate)),
            BigDecimal.valueOf(Double.valueOf(replacCost)), new Date());
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            session.save(f);
            tx.commit();
            session.close();
        }

    }
    public void getLang(){
        session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            l=(Language) session.get(Language.class, Byte.valueOf(idLang));
        
            
            tx.commit();
            session.close();
    }
    
    public String getIdLang() {
        return idLang;
    }

    public void setIdLang(String idLang) {
        this.idLang = idLang;
    }
    public String getTitleF() {
        return titleF;
    }

    public void setTitleF(String titleF) {
        this.titleF = titleF;
    }

    public String getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(String rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getReplacCost() {
        return replacCost;
    }

    public void setReplacCost(String replacCost) {
        this.replacCost = replacCost;
    }

}
