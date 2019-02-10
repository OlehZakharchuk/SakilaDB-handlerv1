/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack.managed_beans;

import mypack.pojos.Actor;
import mypack.pojos.FilmActorId;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import mypack.HibernateUtil;
import mypack.pojos.Film;
import mypack.pojos.FilmActor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author zakhar
 */
@ManagedBean(name = "myBean", eager = true)
@SessionScoped
public class MyBean implements Serializable {

    Session session = null;

    Actor actor = null;
    private static SessionFactory factory;

    private String inputText;
    private String addName;
    private String addLast;
    private String addFId;
    private String addAId;

    public String getAddFId() {
        return addFId;
    }

    public void setAddFId(String addFId) {
        this.addFId = addFId;
    }

    public String getAddAId() {
        return addAId;
    }

    public void setAddAId(String addAId) {
        this.addAId = addAId;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getAddLast() {
        return addLast;
    }

    public void setAddLast(String addLast) {
        this.addLast = addLast;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public MyBean() {
    }

    public String listActorFilm() {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery("SELECT f.title FROM actor a, film f, film_actor fa "
                + "where a.actor_id=fa.actor_id AND f.film_id=fa.film_id AND a.actor_id=:inputId").setParameter("inputId", inputText);
        List<String> lista = q.list();
        tx.commit();
        session.close();
        String filmy = "";
        for (String s : lista) {
            filmy += s;
            filmy += ", ";
        }

        return filmy;
    }

    public String deleteActor() {
        factory = HibernateUtil.getSessionFactory();
        session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery("delete from actor where actor_id=:inputId").setParameter("inputId", inputText);
        q.executeUpdate();
        tx.commit();
        session.close();
        if (inputText != null) {
            return "Actor with " + inputText + " id has been deleted";
        } else {
            return " ";
        }
    }

    public String showActor() {

        factory = HibernateUtil.getSessionFactory();

        session = factory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();

//        List ac = session.createQuery("FROM Actor as actor where actor.actor_id = 222").list();
//        actor = (Actor) ac.get(0);
        if (inputText != null) {
            actor = (Actor) session.get(Actor.class, Short.valueOf(inputText));
        }

        tx.commit();

        session.close();
        try {
            return actor.getFirstName() + " " + actor.getLastName();
        } catch (Exception e) {
            return " ";
        }

    }

    public void addActor() {
        actor = new Actor();
        actor.setActorId(Short.valueOf("322"));
        actor.setFirstName(addName);
        actor.setLastName(addLast);
        actor.setLastUpdate(new Date());
        if (addName != null && addLast != null) {
            factory = HibernateUtil.getSessionFactory();

            session = factory.openSession();
            Transaction tx = null;

            tx = session.beginTransaction();

            session.save(actor);

            tx.commit();

            session.close();
        }
 

    }
    public void addFilmToActor(){
        FilmActor fa = new FilmActor(new FilmActorId(Short.valueOf(addAId), Short.valueOf(addFId)), new Actor(),
                new Film(), new Date());
        Film f = new Film();
        f.setTitle(inputText);
        if (addAId != null && addFId != null) {
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        session.save(fa);
        
        tx.commit();
        session.close();
        }
    }
    public void addMovie(){
        Film f  = new Film();
        f.setTitle(inputText);
        if(inputText!=null){
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        session.save(f);
        tx.commit();
        session.close();
            
        }
        
    }

    public String showHiberVersion() {
        return org.hibernate.Version.getVersionString();
    }

}
