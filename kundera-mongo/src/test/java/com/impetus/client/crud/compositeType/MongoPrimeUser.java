/*******************************************************************************
 * * Copyright 2012 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.client.crud.compositeType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author vivek.mishra
 *
 */

@Entity
@Table(name="User", schema="KunderaExamples@mongoTest")
public class MongoPrimeUser
{
    
    @EmbeddedId
    private MongoCompoundKey key;
    
    @Column
    private String tweetBody;
    
    @Column 
    private Date tweetDate;

    public MongoPrimeUser()
    {
    }
    
    public MongoPrimeUser(MongoCompoundKey key)
    {
        this.key = key;
    }
    
    /**
     * @return the key
     */
    public MongoCompoundKey getKey()
    {
        return key;
    }

    /**
     * @return the tweetBody
     */
    public String getTweetBody()
    {
        return tweetBody;
    }

    /**
     * @return the tweetDate
     */
    public Date getTweetDate()
    {
        return tweetDate;
    }

    /**
     * @param tweetBody the tweetBody to set
     */
    public void setTweetBody(String tweetBody)
    {
        this.tweetBody = tweetBody;
    }

    /**
     * @param tweetDate the tweetDate to set
     */
    public void setTweetDate(Date tweetDate)
    {
        this.tweetDate = tweetDate;
    }
}
