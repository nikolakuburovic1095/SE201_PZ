/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao.impl;

import com.se201.pz.dao.KorisnikDao;
import com.se201.pz.entity.Korisnik;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Repository("korisnikDao")
@Service
public class KorisnikDaoImpl implements KorisnikDao {

    @SuppressWarnings("unused")
    private final Log logger = LogFactory.getLog(getClass());

    //Instanciramo sesiju
    @Autowired
    private SessionFactory sessionFactory;

    //kreiramo seter za sesiju
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //kreiramo geter za sesiju
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Korisnik> getListaKorisnika() {
        return getSession().createCriteria(Korisnik.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Korisnik dodajKorisnika(Korisnik korisnik) {
        return (Korisnik) getSession().merge(korisnik);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Korisnik getKorisnikById(Integer id) {
        return (Korisnik) getSession().createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Korisnik getKorisnikByUsername(String username) {
        return (Korisnik) getSession().createCriteria(Korisnik.class).add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Transactional
    @Override
    public boolean deleteKorisnik(Korisnik korisnik) {
        try {
            getSession().delete(korisnik);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
