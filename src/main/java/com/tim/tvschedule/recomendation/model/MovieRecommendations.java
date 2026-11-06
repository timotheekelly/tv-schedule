package com.tim.tvschedule.recomendation.model;

import java.util.List;

public record MovieRecommendations(
        List<MovieRecommendation> movies
) {}
