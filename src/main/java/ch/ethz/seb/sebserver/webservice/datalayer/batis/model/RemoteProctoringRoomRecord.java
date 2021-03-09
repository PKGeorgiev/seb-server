package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class RemoteProctoringRoomRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.122+01:00", comments="Source field: remote_proctoring_room.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.exam_id")
    private Long examId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.size")
    private Integer size;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.subject")
    private String subject;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.townhall_room")
    private Integer townhallRoom;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.break_out_connections")
    private String breakOutConnections;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.122+01:00", comments="Source Table: remote_proctoring_room")
    public RemoteProctoringRoomRecord(Long id, Long examId, String name, Integer size, String subject, Integer townhallRoom, String breakOutConnections) {
        this.id = id;
        this.examId = examId;
        this.name = name;
        this.size = size;
        this.subject = subject;
        this.townhallRoom = townhallRoom;
        this.breakOutConnections = breakOutConnections;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.122+01:00", comments="Source field: remote_proctoring_room.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.exam_id")
    public Long getExamId() {
        return examId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.size")
    public Integer getSize() {
        return size;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.subject")
    public String getSubject() {
        return subject;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.townhall_room")
    public Integer getTownhallRoom() {
        return townhallRoom;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-03-09T08:24:51.124+01:00", comments="Source field: remote_proctoring_room.break_out_connections")
    public String getBreakOutConnections() {
        return breakOutConnections;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table remote_proctoring_room
     *
     * @mbg.generated Tue Mar 09 08:24:51 CET 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", examId=").append(examId);
        sb.append(", name=").append(name);
        sb.append(", size=").append(size);
        sb.append(", subject=").append(subject);
        sb.append(", townhallRoom=").append(townhallRoom);
        sb.append(", breakOutConnections=").append(breakOutConnections);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table remote_proctoring_room
     *
     * @mbg.generated Tue Mar 09 08:24:51 CET 2021
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
        RemoteProctoringRoomRecord other = (RemoteProctoringRoomRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExamId() == null ? other.getExamId() == null : this.getExamId().equals(other.getExamId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getTownhallRoom() == null ? other.getTownhallRoom() == null : this.getTownhallRoom().equals(other.getTownhallRoom()))
            && (this.getBreakOutConnections() == null ? other.getBreakOutConnections() == null : this.getBreakOutConnections().equals(other.getBreakOutConnections()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table remote_proctoring_room
     *
     * @mbg.generated Tue Mar 09 08:24:51 CET 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamId() == null) ? 0 : getExamId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getTownhallRoom() == null) ? 0 : getTownhallRoom().hashCode());
        result = prime * result + ((getBreakOutConnections() == null) ? 0 : getBreakOutConnections().hashCode());
        return result;
    }
}