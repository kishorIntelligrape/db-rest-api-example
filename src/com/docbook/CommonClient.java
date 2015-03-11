package com.docbook;

import org.restlet.data.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;

public class CommonClient {

    protected ClientResource getClientResource(String endPoint) {
        ClientResource resource = new ClientResource(endPoint);
        Series<Header> headers = (Series<Header> )resource.getRequestAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        if (headers == null) {
            headers = new Series(Header.class);
            resource.getRequestAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS, headers);
        }
        headers.add("email", Config.EMAIL);
        headers.add("token", Config.TOKEN);
        return resource;
    }

}
