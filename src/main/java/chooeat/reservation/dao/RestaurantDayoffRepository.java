package chooeat.reservation.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import chooeat.reservation.model.RestaurantDayoffVO;



@Repository
public interface RestaurantDayoffRepository extends JpaRepository<RestaurantDayoffVO, Integer> {

	@Query(value="select * FROM restaurant_dayoff WHERE restaurant_id = ?1 and dayoff = ?2 ",nativeQuery = true)
	RestaurantDayoffVO dayOffDate(Integer restaurant_id, Date date);
	
	
	

}
