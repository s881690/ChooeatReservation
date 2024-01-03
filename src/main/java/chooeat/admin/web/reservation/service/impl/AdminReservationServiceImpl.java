package chooeat.admin.web.reservation.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.reservation.dao.AdminReservationRepository;
import chooeat.admin.web.reservation.pojo.AdminReservationVO;
import chooeat.admin.web.reservation.service.ReservationService;

@Service
public class AdminReservationServiceImpl implements ReservationService {

	@Autowired
	private AdminReservationRepository adminReservationRepository;

	@Override
	public List<AdminReservationVO> selectAll() {
		List<AdminReservationVO> reservationList = adminReservationRepository.findAll();
		
		Iterator<AdminReservationVO> iterator = reservationList.iterator();

		while (iterator.hasNext()) {
		    AdminReservationVO i = iterator.next();
		    if (i.getRestaurantCommentDatetime() == null) {
		        iterator.remove();
		    }
		}
		return reservationList;
	}

	@Override
	public AdminReservationVO findById(Integer reservationId) {
		return adminReservationRepository.findByReservationId(reservationId);
	}

	@Override
	public List<AdminReservationVO> searchBySomethingId(Integer searchType, Integer id) {
		
		String stringId = "%" + String.valueOf(id) + "%";
		List<AdminReservationVO> reservationList = new ArrayList<AdminReservationVO>();
		
		if(searchType == 2) {
			reservationList = adminReservationRepository.findByResId(stringId);
		} else if(searchType == 4) {
			reservationList = adminReservationRepository.findByAccId(stringId);
		} else {
			return null;
		}
		
		Iterator<AdminReservationVO> iterator = reservationList.iterator();

		while (iterator.hasNext()) {
		    AdminReservationVO i = iterator.next();
		    if (i.getRestaurantCommentDatetime() == null) {
		        iterator.remove();
		    }
		}
		return reservationList;
	}

	@Override
	public List<AdminReservationVO> searchByResNameOrAccName(Integer searchType, String search) {
		
		List<AdminReservationVO> reservationList = new ArrayList<AdminReservationVO>();
		
		if(searchType == 1) {
			reservationList = adminReservationRepository.findByResNameLike(search);
		} else if (searchType == 3) {
			reservationList = adminReservationRepository.findByAccName(search);
		} else {
			return null;
		}
		
		Iterator<AdminReservationVO> iterator = reservationList.iterator();

		while (iterator.hasNext()) {
		    AdminReservationVO i = iterator.next();
		    if (i.getRestaurantCommentDatetime() == null) {
		        iterator.remove();
		    }
		}
		return reservationList;
	}	
	
}
