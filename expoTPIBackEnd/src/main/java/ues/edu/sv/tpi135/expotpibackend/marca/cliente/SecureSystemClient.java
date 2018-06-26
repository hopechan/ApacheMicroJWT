
package ues.edu.sv.tpi135.expotpibackend.marca.cliente;

import java.util.Properties;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author esperanza
 */
public class SecureSystemClient extends SystemClient{
    // Constants for building URI to the system service.
  private final int DEFAULT_SEC_PORT = Integer.valueOf(
      System.getProperty("https.port"));
  private final String SYSTEM_PROPERTIES = "/system/properties";
  private final String SECURED_PROTOCOL = "https";

  private String url;
  private Builder clientBuilder;

  // Overiding the parent method to set the attributes.
  public void init(String hostname, String authHeader) {
    this.url = this.buildUrl(SECURED_PROTOCOL, hostname, DEFAULT_SEC_PORT,
        SYSTEM_PROPERTIES);
    this.clientBuilder = this.buildClientBuilder(authHeader);
  }

  public String buildUrl(String protocol, String host, int port, String path) {
    return super.buildUrl(protocol, host, port, path);
  }

  public Builder buildClientBuilder(String authHeader) {
    Builder builder = super.buildClientBuilder(this.url);
    return builder.header(HttpHeaders.AUTHORIZATION, authHeader);
  }

  public Properties getProperties() {
    return super.getPropertiesHelper(this.clientBuilder);
  }
}
