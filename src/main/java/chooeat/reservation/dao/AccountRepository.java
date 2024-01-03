package chooeat.reservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.reservation.model.AccountVo;



public interface AccountRepository extends JpaRepository<AccountVo, Integer> {

}
