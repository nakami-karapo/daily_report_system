package controllers.attendances;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.validators.AttendanceValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesUpdateServlet
 */
@WebServlet("/attendances/update")
public class AttendancesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Attendance r = em.find(Attendance.class, (Integer)(request.getSession().getAttribute("attendance_id")));

            r.setAttendance_date(Date.valueOf(request.getParameter("attendance_date")));
            r.setClockIn_time(Integer.parseInt(request.getParameter("clockIn_time")));
            r.setClockOut_time(Integer.parseInt(request.getParameter("clockOut_time")));
            r.setBreak_hour(Integer.parseInt(request.getParameter("break_hour")));
//            r.setWorking_hour(Integer.parseInt(request.getParameter("working_hour")));

            Integer calClockIn_hour  = Integer.parseInt(request.getParameter("clockIn_time")) / 100 * 60
                                       + Integer.parseInt(request.getParameter("clockIn_time")) % 100;
            Integer calClockOut_hour = Integer.parseInt(request.getParameter("clockOut_time")) / 100 * 60
                                       + Integer.parseInt(request.getParameter("clockOut_time")) % 100;
            Integer calBreak_hour    = Integer.parseInt(request.getParameter("break_hour")) / 100 * 60
                                      + Integer.parseInt(request.getParameter("break_hour")) % 100;
            Integer calWorking_hour = (calClockOut_hour - calClockIn_hour - calBreak_hour) / 60 * 100
                                      + (calClockOut_hour - calClockIn_hour - calBreak_hour) % 60;
            r.setWorking_hour(calWorking_hour);

            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = AttendanceValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("attendance", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("attendance_id");

                response.sendRedirect(request.getContextPath() + "/attendances/index");
            }
        }
    }

}