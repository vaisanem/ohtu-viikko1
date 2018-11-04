package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVarastonJosSaldoOnNegatiivinen() {
        varasto = new Varasto(10, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEpatyhjanVaraston() {
        varasto = new Varasto(10, 1);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTaydenVarastonJosSaldoYlittaaTilavuuden() {
        varasto = new Varasto(10, 11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaNegatiivinenTilavuusNollaantuu() {
        varasto = new Varasto(-10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaNegatiivinenTilavuusNollaantuu2() {
        varasto = new Varasto(-10, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysOnHalutunlainen() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisayksenJalkeenSaldoEiYlitaVapaataTilaa() {
        varasto.lisaaVarastoon(11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiVoiOllaNegatiivinen() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottoEiVoiOllaNegatiivinen() {
        varasto.otaVarastosta(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaSaldoaEnempaa() {
        varasto.lisaaVarastoon(1);

        varasto.otaVarastosta(2);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

}