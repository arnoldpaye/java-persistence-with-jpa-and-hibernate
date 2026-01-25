package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.dto.TeacherAvgRating;
import com.arnex.app.entities.Review;

public interface ReviewRepository {
    public void add(Review review);

    public void update(Review review);

    public void remove(Review review);

    public Double getAvgRatingForTeacher(String teacher);

    public List<TeacherAvgRating> getAvgRatingsByTeachers();
}
