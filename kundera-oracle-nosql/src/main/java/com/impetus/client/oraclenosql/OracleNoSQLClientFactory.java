/**
 * Copyright 2013 Impetus Infotech.
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
package com.impetus.client.oraclenosql;

import java.util.Map;
import java.util.Properties;

import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.kundera.PersistenceProperties;
import com.impetus.kundera.client.Client;
import com.impetus.kundera.configure.schema.api.SchemaManager;
import com.impetus.kundera.index.IndexManager;
import com.impetus.kundera.loader.GenericClientFactory;
import com.impetus.kundera.metadata.model.KunderaMetadata;
import com.impetus.kundera.metadata.model.PersistenceUnitMetadata;
import com.impetus.kundera.persistence.EntityReader;

/**
 * <Prove description of functionality provided by this Type>
 * 
 * @author amresh.singh
 */
public class OracleNoSQLClientFactory extends GenericClientFactory
{

    /** The logger. */
    private static Logger logger = LoggerFactory.getLogger(OracleNoSQLClientFactory.class);

    /** The index manager. */
    IndexManager indexManager;

    /** The kvstore db. */
    private KVStore kvStore;

    /** The reader. */
    private EntityReader reader;  
    
    @Override
    public SchemaManager getSchemaManager(Map<String, Object> puProperties)
    {
        return null;
    }

    @Override
    public void initialize(Map<String, Object> puProperties)
    {
        setExternalProperties(puProperties);
    }

    @Override
    protected Client instantiateClient(String persistenceUnit)
    {
        return new OracleNoSQLClient(this, kvStore, externalProperties, getPersistenceUnit());
    }

    @Override
    public boolean isThreadSafe()
    {
        return false;
    }
    
    @Override
    public void destroy()
    {
        indexManager.close();
        if (schemaManager != null)
        {
            getSchemaManager(externalProperties).dropSchema();
        }
        schemaManager = null;
        externalProperties = null;
        
        if (kvStore != null)
        {
            logger.info("Closing connection to kvStore.");
            kvStore.close();
            logger.info("Closed connection to kvStore.");
        }
        else
        {
            logger.warn("Can't close connection to kvStore, it was already disconnected");
        }
    }   

    @Override
    protected Object createPoolOrConnection()
    {
        kvStore = getConnection();
        return kvStore;
    }

    /**
     * Gets the connection.
     * 
     * @return the connection
     */
    private KVStore getConnection()
    {

        PersistenceUnitMetadata persistenceUnitMetadata = KunderaMetadata.INSTANCE.getApplicationMetadata()
                .getPersistenceUnitMetadata(getPersistenceUnit());

        Properties props = persistenceUnitMetadata.getProperties();
        String hostName = (String) props.get(PersistenceProperties.KUNDERA_NODES);
        String defaultPort = (String) props.get(PersistenceProperties.KUNDERA_PORT);
        // keyspace is keystore
        String storeName = (String) props.get(PersistenceProperties.KUNDERA_KEYSPACE);
        String poolSize = props.getProperty(PersistenceProperties.KUNDERA_POOL_SIZE_MAX_ACTIVE);
        return KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName + ":" + defaultPort));
    }

    /**
     * @param puProperties
     */
    protected void setExternalProperties(Map<String, Object> puProperties)
    {
        if (this.externalProperties == null)
        {
            this.externalProperties = puProperties;
        }
    }

}
