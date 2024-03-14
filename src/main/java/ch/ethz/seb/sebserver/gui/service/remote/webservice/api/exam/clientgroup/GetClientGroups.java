/*
 * Copyright (c) 2019 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.service.remote.webservice.api.exam.clientgroup;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import ch.ethz.seb.sebserver.gbl.api.API;
import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.exam.ClientGroup;
import ch.ethz.seb.sebserver.gbl.profile.GuiProfile;
import ch.ethz.seb.sebserver.gui.service.remote.webservice.api.PageToListCallAdapter;

@Lazy
@Component
@GuiProfile
public class GetClientGroups extends PageToListCallAdapter<ClientGroup> {

    public GetClientGroups() {
        super(
                GetClientGroupPage.class,
                EntityType.CLIENT_GROUP,
                new TypeReference<List<ClientGroup>>() {
                },
                API.EXAM_CLIENT_GROUP_ENDPOINT);
    }
}
