package si.fri.prpo.skupina14.interceptorji;

import si.fri.prpo.skupina14.anotacije.BeleziKlice;
import si.fri.prpo.skupina14.zrna.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        belezenjeKlicevZrno.povecajStevec();
        return context.proceed();
    }

}