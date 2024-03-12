/*
 * Copyright (c) 2019 ETH Zürich, IT Services
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.dao.impl;

import java.util.Collection;
import java.util.Optional;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.gbl.util.Utils;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.AdditionalAttributeRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.AdditionalAttributeRecordMapper;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.model.AdditionalAttributeRecord;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.AdditionalAttributesDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.NoResourceFoundException;

@Lazy
@Component
@WebServiceProfile
public class AdditionalAttributesDAOImpl implements AdditionalAttributesDAO {

    private static final Logger log = LoggerFactory.getLogger(AdditionalAttributesDAOImpl.class);

    private final AdditionalAttributeRecordMapper additionalAttributeRecordMapper;

    protected AdditionalAttributesDAOImpl(final AdditionalAttributeRecordMapper additionalAttributeRecordMapper) {
        this.additionalAttributeRecordMapper = additionalAttributeRecordMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Collection<AdditionalAttributeRecord>> getAdditionalAttributes(
            final EntityType type,
            final Long entityId) {

        return Result.tryCatch(() -> this.additionalAttributeRecordMapper
                .selectByExample()
                .where(
                        AdditionalAttributeRecordDynamicSqlSupport.entityType,
                        SqlBuilder.isEqualTo(type.name()))
                .and(
                        AdditionalAttributeRecordDynamicSqlSupport.entityId,
                        SqlBuilder.isEqualTo(entityId))
                .build()
                .execute());
    }

    @Override
    @Transactional(readOnly = true)
    public Result<AdditionalAttributeRecord> getAdditionalAttribute(
            final EntityType type,
            final Long entityId,
            final String attributeName) {

        return Result.tryCatch(() -> this.additionalAttributeRecordMapper
                .selectByExample()
                .where(
                        AdditionalAttributeRecordDynamicSqlSupport.entityType,
                        SqlBuilder.isEqualTo(type.name()))
                .and(
                        AdditionalAttributeRecordDynamicSqlSupport.entityId,
                        SqlBuilder.isEqualTo(entityId))
                .and(
                        AdditionalAttributeRecordDynamicSqlSupport.name,
                        SqlBuilder.isEqualTo(attributeName))
                .build()
                .execute()
                .stream()
                .findAny()
                .orElseThrow(() -> new NoResourceFoundException(
                        EntityType.ADDITIONAL_ATTRIBUTES,
                        attributeName)));
    }

    @Override
    public Result<Collection<AdditionalAttributeRecord>> getAdditionalAttribute(
            final EntityType type,
            final String attributeName) {

        return Result.tryCatch(() -> {
            return this.additionalAttributeRecordMapper
                    .selectByExample()
                    .where(
                            AdditionalAttributeRecordDynamicSqlSupport.entityType,
                            SqlBuilder.isEqualTo(type.name()))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.name,
                            SqlBuilder.isEqualTo(attributeName))
                    .build()
                    .execute();
        });
    }

    @Override
    @Transactional
    public Result<AdditionalAttributeRecord> saveAdditionalAttribute(
            final EntityType type,
            final Long entityId,
            final String name,
            final String value) {

        return Result.tryCatch(() -> {

            if (value == null) {
                throw new IllegalArgumentException(
                        "value cannot be null. Use delete to delete an additional attribute: " + String.valueOf(name));
            }

            if (log.isDebugEnabled()) {
                log.debug("Save additional attribute. Type: {}, entity: {}, name: {}, value: {}",
                        type, entityId, name, value);
            }

            final Optional<Long> id = this.additionalAttributeRecordMapper
                    .selectIdsByExample()
                    .where(
                            AdditionalAttributeRecordDynamicSqlSupport.entityType,
                            SqlBuilder.isEqualTo(type.name()))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.entityId,
                            SqlBuilder.isEqualTo(entityId))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.name,
                            SqlBuilder.isEqualTo(name))
                    .build()
                    .execute()
                    .stream()
                    .findFirst();

            if (id.isPresent()) {
                final AdditionalAttributeRecord rec = new AdditionalAttributeRecord(
                        id.get(),
                        type.name(),
                        entityId,
                        name,
                        Utils.truncateText(value, 4000));
                this.additionalAttributeRecordMapper
                        .updateByPrimaryKeySelective(rec);

                return this.additionalAttributeRecordMapper
                        .selectByPrimaryKey(rec.getId());
            } else {

                final AdditionalAttributeRecord rec = new AdditionalAttributeRecord(
                        null,
                        type.name(),
                        entityId,
                        name,
                        Utils.truncateText(value, 4000));
                this.additionalAttributeRecordMapper
                        .insert(rec);

                return this.additionalAttributeRecordMapper
                        .selectByPrimaryKey(rec.getId());
            }
        });
    }

    @Override
    @Transactional
    public boolean initAdditionalAttribute(
            final EntityType type,
            final Long entityId,
            final String name,
            final String value) {

        try {

            final boolean exists = this.additionalAttributeRecordMapper
                    .countByExample()
                    .where(
                            AdditionalAttributeRecordDynamicSqlSupport.entityType,
                            SqlBuilder.isEqualTo(type.name()))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.entityId,
                            SqlBuilder.isEqualTo(entityId))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.name,
                            SqlBuilder.isEqualTo(name))
                    .build()
                    .execute() > 0;

            if (!exists) {
                final AdditionalAttributeRecord rec = new AdditionalAttributeRecord(
                        null,
                        type.name(),
                        entityId,
                        name,
                        Utils.truncateText(value, 4000));
                this.additionalAttributeRecordMapper
                        .insert(rec);
            }

            return !exists;
        } catch (final Exception e) {
            log.error("Failed to initialize additional attribute: type {}, id {}, name {}, value {}",
                    type, entityId, name, value, e);
            return false;
        }
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        this.additionalAttributeRecordMapper
                .deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void delete(final EntityType type, final Long entityId, final String name) {

        try {

            if (log.isDebugEnabled()) {
                log.debug("Delete additional attribute. Type: {}, entity: {}, name: {}",
                        type, entityId, name);
            }

            this.additionalAttributeRecordMapper
                    .deleteByExample()
                    .where(
                            AdditionalAttributeRecordDynamicSqlSupport.entityType,
                            SqlBuilder.isEqualTo(type.name()))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.entityId,
                            SqlBuilder.isEqualTo(entityId))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.name,
                            SqlBuilder.isEqualTo(name))
                    .build()
                    .execute();
        } catch (final Exception e) {
            log.error("Failed to delete additional attribute: Type: {}, entity: {}, name: {}",
                    type, entityId, name, e);
        }
    }

    @Override
    @Transactional
    public void deleteAll(final EntityType type, final Long entityId) {
        try {

            if (log.isDebugEnabled()) {
                log.debug("Delete all additional attributes. Type: {}, entity: {}",
                        type, entityId);
            }

            this.additionalAttributeRecordMapper
                    .deleteByExample()
                    .where(
                            AdditionalAttributeRecordDynamicSqlSupport.entityType,
                            SqlBuilder.isEqualTo(type.name()))
                    .and(
                            AdditionalAttributeRecordDynamicSqlSupport.entityId,
                            SqlBuilder.isEqualTo(entityId))
                    .build()
                    .execute();
        } catch (final Exception e) {
            log.error("Failed to delete all additional attributes for: {} cause: {}", entityId, e.getMessage());
        }
    }

}
