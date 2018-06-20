/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.frontend.ui;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import ues.edu.sv.tpi135.apachemicroweb.frontend.ui.modelos.SystemModel;
import ues.edu.sv.tpi135.apachemicroweb.frontend.ui.utils.ServiceUtils;
import ues.edu.sv.tpi135.apachemicroweb.frontend.ui.utils.SessionUtils;

/**
 *
 * @author esperanza
 */
@ManagedBean
@ViewScoped
public class ApplicationBean {
      private String hostname;

  public String getJwt() {
    String jwtTokenString = SessionUtils.getJwtToken();
    String authHeader = "Bearer " + jwtTokenString;
    return authHeader;
  }

  public String getOs() {
    String authHeader = getJwt();
    if (ServiceUtils.responseOk(authHeader)) {
      JsonObject properties = ServiceUtils.getProperties(authHeader);
      return properties.getString("os.name");
    }
    return "No esta autorizado a entrar al sistema.";
  }

  public String getInventorySize() {
    String authHeader = getJwt();
    if (ServiceUtils.invOk(authHeader)) {
      JsonObject properties = ServiceUtils.getInventory(authHeader);
      return String.valueOf(properties.getInt("total"));
    }
    return "No esta autorizado a entrar al sistema de marcas.";
  }

  public List<SystemModel> getInventoryList() {
    String authHeader = getJwt();
    if (ServiceUtils.invOk(authHeader)) {
      JsonArray systems = ServiceUtils.getInventory(authHeader).getJsonArray("systems");
      return systems.stream().map(s -> new SystemModel((JsonObject) s)).collect(Collectors.toList());
    }

    return Collections.emptyList();
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
    String authHeader = getJwt();

    if (ServiceUtils.invOk(authHeader)) {
      ServiceUtils.addSystem(hostname, authHeader);
    }
  }

  public String getUsername() {
    String authHeader = getJwt();
    return ServiceUtils.getJwtUsername(authHeader);
  }

  public String getUserRole() {
    String authHeader = getJwt();
    return ServiceUtils.getJwtRoles(authHeader);
  }
}
