/*
 * Copyright (c) 2019 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.service.session;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.model.session.ClientConnection.ConnectionStatus;
import ch.ethz.seb.sebserver.gbl.util.Utils;

public class ColorData {

    final Color darkColor;
    final Color lightColor;
    final Color defaultColor;
    final Color color1;
    final Color color2;
    final Color color3;

    public ColorData(final Display display) {
        this.defaultColor = new Color(display, new RGB(220, 220, 220), 255);
        this.color1 = new Color(display, new RGB(255, 255, 255), 255);
        this.color2 = new Color(display, new RGB(255, 194, 14), 255);
        this.color3 = new Color(display, new RGB(237, 28, 36), 255);
        this.darkColor = new Color(display, Constants.BLACK_RGB);
        this.lightColor = new Color(display, Constants.WHITE_RGB);
    }

    Color getStatusColor(final MonitoringEntry entry) {
        final ConnectionStatus status = entry.getStatus();
        if (status == null) {
            return this.defaultColor;
        }

        switch (status) {
            case CONNECTION_REQUESTED:
            case READY:
            case ACTIVE: {
                if (entry.grantDenied()) {
                    return this.color3;
                }
                if (!entry.grantChecked() || entry.hasMissingPing()) {
                    return this.color2;
                }
                return this.color1;
            }
            default:
                return this.defaultColor;
        }
    }

    Color getStatusTextColor(final Color statusColor) {
        return Utils.darkColorContrast(statusColor.getRGB()) ? this.darkColor : this.lightColor;
    }

    int statusWeight(final MonitoringEntry entry) {
        if (entry == null || entry.getStatus() == null) {
            return 100;
        }

        return entry.getStatus().code;
    }

}
