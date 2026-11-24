package se.lexicon.dao;

import se.lexicon.model.Attendance;

import java.sql.Connection;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {

    private Connection connection;

    public AttendanceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Attendance save(Attendance attendance) {
        return null;
    }

    @Override
    public List<Attendance> findAll() {
        return List.of();
    }
}
