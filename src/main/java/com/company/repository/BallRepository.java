package com.company.repository;

import com.company.beans.Game;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface BallRepository {
    public abstract void insertPerBallData(Connection con, Game game);
}
