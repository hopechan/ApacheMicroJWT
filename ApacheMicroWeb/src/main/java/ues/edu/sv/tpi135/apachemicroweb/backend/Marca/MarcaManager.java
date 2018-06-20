/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.backend.Marca;

import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import ues.edu.sv.tpi135.apachemicroweb.backend.Marca.cliente.SecureSystemClient;
import ues.edu.sv.tpi135.apachemicroweb.backend.Marca.modelo.MarcaLista;

/**
 *
 * @author esperanza
 */
@ApplicationScoped
public class MarcaManager {
  private MarcaLista marcaList = new MarcaLista();
  SecureSystemClient secureSystemClient = new SecureSystemClient();

  public Properties get(String hostname, String authHeader) {
    secureSystemClient.init(hostname, authHeader);
    Properties properties = secureSystemClient.getProperties();
    if (properties != null) {
      marcaList.addToMarcaList(hostname, properties);
    }
    return properties;
  }

  public MarcaLista list() {
    return marcaList;
  }
}
