package io.github.devandref.client;

import io.github.devandref.dto.CurrencyPriceDTO;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/last")
@RegisterRestClient(baseUri="https://economia.awesomeapi.com.br")
@ApplicationScoped
public interface CurrencyPriceClient {

    @GET
    @Path("/{pair}")
    CurrencyPriceDTO getPriceByPair(@PathParam("pair") String pair);

}
