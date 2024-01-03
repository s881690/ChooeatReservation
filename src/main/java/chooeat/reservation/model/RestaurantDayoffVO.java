package chooeat.reservation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "restaurant_dayoff")
public class RestaurantDayoffVO {

    @Id
    @Column(name = "dayoff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayoffId;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

   @Column(name = "dayoff")
    private Date dayoff;

    public RestaurantDayoffVO() {}


    @Override
    public String toString() {
        return "restaurant_dayoffVO [dayoffId=" + dayoffId + ", restaurantId=" + restaurantId + ", dayoff=" + dayoff
                + "]";
    }



    public RestaurantDayoffVO(Integer dayoffId, Integer restaurantId, Date dayoff) {
        super();
        this.dayoffId = dayoffId;
        this.restaurantId = restaurantId;
        this.dayoff = dayoff;
    }



    public Integer getDayoffId() {
        return dayoffId;
    }

    public void setDayoffId(Integer dayoffId) {
        this.dayoffId = dayoffId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getDayoff() {
        return dayoff;
    }

    public void setDayoff(Date dayoff) {
        this.dayoff = dayoff;
    }



}
