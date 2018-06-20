
package ues.edu.sv.tpi135.apachemicroweb.frontend.ui.modelos;

import javax.json.JsonObject;

/**
 *
 * @author esperanza
 */
public class SystemModel {
    private String hostname;
    private String username;
    private String osName;

    public SystemModel(JsonObject jo) {
        hostname = jo.getString("hostname");

        JsonObject props = jo.getJsonObject("properties");
        username = props.getString("user.name");
        osName = props.getString("os.name");
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getOsName() {
        return osName;
    }
}
