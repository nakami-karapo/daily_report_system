package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Attendance;

public class AttendanceValidator {
    public static List<String> validate(Attendance r) {
        List<String> errors = new ArrayList<String>();

        String clockIn_time_error = _validateClockIn_time(r.getClockIn_time());
        if(!clockIn_time_error.equals("")) {
            errors.add(clockIn_time_error);
        }

        String clockOut_time_error = _validateClockOut_time(r.getClockOut_time());
        if(!clockOut_time_error.equals("")) {
            errors.add(clockOut_time_error);
        }

        String break_hour_error = _validateBreak_hour(r.getBreak_hour());
        if(!break_hour_error.equals("")) {
            errors.add(break_hour_error);
        }

        return errors;
    }

    private static String _validateClockIn_time(Integer clockIn_time) {
        if(clockIn_time == null || clockIn_time.equals("")) {
            return "出社時刻を入力してください。";
            }

        return "";
    }

    private static String _validateClockOut_time(Integer clockOut_time) {
        if(clockOut_time == null || clockOut_time.equals("")) {
            return "退社時刻を入力してください。";
            }

        return "";
    }

    private static String _validateBreak_hour(Integer break_hour) {
        if(break_hour == null || break_hour.equals("")) {
            return "休憩時間を入力してください。";
            }

        return "";
    }

}