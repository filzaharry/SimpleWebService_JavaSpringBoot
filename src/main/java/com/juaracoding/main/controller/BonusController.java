package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;

@RestController
@RequestMapping("/bonus")
public class BonusController  {

	@Autowired
	JdbcTemplate jdbc;

	public List<Bonus> getBonus() {

		String sql = "SELECT * FROM bonus WHERE 1";

		List<Bonus> bonus = jdbc.query(sql, new BonusRowMapper());

		return bonus;

	}
	

	public int insertBonus(Bonus bonus) {
		return jdbc.update("insert into bonus(worker_ref_id,bonus_date,bonus_amount) values ('" + bonus.getWorker_ref_id() + "','"
				+ bonus.getBonus_date() + "','" + bonus.getBonus_amount() + "')");

	}

	// PR
	public int updateBonus(Integer worker_ref_id, Bonus bonus) {

		return jdbc.update("UPDATE bonus SET `bonus_date`='" + bonus.getBonus_date() + "',`bonus_amount`='" + bonus.getBonus_amount()
				+ "' WHERE worker_ref_id = '"+ bonus.getWorker_ref_id() + "'");

	}

	public int deleteWorker(Integer worker_ref_id) {
		return jdbc.update("DELETE FROM `bonus`  WHERE `worker_ref_id` = '" + worker_ref_id + "';");
		
	
	}

	

	 @PostMapping("/")
	    public String add(@RequestBody Bonus bonus) {
		 

			if (this.insertBonus(bonus) == 1) {
				return "Insert data Bonus berhasil";
			} else {
				return "Insert data Bonus gagal";
			}
	    }
	 
	 
	 
	 
	 @DeleteMapping("/{worker_ref_id}")
	    public void delete(@PathVariable Integer worker_ref_id) {
		 	deleteWorker(worker_ref_id);
	 }
	 
	 
	 @GetMapping("/")
	    public List<Bonus> list() {
	        return getBonus();
	    }
	 
	 
	 
	 @PutMapping("/{worker_ref_id}")
	    public ResponseEntity<?> update(@RequestBody Bonus bonus, @PathVariable Integer worker_ref_id) {
		 try {
	            updateBonus(worker_ref_id, bonus);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

}