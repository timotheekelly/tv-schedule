package com.tim.tvschedule.application.web.dto;

public sealed interface ProgramContentResponse
        permits MovieResponse, TvShowResponse {
}