package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter,Long> {

    Chapter findChaptersById(Long id);
}
