package si.fri.prpo.skupina14.zrna;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private Logger log = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    private static Integer stKlicevMetode = 0;

    public void povecajStevec() {
        stKlicevMetode += 1;
        log.info("Vrednost stevca je " + stKlicevMetode);
    }


}