package com.spaceflightnews.respository;

import com.spaceflightnews.model.Launch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRespository extends JpaRepository<Launch, String> {
}
