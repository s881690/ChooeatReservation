package chooeat.reservation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_comment_report")
public class RestaurantCommentReportVO {

    @Id
    @Column(name = "restaurant_comment_report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantCommentReportId;

   @Column(name = "acc_id")
    private Integer accId;

   @Column(name = "reservation_id")
    private Integer reservationId;

   @Column(name = "restaurant_comment_report_reason")
    private String restaurantCommentReportReason;

    public RestaurantCommentReportVO() {}

    public RestaurantCommentReportVO(Integer restaurantCommentReportId, Integer accId, Integer reservationId,
                                     String restaurantCommentReportReason) {
        super();
        this.restaurantCommentReportId = restaurantCommentReportId;
        this.accId = accId;
        this.reservationId = reservationId;
        this.restaurantCommentReportReason = restaurantCommentReportReason;
    }



    @Override
    public String toString() {
        return "RestaurantCommentReportVO [restaurantCommentReportId=" + restaurantCommentReportId + ", accId="
                + accId + ", reservationId=" + reservationId + ", restaurantCommentReportReason="
                + restaurantCommentReportReason + "]";
    }

    public Integer getRestaurantCommentReportId() {
        return restaurantCommentReportId;
    }

    public void setRestaurantCommentReportId(Integer restaurantCommentReportId) {
        this.restaurantCommentReportId = restaurantCommentReportId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getRestaurantCommentReportReason() {
        return restaurantCommentReportReason;
    }

    public void setRestaurantCommentReportReason(String restaurantCommentReportReason) {
        this.restaurantCommentReportReason = restaurantCommentReportReason;
    }



}
