package chooeat.reservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import chooeat.reservation.model.ReservationVO;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationVO, Integer> {
	
	

}
