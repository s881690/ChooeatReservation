package chooeat.admin.web.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.reservation.service.ReservationService;
import chooeat.admin.web.reservation.pojo.AdminReservationVO;

@RestController
@RequestMapping("/adminSearchReservation")
public class AdminSearchReservation {
	
	@Autowired
	private ReservationService SERVICE;
	
	@GetMapping("/selectAll")
	public List<AdminReservationVO> findAll(Integer searchType, String search){
		if(searchType == 0) {
			return SERVICE.selectAll();			
		} else if (searchType == 1 || searchType == 3) {
			return SERVICE.searchByResNameOrAccName(searchType, search);
		} else if (searchType == 2 || searchType == 4) {
			try {
				int id = Integer.parseInt(search);
				return SERVICE.searchBySomethingId(searchType, id);
			} catch (NumberFormatException e) {
				return Collections.emptyList();
			}
		} else {
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/findReservation")
	public AdminReservationVO findReservation(Integer reservationId) {
		return SERVICE.findById(reservationId);
	}

}
