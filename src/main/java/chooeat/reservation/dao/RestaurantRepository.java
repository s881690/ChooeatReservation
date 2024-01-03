package chooeat.reservation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chooeat.reservation.model.RestaurantVO;



@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantVO, Integer> {


	
	Optional<RestaurantVO> findById(Integer id);
	
	

}
