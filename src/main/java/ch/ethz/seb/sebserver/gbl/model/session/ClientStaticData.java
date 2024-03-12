/*
 * Copyright (c) 2022 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gbl.model.session;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ethz.seb.sebserver.gbl.model.Domain;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientStaticData {

    public static final ClientStaticData NULL_DATA =
            new ClientStaticData(-1L, null, null, null, null, Collections.emptySet());

    @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_ID)
    public final Long id;

    @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_CONNECTION_TOKEN)
    public final String connectionToken;

    @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_EXAM_USER_SESSION_ID)
    public final String userSessionId;

    @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_ASK)
    public final String ask;

    @JsonProperty(ClientConnection.ATTR_INFO)
    public final String info;

    @JsonProperty(ClientConnectionData.ATTR_CLIENT_GROUPS)
    public final Set<Long> groups;

    @JsonCreator
    public ClientStaticData(
            @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_ID) final Long id,
            @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_CONNECTION_TOKEN) final String connectionToken,
            @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_EXAM_USER_SESSION_ID) final String userSessionId,
            @JsonProperty(Domain.CLIENT_CONNECTION.ATTR_ASK) final String ask,
            @JsonProperty(ClientConnection.ATTR_INFO) final String info,
            @JsonProperty(ClientConnectionData.ATTR_CLIENT_GROUPS) final Set<Long> groups) {

        this.id = id;
        this.connectionToken = connectionToken;
        this.userSessionId = userSessionId;
        this.ask = ask;
        this.info = info;
        this.groups = groups;
    }

    public Long getId() {
        return this.id;
    }

    public String getConnectionToken() {
        return this.connectionToken;
    }

    public String getUserSessionId() {
        return this.userSessionId;
    }

    public String getAsk() {
        return this.ask;
    }

    public String getInfo() {
        return this.info;
    }

    public Set<Long> getGroups() {
        return this.groups;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ClientStaticData other = (ClientStaticData) obj;
        return Objects.equals(this.id, other.id);
    }

}
