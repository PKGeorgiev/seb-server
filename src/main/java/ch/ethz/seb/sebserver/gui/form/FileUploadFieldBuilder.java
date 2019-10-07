/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.form;

import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ch.ethz.seb.sebserver.gui.service.i18n.LocTextKey;
import ch.ethz.seb.sebserver.gui.widget.FileUploadSelection;

public class FileUploadFieldBuilder extends FieldBuilder<String> {

    private final Collection<String> supportedFiles;

    FileUploadFieldBuilder(
            final String name,
            final LocTextKey label,
            final String value,
            final Collection<String> supportedFiles) {

        super(name, label, value);
        this.supportedFiles = supportedFiles;
    }

    @Override
    void build(final FormBuilder builder) {

        final Label lab = builder.labelLocalized(
                builder.formParent,
                this.label,
                this.defaultLabel,
                1);

        final Composite fieldGrid = Form.createFieldGrid(builder.formParent, this.spanInput);
        final FileUploadSelection fileUpload = builder.widgetFactory.fileUploadSelection(
                fieldGrid,
                builder.readonly || this.readonly,
                this.supportedFiles);
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        fileUpload.setLayoutData(gridData);
        fileUpload.setFileName(this.value);

        final Label errorLabel = Form.createErrorLabel(fieldGrid);
        builder.form.putField(this.name, lab, fileUpload, errorLabel);
        builder.setFieldVisible(this.visible, this.name);
    }

}