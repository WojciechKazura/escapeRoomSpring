package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Mirror extends Item {

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "black_wall_id")
    private  BlackWall blackWall;

    @Setter
    @Getter
    private int angle;

    @Getter
    private int correctAngle;

    @Getter
    private boolean code = false;

    public Mirror() {
    }

    public Mirror(String name, int angle, int correctAngle) {
        super(name, ItemType.MIRRORFORRIDDLE);
        this.angle = angle;
        this.correctAngle = correctAngle;
    }

    @Override
    public String use(Context context) {
        if (angle != 360) {
            angle = angle + 45;
            code = angle == correctAngle;
        } else {
            angle = 0;
        }
        return "kąt = " + angle+"//// "+correctAngle;
    }

}
