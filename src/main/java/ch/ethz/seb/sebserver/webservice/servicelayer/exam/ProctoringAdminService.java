/*
 * Copyright (c) 2022 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.exam;

import java.util.Arrays;
import java.util.EnumSet;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.seb.sebserver.gbl.api.APIMessage;
import ch.ethz.seb.sebserver.gbl.api.APIMessage.APIMessageException;
import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.EntityKey;
import ch.ethz.seb.sebserver.gbl.model.exam.Exam;
import ch.ethz.seb.sebserver.gbl.model.exam.ProctoringServiceSettings;
import ch.ethz.seb.sebserver.gbl.model.exam.ProctoringServiceSettings.ProctoringServerType;
import ch.ethz.seb.sebserver.gbl.model.exam.ScreenProctoringSettings;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.webservice.servicelayer.session.RemoteProctoringService;

public interface ProctoringAdminService {

    Logger log = LoggerFactory.getLogger(ProctoringAdminService.class);

    EnumSet<EntityType> SUPPORTED_PARENT_ENTITES = EnumSet.of(
            EntityType.EXAM,
            EntityType.EXAM_TEMPLATE);

    /** Get proctoring service settings for a certain entity (SUPPORTED_PARENT_ENTITES).
     *
     * @param parentEntityKey the entity key of the parent entity to get the proctoring service settings from
     * @return Result refer to proctoring service settings or to an error when happened. */
    Result<ProctoringServiceSettings> getProctoringSettings(EntityKey parentEntityKey);

    /** Save the given proctoring service settings for a certain entity (SUPPORTED_PARENT_ENTITES).
     *
     * @param parentEntityKey the entity key of the parent entity to save the proctoring service settings to
     * @param proctoringServiceSettings The proctoring service settings to save
     * @return Result refer to saved proctoring service settings or to an error when happened. */
    Result<ProctoringServiceSettings> saveProctoringServiceSettings(
            EntityKey parentEntityKey,
            ProctoringServiceSettings proctoringServiceSettings);

    /** Get screen proctoring service settings for a certain entity (SUPPORTED_PARENT_ENTITES).
     *
     * @param parentEntityKey the entity key of the parent entity to get the screen proctoring service settings from
     * @return Result refer to proctoring service settings or to an error when happened. */
    Result<ScreenProctoringSettings> getScreenProctoringSettings(EntityKey parentEntityKey);

    /** Save the given screen proctoring service settings for a certain entity (SUPPORTED_PARENT_ENTITES).
     *
     * @param parentEntityKey the entity key of the parent entity to save the screen proctoring service settings to
     * @param screenProctoringSettings The screen proctoring service settings to save
     * @return Result refer to saved screen proctoring service settings or to an error when happened. */
    Result<ScreenProctoringSettings> saveScreenProctoringSettings(
            EntityKey parentEntityKey,
            ScreenProctoringSettings screenProctoringSettings);

    /** Get the exam proctoring service implementation of specified type.
     *
     * @param type exam proctoring service server type
     * @return ExamProctoringService instance */
    Result<RemoteProctoringService> getExamProctoringService(final ProctoringServerType type);

    /** Gets invoked after an exam has been changed and saved.
     *
     * @param exam the exam that has been changed and saved */
    void notifyExamSaved(Exam exam);

    /** Use this to test the proctoring service settings against the remote proctoring server.
     *
     * @param proctoringSettings the settings to test
     * @return Result refer to true if the settings are correct and the proctoring server can be accessed. */
    default Result<Void> testProctoringSettings(final ProctoringServiceSettings proctoringSettings) {
        return Result.tryCatch(() -> {
            if (StringUtils.isNotBlank(proctoringSettings.serverURL)) {
                final Boolean success = getExamProctoringService(proctoringSettings.serverType)
                        .flatMap(service -> service.testExamProctoring(proctoringSettings))
                        .getOrThrow();

                if (BooleanUtils.isFalse(success)) {
                    log.error("Failed to test proctoring settings. Unknown error");
                    throw new APIMessageException(Arrays.asList(
                            APIMessage.fieldValidationError(ProctoringServiceSettings.ATTR_SERVER_TYPE,
                                    "proctoringSettings:serverType:unknown"),
                            APIMessage.ErrorMessage.EXTERNAL_SERVICE_BINDING_ERROR.of()));
                }
            } else {
                throw new APIMessageException(Arrays.asList(
                        APIMessage.fieldValidationError(ProctoringServiceSettings.ATTR_SERVER_URL,
                                "proctoringSettings:serverURL:notNull"),
                        APIMessage.ErrorMessage.EXTERNAL_SERVICE_BINDING_ERROR.of()));
            }
        });
    }

}
