/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import FFSSM.Club;
import FFSSM.GroupeSanguin;
import FFSSM.Licence;
import FFSSM.Moniteur;
import FFSSM.Personne;
import FFSSM.Plongeur;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vdelpy
 */
public class FFSMTest {
    public Calendar ddn;
    public Calendar delivrance;
    public Calendar delivrancefausse;
    public Calendar dateval;
    public Licence licenceToto;
    public Licence licenceInvalide;
    public Plongeur pv;
    public Plongeur pf;
    public Moniteur toto;
    public Club club;
    public Personne totoo;
    public Plongee plongee;
    public Site site;
    
    public FFSMTest() {
    }

    @Before
    public void setUp() {
        Calendar ddn = Calendar.getInstance();
        Calendar delivrance = Calendar.getInstance();
        Calendar delivrancefausse =  Calendar.getInstance();
        Calendar dateval = Calendar.getInstance();
        dateval.set(2019,01,01);
        delivrancefausse.set(2015,11,20);
        delivrance.set(2018,11,17);
        ddn.set(1988, 11,2);
        Personne totoo = new Personne("1","ToTo","Robot","14 Avenue des Bogdhus","06.07.08.09.10",ddn);
        Licence licenceToto = new Licence(totoo,"1",delivrance,4);
        Licence licenceInvalide = new Licence(totoo,"2",delivrancefausse,2);
        Plongeur pv = new Plongeur("2","Curt","Bryan","20 Rue des Champs","04.05.07.06.08",ddn,4,licenceInvalide,GroupeSanguin.BMOINS);
        Plongeur pf = new Plongeur("3","Abdo","Alexandre","10 Rue de Montcuq","01.23.56.78.95",ddn,3,licenceMomo,GroupeSanguin.AMOINS);
        Moniteur momo = new Moniteur("1","Toto","Robot","14 Avenue des Bogdfgous","06.07.08.09.10",ddn,4,licenceMomo,GroupeSanguin.APLUS,1);
        Club club = new Club(toto,"Mon club","06.08.09.10.11");
        Site site = new Site("oui","test");
        Plongee plongee = new Plongee(site,toto,dateval,4,4);
        licenceToto.setClub(club);
        this.ddn = ddn;
        this.delivrance = delivrance;
        this.delivrancefausse = delivrancefausse;
        this.totoo = totoo;
        this.licenceToto = licenceToto;
        this.licenceInvalide = licenceInvalide;
        this.pv = pv;
        this.pf = pf;
        this.toto = toto;
        this.club = club;
        this.dateval = dateval;
        this.site = site;
        this.plongee = plongee;
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testLicence(){
        assertEquals(true,licenceToto.estValide(dateval));
        assertEquals(false,licenceInvalide.estValide(dateval));
    }
    
    @Test
    public void testEmbauche(){
        Embauche e = new Embauche(delivrance,toto,club);
        Embauche f = new Embauche(dateval,toto,club);
        Calendar date = Calendar.getInstance();
        date.set(2018,11,20);
        e.terminer(date);
        assertEquals(true,e.estTerminee());
        assertEquals(false,f.estTerminee());
    }
    
    @Test
    public void testMoniteur(){
        assertEquals(null,toto.employeur());
        Calendar date = Calendar.getInstance();
        date.set(2018,11,20);
        toto.nouvelleEmbauche(club, date);
        assertEquals(club,toto.employeur());
    }
    
    @Test
    public void testLicencesValides(){
        assertEquals(true,toto.licencesValides(dateval));
        assertEquals(false,pv.licencesValides(dateval));
    }
    
    @Test
    public void testPlongee(){
        plongee.ajouteParticipant(toto);
        assertEquals(1,plongee.lesPlongeurs.size());
        assertEquals(true,plongee.estConforme());
        plongee.ajouteParticipant(pv);
        assertEquals(false,plongee.estConforme());
    }
}

