package org.academiadecodigo.bootcamp.client;

import java.io.File;

/**
 * Created by Renato on 28/02/17.
 */
public class Cadet extends Client {

    public Cadet() {super(); }

    public int currentCadet = -1;

    public void nextCadet() {
        currentCadet++;
        if (currentCadet == CadetInfo.values().length) {
            currentCadet = 0;
        }
        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i]);
            break;
        }
    }

    public void nextCadetPitch() {
        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i].saying);
            break;
        }
    }

    public void nextCadetCV() {
        for (int i = currentCadet; i < CadetInfo.values().length; i++) {
            System.out.println(CadetInfo.values()[i].filepath);
            break;
        }
    }

    static void printCadetPitch(CadetInfo name) {
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
                System.out.println("Not a valid cadet!");

        }

    }



//    public static void main(String[] args) {
//        Cadet a = new Cadet();
//        a.nextCadet();
//        a.nextCadetPitch();
//        a.nextCadet();
//        a.nextCadetPitch();
//        a.nextCadetPitch();
//    }

    public enum CadetInfo {
        RENATO("I'm always the loudest person in the room!", "resources/codecadet/codeCadet.pdf"),
        SOFIA("Eh pa, honestamente não concordo!", "resources/codecadet/codeCadet.pdf"),
        RICARDO("I have no battery!", "resources/codecadet/codeCadet.pdf"),
        JESSE("I fucking love Fundão!", "resources/codecadet/codeCadet.pdf"),
        PEDRO("My battery died too!", "resources/codecadet/codeCadet.pdf");

        public final String saying;
        public final File filepath;

        CadetInfo(String pitch, String cfile) {
            this.saying = pitch;
            filepath = new File(cfile);
        }

    }

}
