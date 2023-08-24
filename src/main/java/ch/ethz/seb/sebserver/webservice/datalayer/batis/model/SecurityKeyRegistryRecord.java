package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class SecurityKeyRegistryRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.institution_id")
    private Long institutionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.key_type")
    private String keyType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.key_value")
    private String keyValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.tag")
    private String tag;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.809+02:00", comments="Source field: seb_security_key_registry.exam_id")
    private Long examId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.809+02:00", comments="Source field: seb_security_key_registry.exam_template_id")
    private Long examTemplateId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source Table: seb_security_key_registry")
    public SecurityKeyRegistryRecord(Long id, Long institutionId, String keyType, String keyValue, String tag, Long examId, Long examTemplateId) {
        this.id = id;
        this.institutionId = institutionId;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.tag = tag;
        this.examId = examId;
        this.examTemplateId = examTemplateId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.institution_id")
    public Long getInstitutionId() {
        return institutionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.key_type")
    public String getKeyType() {
        return keyType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.key_value")
    public String getKeyValue() {
        return keyValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.808+02:00", comments="Source field: seb_security_key_registry.tag")
    public String getTag() {
        return tag;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.809+02:00", comments="Source field: seb_security_key_registry.exam_id")
    public Long getExamId() {
        return examId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-08-24T13:24:15.810+02:00", comments="Source field: seb_security_key_registry.exam_template_id")
    public Long getExamTemplateId() {
        return examTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seb_security_key_registry
     *
     * @mbg.generated Thu Aug 24 13:24:15 CEST 2023
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", institutionId=").append(institutionId);
        sb.append(", keyType=").append(keyType);
        sb.append(", keyValue=").append(keyValue);
        sb.append(", tag=").append(tag);
        sb.append(", examId=").append(examId);
        sb.append(", examTemplateId=").append(examTemplateId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seb_security_key_registry
     *
     * @mbg.generated Thu Aug 24 13:24:15 CEST 2023
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SecurityKeyRegistryRecord other = (SecurityKeyRegistryRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getKeyType() == null ? other.getKeyType() == null : this.getKeyType().equals(other.getKeyType()))
            && (this.getKeyValue() == null ? other.getKeyValue() == null : this.getKeyValue().equals(other.getKeyValue()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getExamId() == null ? other.getExamId() == null : this.getExamId().equals(other.getExamId()))
            && (this.getExamTemplateId() == null ? other.getExamTemplateId() == null : this.getExamTemplateId().equals(other.getExamTemplateId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seb_security_key_registry
     *
     * @mbg.generated Thu Aug 24 13:24:15 CEST 2023
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getKeyType() == null) ? 0 : getKeyType().hashCode());
        result = prime * result + ((getKeyValue() == null) ? 0 : getKeyValue().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getExamId() == null) ? 0 : getExamId().hashCode());
        result = prime * result + ((getExamTemplateId() == null) ? 0 : getExamTemplateId().hashCode());
        return result;
    }
}