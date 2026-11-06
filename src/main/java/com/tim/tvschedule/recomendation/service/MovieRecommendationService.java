package com.tim.tvschedule.recomendation.service;

import com.tim.tvschedule.recomendation.model.MovieRecommendations;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class MovieRecommendationService {

    private final ChatClient chatClient;

    public MovieRecommendationService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public MovieRecommendations recommend(String prompt, int count) {
        return chatClient.prompt()
                .system("""
                    You are a film curator.

                    Recommend real, existing movies based on the
                    user's requested theme.

                    Return exactly the requested number of movies.
                    Include the original release year for each movie.
                    Do not invent titles or include duplicates.
                    """)
                .user("""
                    Theme: %s
                    Number of movies: %d
                    """.formatted(prompt, count))
                .call()
                .entity(MovieRecommendations.class);
    }
}
