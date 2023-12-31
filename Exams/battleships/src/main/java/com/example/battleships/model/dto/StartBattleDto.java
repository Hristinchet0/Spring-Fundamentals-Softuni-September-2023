package com.example.battleships.model.dto;

import jakarta.validation.constraints.Positive;

public class StartBattleDto {

    @Positive
    private Long attackerId;

    @Positive
    private Long defenderId;

    public StartBattleDto() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
    }
}
