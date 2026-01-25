package com.arnex.app.repository;

import com.arnex.app.entities.Teacher;

public interface TeacherRepository {
    public void add(Teacher teacher);

    public void update(Teacher teacher);

    public void remove(Teacher teacher);

    public Teacher getTeacherById(Integer id);
}
