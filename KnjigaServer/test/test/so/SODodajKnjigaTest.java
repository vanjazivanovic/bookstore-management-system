package test.so;

import domain.Knjiga;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import so.knjiga.SODodajKnjiga;
import test.base.TestBase;

public class SODodajKnjigaTest extends TestBase {

    private SODodajKnjiga so = new SODodajKnjiga();

    @Test
    public void saveSuccessTest() throws Exception {

        long vreme = System.currentTimeMillis();

        Knjiga knjiga = new Knjiga(
                0,
                "JUnit Knjiga " + vreme,
                "JUnit Autor",
                "Opis",
                1200,
                "978" + vreme,
                "JUnit Izdavac"
        );

        so.templateExecute(knjiga);

        assertNotNull(knjiga);
    }

    @Test(expected = Exception.class)
    public void savePreconditionInvalidPriceTest() throws Exception {

        long vreme = System.currentTimeMillis();

        Knjiga knjiga = new Knjiga(
                0,
                "JUnit Cena " + vreme,
                "JUnit Autor",
                "Opis",
                -100,
                "978" + vreme,
                "JUnit Izdavac"
        );

        so.templateExecute(knjiga);
    }

    @Test(expected = Exception.class)
    public void saveDuplicateISBNTest() throws Exception {

        long vreme = System.currentTimeMillis();

        String isbn = "978" + vreme;

        Knjiga prva = new Knjiga(
                0,
                "Prva " + vreme,
                "Autor",
                "Opis",
                1000,
                isbn,
                "Laguna"
        );

        so.templateExecute(prva);

        Knjiga druga = new Knjiga(
                0,
                "Druga " + vreme,
                "Drugi autor",
                "Opis",
                1200,
                isbn,
                "Vulkan"
        );

        so.templateExecute(druga);
    }

    @Test(expected = Exception.class)
    public void saveDuplicateNameAuthorTest() throws Exception {

        long vreme = System.currentTimeMillis();

        String naziv = "JUnit Knjiga " + vreme;
        String autor = "JUnit Autor";

        Knjiga prva = new Knjiga(
                0,
                naziv,
                autor,
                "Opis",
                1000,
                "978111" + vreme,
                "Laguna"
        );

        so.templateExecute(prva);

        Knjiga druga = new Knjiga(
                0,
                naziv,
                autor,
                "Drugi opis",
                1500,
                "978222" + vreme,
                "Vulkan"
        );

        so.templateExecute(druga);
    }
}
