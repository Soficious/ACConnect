package org.academiadecodigo.bootcamp.client;

import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Renato on 28/02/17.
 */
public class Cadet extends Client {

    private CopyOnWriteArrayList <Company> matchedCompaniesList = new CopyOnWriteArrayList<>();

    /**
     * Class construtor.
     * Receive String and sets the name
     * insticiates a File with the correct showCompanyFile using the name
     *
     */
    public Cadet(String name) {
        super(name);
    }




    public int currentCadet = -1;

    public void nextCompany() {
        currentCadet++;
        if (currentCadet == CadetInfo.values().length) {
            currentCadet = 0;
        }
        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i]);
            break;
        }
    }

    public void nextCompanyMotto() {

        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i].saying);
            break;
        }
    }

    public void nextCompanyWebpage() {

        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i].saying);
            break;
        }
    }


    static void printCompanyMotto(CadetInfo name) {
        switch (name) {
            case RENATO:
                System.out.println(CadetInfo.RENATO.saying);
                break;
            case SOFIA:
                System.out.println(CadetInfo.SOFIA.saying);
                break;
            case RICARDO:
                System.out.println(CadetInfo.RICARDO.saying);
                break;
            case JESSE:
                System.out.println(CadetInfo.JESSE.saying);
                break;
            case PEDRO:
                System.out.println(CadetInfo.PEDRO.saying);
                break;
            default:
                System.out.println("Not a valid company!");

        }

    }
/*
    public static void main(String[] args) {
        Cadet a = new Cadet(String name);
        a.nextCompany();
        a.nextCompanyMotto();
        a.nextCompanyMotto();
        a.nextCompany();
        a.nextCompanyMotto();

    }
*/
    public enum CadetInfo {


        RENATO("I'm always the loudest person in the room!", "resources/codecadet/codeCadet.pdf"),
        SOFIA("Eh pa, honestamente não concordo!", "resources/codecadet/codeCadet.pdf"),
        RICARDO("I have no battery!", "resources/codecadet/codeCadet.pdf"),
        JESSE("I fucking hate Fundão!", "resources/codecadet/codeCadet.pdf"),
        PEDRO("My battery died too!", "resources/codecadet/codeCadet.pdf");


        public final String saying;
        public final File filepath;

        CadetInfo(String desc, String path) {
            this.saying = desc;
            filepath = new File(path);
        }



    }

}
