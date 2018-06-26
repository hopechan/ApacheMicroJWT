
package ues.edu.sv.tpi135.expotpifrontend.ui;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import ues.edu.sv.tpi135.expotpifrontend.ui.modelos.SystemModel;
import ues.edu.sv.tpi135.expotpifrontend.ui.utilidades.ServiceUtils;
import ues.edu.sv.tpi135.expotpifrontend.ui.utilidades.SessionUtils;

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
    return "You are not authorized to access the system service.";
  }

  public String getInventorySize() {
    String authHeader = getJwt();
    if (ServiceUtils.invOk(authHeader)) {
      JsonObject properties = ServiceUtils.getInventory(authHeader);
      return String.valueOf(properties.getInt("total"));
    }
    return "You are not authorized to access the inventory service.";
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
