package org.academiadecodigo.bootcamp.client;

import javax.lang.model.element.Name;
import java.io.File;

/**
 * Created by Renato on 28/02/17.
 */
public class Company extends Client {

    /**
     * Class construtor.
     * Receive String and sets the name
     * insticiates a File with the correct showCompanyFile using the name
     *
     */
    public Company() {
        super();
    }




    public int currentCompany = -1;

    public void nextCompany() {
        currentCompany++;
        if (currentCompany == CompanyInfo.values().length) {
            currentCompany = 0;
        }
        for (int i = currentCompany; i < CompanyInfo.values().length; i++) {
            System.out.println(CompanyInfo.values()[i]);
            break;
        }
    }

    public void nextCompanyMotto() {

        for (int i = currentCompany; i < CompanyInfo.values().length; i++) {
            System.out.println(CompanyInfo.values()[i].description);
            break;
        }
    }

    public void nextCompanyWebpage() {

        for (int i = currentCompany; i < CompanyInfo.values().length; i++) {
            System.out.println(CompanyInfo.values()[i].description);
            break;
        }
    }


    static void printCompanyMotto(CompanyInfo name) {
        switch (name) {
            case GOOGLE:
                System.out.println(CompanyInfo.GOOGLE.description);
                break;
            case LOGICALIS:
                System.out.println(CompanyInfo.LOGICALIS.description);
                break;
            case MICROSOFT:
                System.out.println(CompanyInfo.MICROSOFT.description);
                break;
            case ALTRAN:
                System.out.println(CompanyInfo.ALTRAN.description);
                break;
            case ACADEMIA:
                System.out.println(CompanyInfo.ACADEMIA.description);
                break;
            case READINESSIT:
                System.out.println(CompanyInfo.READINESSIT.description);
                break;
            default:
                System.out.println("Not a valid company!");

        }

    }

    public static void main(String[] args) {
        Company a = new Company();
        a.nextCompany();
        a.nextCompanyMotto();
        a.nextCompanyMotto();
        a.nextCompany();
        a.nextCompanyMotto();

    }

    public enum CompanyInfo {
        GOOGLE("Are you feeling lucky?", "resources/company/logicalis.pdf"),
        LOGICALIS("We're Dutch and super cool!", "resources/company/logicalis.pdf"),
        MICROSOFT("Open the Windows!", "resources/company/logicalis.pdf"),
        ALTRAN("America first, France second!", "resources/company/logicalis.pdf"),
        ACADEMIA("Stay here and Padawan with us!", "resources/company/logicalis.pdf"),
        READINESSIT("Get ready to travel!", "resources/company/logicalis.pdf");

        public final String description;
        public final File file;

        CompanyInfo(String desc, String path) {
            this.description = desc;
            file = new File(path);
        }



    }

}
