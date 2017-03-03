package org.academiadecodigo.bootcamp.client;

import java.io.File;

/**
 * Created by Renato on 28/02/17.
 */
public class Cadet extends Client {

    /**
     * Class construtor.
     * Receive String and sets the name
     * insticiates a File with the correct path using the name
     *
     * @param name
     * @param moto
     */
    public Cadet(String name, String moto) {
        super();
    }

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

        public String saying() {
            return this.saying;
        }

        public static void main(String[] args) {
            for (CadetInfo mantra : CadetInfo.values()) {
                System.out.println("Cadet pitch: " + mantra.saying());
            }
        }
    }

}
