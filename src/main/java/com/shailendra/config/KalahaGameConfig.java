package com.shailendra.config;

import com.shailendra.model.KalahaBoard;
import com.shailendra.model.PlayerManager;
import com.shailendra.model.PlayerToPitMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KalahaGameConfig {

    public static final int NUMBER_OF_MARBLE_PER_PIT = 4;
    public static final int PITS_FOR_PLAYER = 7;
    public static final int NUMBER_OF_PLAYER = 2;
    public static final int TOTAL_NUMBER_OF_PITS = NUMBER_OF_PLAYER * PITS_FOR_PLAYER;

    @Bean
    public PlayerToPitMapper playerToPitMapper(KalahaBoard kalahaBoard, PlayerManager playerManager) {
        return new PlayerToPitMapper(kalahaBoard, playerManager);
    }
}
