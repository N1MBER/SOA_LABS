package ru.itmo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itmo.converter.FieldConverter;
import ru.itmo.service.PersonService;
import ru.itmo.utils.PersonParams;

import java.io.IOException;

@WebServlet("/persons/*")
public class PersonServlet extends HttpServlet {
    private static final String NAME_PARAM = "name";
    private static final String CREATION_DATE_PARAM = "creationDate";
    private static final String PASSPORT_ID_PARAM = "passportID";
    private static final String HEIGHT_PARAM = "height";
    private static final String NATIONALITY_PARAM = "nationality";
    private static final String HAIR_COLOR_PARAM = "hairColor";
    private static final String COORDINATES_X_PARAM = "coordinatesX";
    private static final String COORDINATES_Y_PARAM = "coordinatesY";
    private static final String PAGE_IDX_PARAM = "pageIdx";
    private static final String PAGE_SIZE_PARAM = "pageSize";
    private static final String SORT_FIELD_PARAM = "sortField";
    private static final String LOCATION_X_PARAM = "locationX";
    private static final String LOCATION_Y_PARAM = "locationY";
    private static final String LOCATION_Z_PARAM = "locationZ";
    private static final String LESS_MAXIMUM_POINT_FLAG = "lessMaximumPoint";

    private PersonService service;

    private PersonParams getPersonParams(HttpServletRequest request){
        return new PersonParams(
                request.getParameter(NAME_PARAM),
                request.getParameter(CREATION_DATE_PARAM),
                request.getParameter(COORDINATES_X_PARAM),
                request.getParameter(COORDINATES_Y_PARAM),
                request.getParameter(PASSPORT_ID_PARAM),
                request.getParameter(HEIGHT_PARAM),
                request.getParameter(LOCATION_X_PARAM),
                request.getParameter(LOCATION_Y_PARAM),
                request.getParameter(LOCATION_Z_PARAM),
                request.getParameter(HAIR_COLOR_PARAM),
                request.getParameter(NATIONALITY_PARAM),
                request.getParameter(PAGE_IDX_PARAM),
                request.getParameter(PAGE_SIZE_PARAM),
                request.getParameter(SORT_FIELD_PARAM),
                request.getParameter(LESS_MAXIMUM_POINT_FLAG)
        );
    }

    @Override
    public void init() throws ServletException {
        super.init();
        service = new PersonService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String pathInfo = req.getPathInfo();
        if (pathInfo == null){
            PersonParams filterParams = getPersonParams(req);
            service.getAllPersons(filterParams, resp);
        } else {
            String[] parts = pathInfo.split("/");
            service.getPerson(FieldConverter.longConvert(parts[1]), resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        service.createPerson(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/ml");
        service.updatePerson(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
