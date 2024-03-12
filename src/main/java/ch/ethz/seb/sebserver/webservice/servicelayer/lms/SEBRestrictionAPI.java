/*
 * Copyright (c) 2022 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.lms;

import ch.ethz.seb.sebserver.gbl.model.exam.Exam;
import ch.ethz.seb.sebserver.gbl.model.exam.SEBRestriction;
import ch.ethz.seb.sebserver.gbl.model.institution.LmsSetup;
import ch.ethz.seb.sebserver.gbl.model.institution.LmsSetupTestResult;
import ch.ethz.seb.sebserver.gbl.util.Result;

public interface SEBRestrictionAPI {

    /** Performs a test for the underling {@link LmsSetup } configuration and checks if the
     * LMS and the course restriction API of the LMS can be accessed or if there are some difficulties,
     * missing configuration data or connection/authentication errors.
     *
     * @return {@link LmsSetupTestResult } instance with the test result report */
    LmsSetupTestResult testCourseRestrictionAPI();

    /** Get SEB restriction data from LMS within a {@link SEBRestriction } instance. The available restriction
     * details
     * depends on the type of LMS but shall at least contains the config-key(s) and the browser-exam-key(s).
     *
     * @param exam the exam to get the SEB restriction data for
     * @return Result refer to the {@link SEBRestriction } instance or to an ResourceNotFoundException if the
     *         restriction is
     *         missing or to another exception on unexpected error case */
    Result<SEBRestriction> getSEBClientRestriction(Exam exam);

    /** Use this to check if there is a SEB restriction available on the LMS for the specified exam.
     * <p>
     * A SEB Restriction is available if it can get from LMS and if there is either a Config-Key
     * or a BrowserExam-Key set or both. If none of this keys is set, the SEB Restriction is been
     * considered to not set on the LMS.
     *
     * @param exam exam the exam to get the SEB restriction data for
     * @return true if there is a SEB restriction set on the LMS for the exam or false otherwise */
    default boolean hasSEBClientRestriction(final Exam exam) {
        return hasSEBClientRestriction(getSEBClientRestriction(exam).getOrThrow());
    }

    default boolean hasSEBClientRestriction(final SEBRestriction sebRestriction) {
        return !sebRestriction.configKeys.isEmpty() || !sebRestriction.browserExamKeys.isEmpty();
    }

    /** Applies SEB Client restrictions to the LMS with the given attributes.
     *
     * @param exam The exam to apply the restriction for
     * @param sebRestrictionData containing all data for SEB Client restriction to apply to the LMS
     * @return Result refer to the given {@link SEBRestriction } if restriction was successful or to an error if
     *         not */
    Result<SEBRestriction> applySEBClientRestriction(
            Exam exam,
            SEBRestriction sebRestrictionData);

    /** Releases an already applied SEB Client restriction within the LMS for a given Exam.
     * This completely removes the SEB Client restriction on LMS side.
     *
     * @param exam the Exam to release the restriction for.
     * @return Result refer to the given Exam if successful or to an error if not */
    Result<Exam> releaseSEBClientRestriction(Exam exam);

}
