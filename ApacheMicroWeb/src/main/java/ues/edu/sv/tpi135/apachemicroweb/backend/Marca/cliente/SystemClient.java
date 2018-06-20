/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.backend.Marca.cliente;

import java.net.URI;
import java.util.Properties;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author esperanza
 */
public class SystemClient {
     // Constants for building URI to the system service.
  private final int DEFAULT_PORT = Integer.valueOf(System.getProperty("http.port"));
  private final String SYSTEM_PROPERTIES = "/system/properties";
  private final String PROTOCOL = "http";

  private String url;
  private Builder clientBuilder;

  // Used by the following guide(s): CDI, MP-METRICS, FAULT-TOLERANCE
  public void init(String hostname) {
    this.initHelper(hostname, DEFAULT_PORT);
  }

  // Used by the following guide(s): MP-CONFIG, MP-HEALTH
  public void init(String hostname, int port) {
    this.initHelper(hostname, port);
  }

  // Helper method to set the attributes.
  private void initHelper(String hostname, int port) {
    this.url = buildUrl(PROTOCOL, hostname, port, SYSTEM_PROPERTIES);
    this.clientBuilder = buildClientBuilder(this.url);
  }

  // Wrapper function that gets properties
  public Properties getProperties() {
    return getPropertiesHelper(this.clientBuilder);
  }

  protected String buildUrl(String protocol, String host, int port, String path) {
    try {
      URI uri = new URI(protocol, null, host, port, path, null, null);
      return uri.toString();
    } catch (Exception e) {
      System.out.println("URISyntaxException");
      return null;
    }
  }

  // Method that creates the client builder
  protected Builder buildClientBuilder(String urlString) {
    try {
      Client client = ClientBuilder.newClient();
      Builder builder = client.target(urlString).request();
      return builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    } catch (Exception e) {
      System.out.println("ClientBuilderException");
      return null;
    }
  }

  protected Properties getPropertiesHelper(Builder builder) {
    try {
      Response response = builder.get();
      if (response.getStatus() == Status.OK.getStatusCode()) {
        return response.readEntity(Properties.class);
      } else {
        System.out.println("Response Status is not OK.");
      }
    } catch (RuntimeException e) {
      System.out.println("Runtime exception: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Exception thrown while invoking the request.");
    }
    return null;
  }
}
