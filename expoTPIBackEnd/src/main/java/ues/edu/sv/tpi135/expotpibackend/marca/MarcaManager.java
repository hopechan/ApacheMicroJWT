
package ues.edu.sv.tpi135.expotpibackend.marca;

import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import ues.edu.sv.tpi135.expotpibackend.marca.cliente.SecureSystemClient;
import ues.edu.sv.tpi135.expotpibackend.marca.modelo.MarcaLista;

/**
 *
 * @author esperanza
 */
@ApplicationScoped
public class MarcaManager {
    private MarcaLista marcaList = new MarcaLista();
    SecureSystemClient secureSystemClient = new SecureSystemClient();
    
    public Properties get(String hostname, String authHeader){
        secureSystemClient.init(hostname, authHeader);
        Properties properties = secureSystemClient.getProperties();
        if (properties != null) {
        marcaList.addToMarcaList(hostname, properties);
        }
        return properties;
    }
    
    public MarcaLista list(){
        return marcaList;
    }
}
