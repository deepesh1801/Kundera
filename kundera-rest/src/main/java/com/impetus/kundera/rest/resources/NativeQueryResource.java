/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.rest.resources;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.impetus.kundera.rest.common.Constants;
import com.impetus.kundera.rest.repository.EMRepository;

/**
 * REST based resource for Native Queries 
 * @author amresh.singh
 */

@Path("/" + Constants.KUNDERA_API_PATH + Constants.NATIVE_QUERY_RESOURCE_PATH)
public class NativeQueryResource
{
    private static Log log = LogFactory.getLog(NativeQueryResource.class);
    
    /**
     * Handler for GET method requests for this resource
     * Retrieves records from datasource for a given Native query
     * @param sessionToken
     * @param nativeQuery
     * @param headers
     * @return
     */

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{nativeQuery}")
    public Response executeNativeQuery(@HeaderParam(Constants.SESSION_TOKEN_HEADER_NAME) String sessionToken,
            @PathParam("nativeQuery") String nativeQuery,
            @Context HttpHeaders headers)
    {
        log.debug("Session Token:" + sessionToken + ", Native Query:" + nativeQuery);
        EntityManager em = EMRepository.INSTANCE.getEM(sessionToken);
        
        //Query q = em.createNativeQuery(nativeQuery, paramClass)
        
        
        return null;
    }
    
    /**
     * Handler for GET method requests for this resource 
     * Retrieves all entities for the given named native query
     * @param sessionToken
     * @param entityClassName
     * @param namedNativeQueryName
     * @param headers
     * @return
     */

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{entityClass}/{namedNativeQueryName}")
    public Response executeNamedNativeQuery(@HeaderParam(Constants.SESSION_TOKEN_HEADER_NAME) String sessionToken,            
            @PathParam("entityClass") String entityClassName,
            @PathParam("namedNativeQueryName") String namedNativeQueryName,
            @Context HttpHeaders headers)
    {
        return null;
        
    }

}
