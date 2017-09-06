package com.vain.flicker.model.status;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.Date;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("status")
public class Status {

    @Id
    private String id;

    private Date releasedAt;
    private String version;

    public Date getReleasedAt() {
        return releasedAt;
    }

    public String getVersion() {
        return version;
    }
}
