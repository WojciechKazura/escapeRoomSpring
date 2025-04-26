package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class WeightForScale extends Item {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "weight_for_scale_id")
    private WeightMechanism weightMechanism;

    private int weight;

    public WeightForScale() {
    }

    public WeightForScale(int weight) {
        super("Ciężarek", ItemType.WEIGHTFORSCALE);
        this.weight = weight;
    }

    @Override
    public String use(Context context) {
        return "Nie określono strony wagi.";
    }

    public String use(Context context, String side) {
        String result = "Ciężarek umieszczony na " + side + " stronie wagi.";
        if ("left".equalsIgnoreCase(side)) {
            use(context);
            return "llllllllllllllll";
        } else if ("right".equalsIgnoreCase(side)) {
            return "ppppppppppppppppppppp";
        }

        return result;
    }

}

