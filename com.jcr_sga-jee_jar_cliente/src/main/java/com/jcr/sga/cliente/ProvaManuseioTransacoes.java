package com.jcr.sga.cliente;

import com.jcr.sga.domain.Pessoa;
import com.jcr.sga.service.PessoaServiceRemote;
import java.util.Properties;
import javax.naming.*;
import org.apache.logging.log4j.*;

public class ProvaManuseioTransacoes {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        try {
            
                Properties props = new Properties();
 props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
 props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
 // glassfish default port value will be 3700,
props.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); 
            Context jndi = new InitialContext(props);
            PessoaServiceRemote pessoaService = (PessoaServiceRemote) jndi.lookup("java:global/sga-jee-web/PessoaServiceImpl!com.jcr.sga.service.PessoaServiceRemote");
            log.debug("Iniciando teste de manuseio transacional PessoaService");
            
            Pessoa pessoa1 = pessoaService.encontrarPessoaPorId(new Pessoa(1));
            
            log.debug("Pessoa Recuperada : " + pessoa1);
            
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
    }
    
}
