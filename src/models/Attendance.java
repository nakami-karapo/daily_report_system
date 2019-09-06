package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "attendances")
@NamedQueries({
    @NamedQuery(
            name = "getAllAttendances",
            query = "SELECT r FROM Attendance AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(r) FROM Attendance AS r"
            ),
    @NamedQuery(
            name = "getMyAllAttendances",
            query = "SELECT r FROM Attendance AS r WHERE r.employee = :employee and r.attendance_date = :attendance_date ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getMyAttendancesCount",
            query = "SELECT COUNT(r) FROM Attendance AS r WHERE r.employee = :employee and r.attendance_date = :attendance_date"
            )
})
@Entity
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "attendance_date", nullable = false)
    private Date attendance_date;

    @Column(name = "clockIn_time", nullable = false)
    private Integer clockIn_time;

    @Column(name = "clockOut_time", nullable = false)
    private Integer clockOut_time;

    @Column(name = "break_hour", nullable = false)
    private Integer break_hour;

    @Column(name = "working_hour", nullable = false)
    private Integer working_hour;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public Integer getClockIn_time() {
        return clockIn_time;
    }

    public void setClockIn_time(Integer clockIn_time) {
        this.clockIn_time = clockIn_time;
    }

    public Integer getClockOut_time() {
        return clockOut_time;
    }

    public void setClockOut_time(Integer clockOut_time) {
        this.clockOut_time = clockOut_time;
    }

    public Integer getBreak_hour() {
        return break_hour;
    }

    public void setBreak_hour(Integer break_hour) {
        this.break_hour = break_hour;
    }

    public Integer getWorking_hour() {
        return working_hour;
    }

    public void setWorking_hour(Integer working_hour) {
        this.working_hour = working_hour;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}