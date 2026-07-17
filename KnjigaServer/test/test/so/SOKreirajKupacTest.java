package test.so;

import domain.Kupac;
import domain.Mesto;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import so.kupac.SOKreirajKupac;
import test.base.TestBase;

public class SOKreirajKupacTest extends TestBase {

    private SOKreirajKupac so = new SOKreirajKupac();

    @Test
    public void saveSuccessTest() throws Exception {

        long vreme = System.currentTimeMillis();
        String telefon = "061234" + (int) (Math.random() * 10000);
        Kupac kupac = new Kupac(
                0,
                "JUnit",
                "Kupac",
                "junit" + vreme + "@gmail.com",
                telefon,
                new Mesto(1, "Beograd")
        );

        so.templateExecute(kupac);

        assertNotNull(kupac);
    }

    @Test(expected = Exception.class)
    public void saveDuplicateEmailTest() throws Exception {

        long vreme = System.currentTimeMillis();

        String email = "junit" + vreme + "@gmail.com";

        String telefon1 = "0611111111";
        String telefon2 = "0622222222";

        Mesto mesto = new Mesto(1, "Beograd");

        Kupac prvi = new Kupac(
                0,
                "Pera",
                "Peric",
                email,
                telefon1,
                mesto
        );

        so.templateExecute(prvi);

        Kupac drugi = new Kupac(
                0,
                "Marko",
                "Markovic",
                email,
                telefon2,
                mesto
        );

        so.templateExecute(drugi);
    }

    @Test(expected = Exception.class)
    public void saveDuplicatePhoneTest() throws Exception {

        long vreme = System.currentTimeMillis();

        String telefon = "0633333333";

        Mesto mesto = new Mesto(1, "Beograd");

        Kupac prvi = new Kupac(
                0,
                "Pera",
                "Peric",
                "pera" + vreme + "@gmail.com",
                telefon,
                mesto
        );

        so.templateExecute(prvi);

        Kupac drugi = new Kupac(
                0,
                "Marko",
                "Markovic",
                "marko" + vreme + "@gmail.com",
                telefon,
                mesto
        );

        so.templateExecute(drugi);
    }

    @Test(expected = Exception.class)
    public void saveInvalidPhoneFormatTest() throws Exception {

        Kupac kupac = new Kupac(
                0,
                "Pera",
                "Peric",
                "pera" + System.currentTimeMillis() + "@gmail.com",
                "12345",
                new Mesto(1, "Beograd")
        );

        so.templateExecute(kupac);
    }

    @Test(expected = Exception.class)
    public void saveInvalidEmailFormatTest() throws Exception {

        String telefon = "0644444444";

        Kupac kupac = new Kupac(
                0,
                "Pera",
                "Peric",
                "peragmail.com",
                telefon,
                new Mesto(1, "Beograd")
        );

        so.templateExecute(kupac);
    }
}
